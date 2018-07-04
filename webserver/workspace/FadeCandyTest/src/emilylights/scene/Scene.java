package emilylights.scene;

import java.util.Random;

public abstract class Scene {

	protected static final Random RANDOM = new Random();

	private long startTime = System.currentTimeMillis();

	public static final int MAX_COLS = 11;
	public static final int MAX_ROWS = 9;
	public static final int PIXEL_COUNT = MAX_ROWS * MAX_COLS;

	private byte[] pixels = null;

	public final void fillCol(int col, int red, int green, int blue) {
		fillRect(0, col, MAX_ROWS - 1, col, red, green, blue);
	}

	public final void fillRow(int row, int red, int green, int blue) {
		fillRect(row, 0, row,  MAX_COLS - 1, red, green, blue);
	}

	public final void setAllPixels(int red, int green, int blue) {
		fillRect(0, 0, MAX_ROWS-1, MAX_COLS-1, red, green, blue);
	}

	public final void setLineRow(int row, int start, int end, int red, int green, int blue) {
		for (int col = start; col < end; col++) {
			this.setPixel(row, col, red, green, blue);
		}
	}

	public final void setLineCol(int col, int start, int end, int red, int green, int blue) {
		if (start <= end) {
			for (int row = start; row <= end; row++) {
				this.setPixel(row, col, red, green, blue);
			}
		} else {
			for (int row = end; row <= start; row++) {
				this.setPixel(row, col, red, green, blue);
			}
		}

	}

	public final void fillRect(int firstRow, int firstCol, int lastRow, int lastCol, int red, int green, int blue) {
		for(int row = firstRow; row <= lastRow; row++) {
			for(int col = firstCol; col <= lastCol; col++) {
				setPixel(row, col, red, green, blue);
			}
		}
	}

	public final void setPixel(int row, int col, int red, int green, int blue) {
		if (row < 0 || row >= MAX_ROWS || col < 0 || col >= MAX_COLS)
			return;
		setPixel(row + col * MAX_ROWS, red, green, blue);
	}

	public final void setPixel(int row, int col, int[] colors)
	{
		setPixel(row, col, colors[0], colors[1], colors[2]);
	}

	public final void setPixel(int num, int[] colors) {
		setPixel(num, colors[0], colors[1], colors[2]);
	}

	public final void setPixel(int num, int red, int green, int blue) {
		if (num < 0 || num >= PIXEL_COUNT) {
			return;
		}

		int offset = num * 3;
		pixels[offset] = (byte) red;
		pixels[offset+1] = (byte) green;
		pixels[offset+2] = (byte) blue;
	}


	public final void addPixel(int row, int col, int red, int green, int blue) {
		if (row < 0 || row >= MAX_ROWS || col < 0 || col >= MAX_COLS)
			return;
		addPixel(row + col * MAX_ROWS, red, green, blue);
	}

	public final void addPixel(int row, int col, int[] colors)
	{
		addPixel(row, col, colors[0], colors[1], colors[2]);
	}

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
			addPixel((int) row, (int) col, red, green, blue);
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

	public final void addPixelAntiAlias(double row, double col, int[] colors)
	{
		addPixelAntiAlias(row, col, colors[0], colors[1], colors[2]);
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

	private boolean first = true;

	public final byte[] getPixels() {
		return pixels;
	}

	public final int[] hsvToRgb(float h, float s, float v)
	{
		int i;
		float f, p, q, t;
		float r, g, b;
		if (s == 0) {
			// achromatic (grey)
			r = g = b = v;
		}
		else {
			if (h >= 1 || h <= -1)
				h = h % 1;
			if (h < 0)
				h += 1;
			h *= 6;         // sector 0 to 5
			i = (int)Math.floor(h);
			f = h - i;          // factorial part of h
			p = v * (1 - s);
			q = v * (1 - s * f);
			t = v * (1 - s * (1 - f));
			switch (i) {
			case 0:
				r = v;
				g = t;
				b = p;
				break;
			case 1:
				r = q;
				g = v;
				b = p;
				break;
			case 2:
				r = p;
				g = v;
				b = t;
				break;
			case 3:
				r = p;
				g = q;
				b = v;
				break;
			case 4:
				r = t;
				g = p;
				b = v;
				break;
			default:        // case 5:
				r = v;
				g = p;
				b = q;
				break;
			}
		}

		return new int[] {(int)Math.round(r * 255.0), (int)Math.round(g * 255.0), (int)Math.round(b * 255.0)};
	}

	public final int[] rgbToInt(float r, float g, float b) {
		return new int[] {(int)(r * 255), (int)(g * 255), (int)(b * 255)};
	}

	public final double lerp(double v0, double v1, double t) {
		return v0 * (1 - t) + v1 * t;
	}

	// Get time since start in seconds.
	public final double getTime()
	{
		return (double)(System.currentTimeMillis() - startTime) / 1000.0;
	}

	public final float randomFloatRange(float min, float max) {
		return min + RANDOM.nextFloat() * (max - min);
	}

}