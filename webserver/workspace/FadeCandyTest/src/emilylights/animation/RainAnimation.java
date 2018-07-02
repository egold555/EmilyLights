package emilylights.animation;

import java.util.ArrayList;
import java.util.List;

public class RainAnimation extends Animation {
	private float hue = 0;
	private float saturation = 0;
	
	private static final int DROP_LEN = 7;
	private static final float DROP_VALUESTART = 0.6F;
	private static final double ADVANCE_TIME = 0.2;
	private static final double DROP_MIN_TIME = 0.6;
	private static final double DROP_MAX_TIME = 3.0;
	
	
	private List<Drop> drops = new ArrayList<Drop>();
	private double lastAdvance = getTime();
	private double nextDrop = getTime();
	
	@Override
	public void draw() {
		drawDrops();
		advanceDrops();
		addDrop();
	}
	
	private void drawDrops()
	{
		for (Drop drop: drops) {
			int[] colors = this.hsvToRgb(hue, saturation, 1);
			setPixel(drop.row, drop.column, colors);
			for (int i = 1; i <= DROP_LEN; ++i) {
				int[] c = this.hsvToRgb(hue, saturation, (float) lerp(DROP_VALUESTART, 0, (float) i / DROP_LEN));
				if (drop.row- i >= 0) {
				    setPixel(drop.row - i, drop.column, c);
				}
			}
		}
	}
	
	private void advanceDrops() {
		if (getTime() > lastAdvance + ADVANCE_TIME) {
			for (Drop drop: drops) {
				drop.row += 1;
			}
			for (int i = drops.size() - 1; i >= 0; --i) {
				if (drops.get(i).row > MAX_ROWS + DROP_LEN) {
					drops.remove(i);
				}
			}
			lastAdvance = getTime();
		}
	}
	
	private void addDrop()
	{
		if (getTime() > nextDrop) {
			nextDrop = getTime() + lerp(DROP_MIN_TIME, DROP_MAX_TIME, RANDOM.nextDouble());
			for (int i = 0; i < 20; ++i) {
				int col = RANDOM.nextInt(MAX_COLS);
				boolean exists = false;
				for (Drop drop: drops) {
					if (drop.column == col)
						exists = true;
				}
				if (! exists) {
					Drop newDrop = new Drop();
					newDrop.column = col;
					newDrop.row = 0;
					drops.add(newDrop);
					return;
				}
			}
		}
	}
	
	

	private class Drop {
		public int column;
		public int row;
		public long startTime;
	}
}
