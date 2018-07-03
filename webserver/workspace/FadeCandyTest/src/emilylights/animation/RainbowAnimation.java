package emilylights.animation;

public class RainbowAnimation extends Animation {
	public enum Direction {LEFT, RIGHT, TOP, BOTTOM, LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM, DIAMOND_IN, DIAMOND_OUT, SQUARE_IN, SQUARE_OUT, CIRCLE_IN, CIRCLE_OUT, SOLID};
	
	public Direction direction = Direction.DIAMOND_OUT;
	public double speed = 2; // higher numbers = faster motion
	public double smoothness = 1.5; // higher numbers = colors change more slowly/smoothly.

	
	@Override
	public void draw() {
		double time = getTime();

        for (int c = 0; c < MAX_COLS; c++) {
            for (int r = 0; r < MAX_ROWS; r++) {
            	double metric = getMetric(r, c);
                float hue = (float) ((metric / (smoothness * 10)) + ((double)time * speed / 10.0));
                int[] rgbColor = hsvToRgb(hue, 1, 1);
                this.setPixel(r, c, rgbColor[0], rgbColor[1], rgbColor[2]);
            }
        }
	}
	
	private double getMetric(int r, int c)
	{
		int rMid = (MAX_ROWS - 1) / 2;
		int cMid = (MAX_COLS - 1) / 2;
		
		switch (direction) {
		case BOTTOM:
			return r;
		case TOP:
			return -r-10;
		case RIGHT:
			return c;
		case LEFT:
			return -c;
		case LEFT_TOP:
			return -r-c;
		case LEFT_BOTTOM:
			return r-c;
		case RIGHT_TOP:
			return c-r;
		case RIGHT_BOTTOM:
			return r+c;
		case SQUARE_IN:
			return Math.max(Math.abs(r - rMid), Math.abs(c - cMid));
		case SQUARE_OUT:
			return Math.max(Math.abs(r - rMid), Math.abs(c - cMid));
		case DIAMOND_IN:
			return Math.abs(r - rMid) + Math.abs(c - cMid);
		case DIAMOND_OUT:
			return - (Math.abs(r - rMid) + Math.abs(c - cMid));
		case CIRCLE_IN:
			return Math.sqrt((r - rMid) * (r - rMid) + (c - cMid) * (c - cMid));
		case CIRCLE_OUT:
			return - Math.sqrt((r - rMid) * (r - rMid) + (c - cMid) * (c - cMid));
		case SOLID:
			return 0;
		default:
			return 0;
		}
	}
}
