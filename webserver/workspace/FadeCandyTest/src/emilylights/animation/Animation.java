package emilylights.animation;

import java.util.Random;

public abstract class Animation {

	protected static final Random RANDOM = new Random();
	
	private static long startTime = System.currentTimeMillis();
	
	protected static final int MAX_COLS = 11;
	protected static final int MAX_ROWS = 9;
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
        for (int row = start; row < end; row++) {
            this.setPixel(row, col, red, green, blue);
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
		setPixel(row + col * MAX_ROWS, red, green, blue);
	}
	
	public final void setPixel(int num, int red, int green, int blue) {
		int offset = num * 3;
		pixels[offset] = (byte) red;
		pixels[offset+1] = (byte) green;
		pixels[offset+2] = (byte) blue;
	}
	
	public final void reset()
	{
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
	public final void initInternal() {
		if(first) {
			init();
			first = false;
		}
	}
	
	public final byte[] getPixels() {
		return pixels;
	}
	
	public final  int[] hsvToRgb(float h, float s, float v)
    {
        int i;
        float f, p, q, t;
        float r, g, b;
        if (s == 0) {
            // achromatic (grey)
            r = g = b = v;
        }
        else {
            if (h >= 1)
                h = h % 1;
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

}
