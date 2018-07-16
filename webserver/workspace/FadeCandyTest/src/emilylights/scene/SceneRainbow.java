package emilylights.scene;

import emilylights.scene.options.Color;
import emilylights.scene.options.SceneOptions;

public class SceneRainbow extends Scene {
	public enum Direction {LEFT, RIGHT, TOP, BOTTOM, LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM, DIAMOND_IN, DIAMOND_OUT, SQUARE_IN, SQUARE_OUT, CIRCLE_IN, CIRCLE_OUT, SOLID};

	public Direction direction = Direction.LEFT;
	public double speed = 0.5; // higher numbers = faster motion
	public double smoothness = 2.0; // higher numbers = colors change more slowly/smoothly.
	public Color[] colors = new Color[0];

	@Override
	public void setOptions(SceneOptions options) {
		if(options.customOptions.containsKey("direction")) {
			this.direction = Direction.valueOf((String) options.customOptions.get("direction"));
		}
		if(options.customOptions.containsKey("speed")) {
			this.speed = Double.valueOf(options.customOptions.get("speed").toString());
		}
		if(options.customOptions.containsKey("smoothness")) {
			this.smoothness = Double.valueOf(options.customOptions.get("smoothness").toString());
		}
		if (options.colors != null && options.colors.length > 0) {
			this.colors = options.colors;
		}
	}


	@Override
	public void draw() {
		double time = getTime();

		for (int c = 0; c < MAX_COLS; c++) {
			for (int r = 0; r < MAX_ROWS; r++) {
				double metric = getMetric(r, c);
				double h = ((metric / (smoothness * 10)) + ((double)time * speed / 10.0));
				if (h >= 1 || h <= -1)
					h = h % 1;
				if (h < 0)
					h += 1;
				
				Color color;
				
				if (colors.length == 0) {
					color = new Color((float)h, 1.0F, 1.0F);					
				}
				else if (colors.length == 1) {
					float[] hsvBase = colors[0].getHSV();
					float[] hsv = new float[3];
					hsv[0] = hsvBase[0];
					hsv[1] = hsvBase[1];
					if (h < 0.5) {
						hsv[2] = (float) lerp(0, hsvBase[2], h * 2);
					}
					else {
						hsv[2] = (float) lerp(hsvBase[2], 0, (h - 0.5) * 2);
					}
					color = new Color(hsv[0], hsv[1], hsv[2]);
				}
				else {
					int index1 = (int)(h * colors.length);
					int index2 = (index1 + 1) % colors.length;
					float[] hsvBase1 = colors[index1].getHSV();
					float[] hsvBase2 = colors[index2].getHSV();
					float[] hsv = new float[3];
					double hh = (h * colors.length) % 1.0;
					hsv[0] = (float) lerp(hsvBase1[0], hsvBase2[0], hh);
					hsv[1] = (float) lerp(hsvBase1[1], hsvBase2[1], hh);
					hsv[2] = (float) lerp(hsvBase1[2], hsvBase2[2], hh);
					color = new Color(hsv[0], hsv[1], hsv[2]);
				}

				this.setPixel(r, c, color);
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
