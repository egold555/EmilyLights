package emilylights.scene;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import javax.imageio.IIOException;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import emilylights.http.WebServer;
import emilylights.scene.options.Color;
import emilylights.scene.options.SceneOptions;
import emilylights.utils.GifSequenceWriter;

public abstract class Scene {

	protected static final Random RANDOM = new Random();

	private long startTime = System.currentTimeMillis();

	public static final int MAX_COLS = 11;
	public static final int MAX_ROWS = 9;
	public static final int PIXEL_COUNT = MAX_ROWS * MAX_COLS;

	private byte[] pixels = null;
	
	// Use these to override the time when creating a GIF.
	private boolean overrideTime = false;
	private double currentTime;

	public final void fillCol(int col, Color color) {
		fillRect(0, col, MAX_ROWS - 1, col, color);
	}

	public final void fillRow(int row, Color color) {
		fillRect(row, 0, row,  MAX_COLS - 1, color);
	}

	public final void setAllPixels(Color color) {
		fillRect(0, 0, MAX_ROWS-1, MAX_COLS-1, color);
	}

	public final void setLineRow(int row, int start, int end, Color color) {
		for (int col = start; col < end; col++) {
			this.setPixel(row, col, color);
		}
	}

	public final void setLineCol(int col, int start, int end, Color color) {
		if (start <= end) {
			for (int row = start; row <= end; row++) {
				this.setPixel(row, col, color);
			}
		} else {
			for (int row = end; row <= start; row++) {
				this.setPixel(row, col, color);
			}
		}

	}

	public final void fillRect(int firstRow, int firstCol, int lastRow, int lastCol, Color color) {
		for(int row = firstRow; row <= lastRow; row++) {
			for(int col = firstCol; col <= lastCol; col++) {
				setPixel(row, col, color);
			}
		}
	}

	public final void setPixel(int row, int col, Color color) {
		if (row < 0 || row >= MAX_ROWS || col < 0 || col >= MAX_COLS)
			return;
		setPixel(row + col * MAX_ROWS, color);
	}

	public final void setPixel(int num, Color color) {
		if (num < 0 || num >= PIXEL_COUNT) {
			return;
		}

		int offset = num * 3;
		pixels[offset] = (byte) color.getRed();
		pixels[offset+1] = (byte) color.getGreen();
		pixels[offset+2] = (byte) color.getBlue();
	}


	public final void addPixel(int row, int col, Color color) {
		addPixel(row, col, color.getRed(), color.getGreen(), color.getBlue());
	}
	
	@Deprecated
	public final void addPixel(int row, int col, int red, int green, int blue) {
		if (row < 0 || row >= MAX_ROWS || col < 0 || col >= MAX_COLS)
			return;
		addPixel(row + col * MAX_ROWS, red, green, blue);
	}

//	@Deprecated
//	public final void addPixel(int num, int[] rgb) {
//		addPixel(num, rgb[0], rgb[1], rgb[2]);
//	}
	
	@Deprecated
	public final void addPixel(int num, int red, int green, int blue) {
		if (num < 0 || num >= PIXEL_COUNT) {
			return;
		}

		int offset = num * 3;

		red += (pixels[offset] & 0xFF);
		if (red > 255) {
			red = 255;
		}
		green += (pixels[offset + 1] & 0xFF);
		if (green > 255) {
			green = 255;
		}
		blue += (pixels[offset + 2] & 0xFF);
		if (blue > 255) {
			blue = 255;
		}

		pixels[offset] = (byte) red;
		pixels[offset+1] = (byte) green;
		pixels[offset+2] = (byte) blue;
	}

	@Deprecated
	public final void addPixelAntiAlias(double row, double col, int red, int green, int blue) {
		if (row <= -1 || row >= MAX_ROWS || col <= -1 || col >= MAX_COLS)
			return;

		int row1 = (int) Math.round(row);
		int col1 = (int) Math.round(col);
		int row2, col2;
		double rowFrac1, rowFrac2, colFrac1, colFrac2;
		double rowFrac = row - row1;
		double colFrac = col - col1;

		if (rowFrac == 0 && colFrac == 0) {
			addPixel((int) row, (int) col, new Color(red, green, blue));
			return;
		}

		if (rowFrac <= 0) {
			row2 = row1 - 1;
			rowFrac2 = -rowFrac;
			rowFrac1 = 1 - rowFrac2;
		}
		else {
			row2 = row1 + 1;
			rowFrac2 = rowFrac;
			rowFrac1 = 1 - rowFrac2;
		}

		if (colFrac <= 0) {
			col2 = col1 - 1;
			colFrac2 = -colFrac;
			colFrac1 = 1 - colFrac2;
		}
		else {
			col2 = col1 + 1;
			colFrac2 = colFrac;
			colFrac1 = 1 - colFrac2;
		}

		double r1c1Frac = rowFrac1 * colFrac1;
		double r1c2Frac = rowFrac1 * colFrac2;
		double r2c1Frac = rowFrac2 * colFrac1;
		double r2c2Frac = rowFrac2 * colFrac2;

		addPixel(row1, col1, (int) Math.round(red * r1c1Frac), (int) Math.round(green * r1c1Frac), (int) Math.round(blue * r1c1Frac));
		addPixel(row1, col2, (int) Math.round(red * r1c2Frac), (int) Math.round(green * r1c2Frac), (int) Math.round(blue * r1c2Frac));
		addPixel(row2, col1, (int) Math.round(red * r2c1Frac), (int) Math.round(green * r2c1Frac), (int) Math.round(blue * r2c1Frac));
		addPixel(row2, col2, (int) Math.round(red * r2c2Frac), (int) Math.round(green * r2c2Frac), (int) Math.round(blue * r2c2Frac));
	}

	public final void addPixelAntiAlias(double row, double col, Color color)
	{
		addPixelAntiAlias(row, col, color.getRed(), color.getGreen(), color.getBlue());
	}


	public final void reset()
	{
		if(first) {
			init();
			first = false;
		}

		if (pixels == null) {
			pixels = new byte[3 * PIXEL_COUNT];
		}

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public abstract void draw();
	public void init() {};
	
	public void setOptions(SceneOptions options) { }

	private boolean first = true;

	public final byte[] getPixels() {
		return pixels;
	}

//	@Deprecated
//	public final int[] rgbToInt(float r, float g, float b) {
//		return new int[] {(int)(r * 255), (int)(g * 255), (int)(b * 255)};
//	}

	public final double lerp(double v0, double v1, double t) {
		return v0 * (1 - t) + v1 * t;
	}

	// Get time since start in seconds.
	public final double getTime()
	{
		if (overrideTime)
			return currentTime;
		else
			return (double)(System.currentTimeMillis() - startTime) / 1000.0;
	}

	public final float randomFloatRange(float min, float max) {
		return min + RANDOM.nextFloat() * (max - min);
	}
	
	public final void toGif(int id) throws IIOException, IOException {
		
		ImageOutputStream output = new FileImageOutputStream(new File("files\\previmgs\\" + id + ".gif"));
		
		GifSequenceWriter writer = new GifSequenceWriter(output, BufferedImage.TYPE_INT_RGB, 33, true);
		
		this.overrideTime = true;
		
		for (int milliseconds = 0; milliseconds <= 5000; milliseconds += 33) {
			this.currentTime = (double) milliseconds / 1000.0;
			this.reset();
			this.draw();
			byte[] pixels = this.getPixels();
			int size = 23;
			
			BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
			
			// clear the image
			for (int x = 0; x < size; ++x) {
				for (int y = 0; y < size; ++y) {
					image.setRGB(x, y, 0);
				}
			}
			
			// draw pixels into the image.
			for (int row = 0; row < MAX_ROWS; ++row) {
				for (int col = 0; col < MAX_COLS; ++col) {
					int offset  = (row + col * MAX_ROWS) * 3;
					int rgb = ((pixels[offset] & 0xFF) << 16) + ((pixels[offset + 1] & 0xFF) << 8) + (pixels[offset + 2] & 0xFF);
					image.setRGB(col * 2 + 1, row * 2 + 3, rgb);
				}
			}
			
			writer.writeToSequence(image);
		}
		
		this.overrideTime = false;
		
		writer.close();
		output.close();
	}

}
