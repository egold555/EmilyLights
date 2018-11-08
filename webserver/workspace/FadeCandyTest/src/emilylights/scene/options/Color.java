package emilylights.scene.options;

import java.util.Arrays;

public class Color {

	public static final Color COLOR_RED = new Color(255,0,0);
	public static final Color COLOR_YELLOW = new Color(255,255,0);
	public static final Color COLOR_GREEN = new Color(0,255,0);
	public static final Color COLOR_TURQUOISE = new Color(0,255,255);
	public static final Color COLOR_BLUE = new Color(0,0,255);
	public static final Color COLOR_PURPLE = new Color(255,0,255);
	public static final Color COLOR_WHITE = new Color(255,255,255);
	public static final Color COLOR_BLACK = new Color(0,0,0);
	
	
	public static Color RAINBOW = new Color("#RAINBOW");

	private final String hex;

	public Color(String in) {
		if(in.charAt(0) != '#') {
			in = "#" + in;
		}
		this.hex = in;
	}

	public Color(int r, int g, int b) {
		this(rgbToHex(r, g, b));
	}

	public Color(int[] rgb) {
		this(rgb[0], rgb[1], rgb[2]);
	}

	public Color(float h, float s, float v) {
		this(hsvToRgb(h, s, v));
	}

	public Color(float[] hsv) {
		this(hsv[0], hsv[1], hsv[2]);
	}


	//Getters
	
	public boolean isRainbow() {
		return this.hex.equals("#RAINBOW");
	}
	
	public String getHex() {
		return hex;
	}

	public int[] getRGB() {
		return new int[] {getRed(), getGreen(), getBlue()};
	}

	public int getRed() {
		return Integer.valueOf( hex.substring( 1, 3 ), 16 );
	}

	public int getGreen() {
		return Integer.valueOf( hex.substring( 3, 5 ), 16 );
	}

	public int getBlue() {
		return Integer.valueOf( hex.substring( 5, 7 ), 16 );
	}
	
	public float[] getHSV()
	{
		return java.awt.Color.RGBtoHSB(getRed(), getGreen(), getBlue(), null);
	}
	
	@Override
	public String toString() {
		//return "Color [isRainbow()=" + isRainbow() + ", getHex()=" + getHex() + ", getRGB()=" + Arrays.toString(getRGB()) + "]";
		return "Color[" + Arrays.toString(getRGB()) + "]";
	}

	
	//UTILS
	private static int[] hsvToRgb(float h, float s, float v)
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

	private static String rgbToHex(int r, int g, int b) {
		String rs = Integer.toHexString(r);
		String gs = Integer.toHexString(g);
		String bs = Integer.toHexString(b);

		if (rs.length() == 1)
			rs = "0" + rs;
		if (gs.length() == 1)
			gs = "0" + gs;
		if (bs.length() == 1)
			bs = "0" + bs;
		return "#" + rs + gs + bs;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Color) {
			Color cobj = (Color)obj;
			return cobj.getHex().equals(this.getHex());
		}
		return super.equals(obj);
	}

}
