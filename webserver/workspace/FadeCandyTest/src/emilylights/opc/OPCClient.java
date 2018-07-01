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
		
		if (this.output != null) {
			this.open();
		}
		writePixels(packetHeader, pixelData);
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
