package emilylights.scene;

import java.util.ArrayList;
import java.util.List;

import emilylights.scene.options.Color;
import emilylights.scene.options.SceneOptions;

public class SceneRaindrops extends Scene {
	
	private int DROP_LEN = 7;
	private float DROP_VALUESTART = 0.6F;
	private double DROP_MIN_TIME = 0.3;
	private double DROP_MAX_TIME = 1.0;
	private double SPEED = 10;
	
	
	private List<Drop> drops = new ArrayList<Drop>();
	private double lastAdvance = getTime();
	private double nextDrop = getTime();
	
	@Override
	public void setOptions(SceneOptions options) {
		if(options.customOptions.containsKey("dropLength")) {
			this.DROP_LEN = Integer.valueOf(options.customOptions.get("dropLength").toString());
		}
		if(options.customOptions.containsKey("dropValueStart")) {
			this.DROP_VALUESTART = Float.valueOf(options.customOptions.get("dropValueStart").toString());
		}
		if(options.customOptions.containsKey("dropMinTime")) {
			this.DROP_MIN_TIME = Double.valueOf(options.customOptions.get("dropMinTime").toString());
		}
		if(options.customOptions.containsKey("dropMaxTime")) {
			this.DROP_MAX_TIME = Double.valueOf(options.customOptions.get("dropMaxTime").toString());
		}
		if(options.customOptions.containsKey("speed")) {
			this.SPEED = Double.valueOf(options.customOptions.get("speed").toString());
		}
	}
	
	@Override
	public void draw() {
		drawDrops();
		advanceDrops();
		addDrop();
	}
	
	private void drawDrops()
	{
		for (Drop drop: drops) {
			addPixelAntiAlias(drop.row, drop.column, new Color(drop.hue, drop.saturation, 1));
			for (int i = 1; i <= DROP_LEN; ++i) {
				if (drop.row- i >= -1) {
					addPixelAntiAlias(drop.row - i, drop.column, new Color(drop.hue, drop.saturation, (float) lerp(DROP_VALUESTART, 0, (float) i / DROP_LEN)));
				}
			}
		}
	}
	
	private void advanceDrops() {
		for (Drop drop: drops) {
			drop.row += (getTime() - lastAdvance) * SPEED;
		}
		for (int i = drops.size() - 1; i >= 0; --i) {
			if (drops.get(i).row > MAX_ROWS + DROP_LEN) {
				drops.remove(i);
			}
		}
		lastAdvance = getTime();
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
		public double column;
		public double row;
		public float saturation = RANDOM.nextFloat();
		public float hue = randomFloatRange(0.5f, 0.6f);
	}
}
