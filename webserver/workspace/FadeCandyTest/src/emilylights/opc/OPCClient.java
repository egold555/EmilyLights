package emilylights.opc;

import java.io.OutputStream;
import java.net.Socket;

import emilylights.animation.Animation;


public class OPCClient implements AutoCloseable {

	private Socket socket;
	protected OutputStream output;
	private final String host;
	private final int port;
	protected final int channel = 0;
	protected byte[] packetHeader;

	/**
	 * Construct a new OPC Client.
	 * 
	 * @param hostname Host name or IP address.
	 * @param portNumber Port number
	 */
	public OPCClient(String hostname, int portNumber) {
		this.host = hostname;
		this.port = portNumber;
	}


	/**
	 * Close a socket connection to the OPC server.
	 */

	@Override
	public void close() {
		try {
			if (this.output != null) {
				this.output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.output = null;
		}

		try {
			if (this.socket != null && (!this.socket.isClosed())) {
				this.socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.socket = null;
		}
	}



	/**
	 * Open a socket connection to the OPC server.
	 */
	protected void open() {
		if (this.output == null) {
			try {
				socket = new Socket(this.host, this.port);
				socket.setTcpNoDelay(true);
				output = socket.getOutputStream();
			} catch (Exception e) {
				log("open: error: " + e, e);
				this.close();
			}
		}
	}



	/**
	 * Run one frame of an animation and send it to the OPC server.
	 */
	public void animate(Animation animation) {
		animation.reset();
		animation.initInternal();
		animation.draw();
		show(animation);
	}

	public void clear(Animation animation) {
		animation.reset();
		show(animation);
	}



	/**
	 * Push all pixel changes to the strip.
	 */
	public void show(Animation animation) {
		int numPixels = Animation.PIXEL_COUNT;
		int numBytes = 3 * numPixels;
		int headerLen = 4;
		if (packetHeader == null || packetHeader.length != headerLen) {
			packetHeader = new byte[headerLen];
		}
		packetHeader[0] = (byte) this.channel;
		packetHeader[1] = 0; // Command (Set pixel colors)
		packetHeader[2] = (byte) (numBytes >> 8);
		packetHeader[3] = (byte) (numBytes & 0xFF);

		byte[] pixelData = animation.getPixels();
		limit_brightness(pixelData);

		if (this.output != null) {
			this.open();
		}
		writePixels(packetHeader, pixelData);
	}

	// Gamma curve for computing power draw for a given brightness.
	private static short[] gamma = {
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  1,  1,  1,
			1,  1,  1,  1,  1,  1,  1,  1,  1,  2,  2,  2,  2,  2,  2,  2,
			2,  3,  3,  3,  3,  3,  3,  3,  4,  4,  4,  4,  4,  5,  5,  5,
			5,  6,  6,  6,  6,  7,  7,  7,  7,  8,  8,  8,  9,  9,  9, 10,
			10, 10, 11, 11, 11, 12, 12, 13, 13, 13, 14, 14, 15, 15, 16, 16,
			17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 24, 24, 25,
			25, 26, 27, 27, 28, 29, 29, 30, 31, 32, 32, 33, 34, 35, 35, 36,
			37, 38, 39, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 50,
			51, 52, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 66, 67, 68,
			69, 70, 72, 73, 74, 75, 77, 78, 79, 81, 82, 83, 85, 86, 87, 89,
			90, 92, 93, 95, 96, 98, 99,101,102,104,105,107,109,110,112,114,
			115,117,119,120,122,124,126,127,129,131,133,135,137,138,140,142,
			144,146,148,150,152,154,156,158,160,162,164,167,169,171,173,175,
			177,180,182,184,186,189,191,193,196,198,200,203,205,208,210,213,
			215,218,220,223,225,228,231,233,236,239,241,244,247,249,252,255 };


	// Maximum gamma-adjusted brightness.
	private static final int max_brightness = 164;  // probably should be 164

	private void limit_brightness(byte[] pixelData)
	{
		// Get the average gamma-corrected brightness level.
		long total_pixels = 0;
		for (int i = 0; i < pixelData.length; ++i) {
			total_pixels += gamma[pixelData[i] & 0xFF];
		}
		int avg_pixel = (int) (total_pixels / pixelData.length);

		if (avg_pixel > max_brightness) {
			// The pixels are too bright for the power supply. Scale down.
			//System.out.println("Pixels too bright: average brightness = " + avg_pixel + "   (limit = " + max_brightness + ")");

			int unscaled_avg = 255;
			while (gamma[unscaled_avg] > avg_pixel) {
				--unscaled_avg;
			}
			int unscaled_max = 255;
			while (gamma[unscaled_max] > max_brightness) {
				--unscaled_max;
			}			

			float scale = (float) unscaled_max / (float) unscaled_avg;
			//System.out.println("Scaling down by scale factor = " + scale);

			for (int i = 0; i < pixelData.length; ++i) {
				pixelData[i] = (byte)(int)((pixelData[i] & 0xFF) * scale);
			}

			// Recompute average brightness, as a check.
			//			total_pixels = 0;
			//			for (int i = 0; i < pixelData.length; ++i) {
			//				total_pixels += gamma[pixelData[i] & 0xFF];
			//			}
			//			avg_pixel = (int) (total_pixels / pixelData.length);
			//System.out.println("New average brightness = " + avg_pixel);
			//System.out.println();
		}
	}


	/**
	 * Push a pixel buffer out the socket to the OPC server.
	 */
	protected void writePixels(byte[] packetHeader, byte[] pixelData) {
		if (packetHeader == null || packetHeader.length == 0) {
			log("writePixels: no packet data", null);
			return;
		}
		if (output == null) {
			open();
		}
		if (output == null) {
			log("writePixels: no socket", null);
			return;
		}

		try {
			output.write(packetHeader);
			output.write(pixelData);
			output.flush();
		} catch (Exception e) {
			log("writePixels: error : " + e, e);
			close();
		}
	}


	@Override
	public String toString() {
		return "OpcClient(" + this.host + "," + this.port + ")";
	}

	/**
	 * Print a message out to the console.
	 */
	protected void log(String msg, Exception e) {
		System.out.println(msg);
		if (e != null) {
			e.printStackTrace();
		}
	}
}
