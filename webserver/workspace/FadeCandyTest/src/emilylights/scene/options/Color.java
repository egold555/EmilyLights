package emilylights.scene.options;

public class Color {

	public static Color RAINBOW = new Color(-1, -1, -1);
	
	private final int red;
	private final int green;
	private final int blue;
	
	public Color(String hex) {
		this.red = Integer.valueOf( hex.substring( 1, 3 ), 16 );
		this.green = Integer.valueOf( hex.substring( 3, 5 ), 16 );
		this.blue = Integer.valueOf( hex.substring( 5, 7 ), 16 );
	}
	
	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	@Override
	public String toString() {
		return "Color [red=" + red + ", green=" + green + ", blue=" + blue + "]";
	}
	
	
	
}
