package emilylights.animation;

import emilylights.opc.Animation;

public class RainbowAnimation extends Animation {

	@Override
	public void draw() {
		double time = getTime();

        for (int c = 0; c < MAX_COLS; c++) {
            for (int r = 0; r < MAX_ROWS; r++) {
                float hue = (float) (((double)r / 10) + ((double)time / 10));
                int[] rgbColor = hsvToRgb(hue, 1, 1);
                this.setPixel(r, c, rgbColor[0], rgbColor[1], rgbColor[2]);
            }
        }
	}
}
