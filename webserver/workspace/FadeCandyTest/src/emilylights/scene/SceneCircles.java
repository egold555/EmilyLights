package emilylights.scene;

import java.util.ArrayList;
import java.util.List;

import emilylights.scene.options.Color;
import emilylights.scene.options.SceneOptions;

public class SceneCircles extends Scene {
	private double WIDTH = 1.1F;
	private double SPEED = 0.07F;
	private double DROP_MIN_TIME = 2.5;
	private double DROP_MAX_TIME = 8.0;
	
	
	private List<Drop> drops = new ArrayList<Drop>();
	private double nextDrop = getTime();
	
	private Color[] colors = new Color[0];
	private int colorIndex = 0;
	
	@Override
	public void setOptions(SceneOptions options) {
		if(options.customOptions.containsKey("width")) {
			this.WIDTH = Double.valueOf(options.customOptions.get("width").toString());
		}
		if(options.customOptions.containsKey("speed")) {
			this.SPEED = Double.valueOf(options.customOptions.get("speed").toString());
		}
		if(options.customOptions.containsKey("dropMinTime")) {
			this.DROP_MIN_TIME = Double.valueOf(options.customOptions.get("dropMinTime").toString());
		}
		if(options.customOptions.containsKey("dropMaxTime")) {
			this.DROP_MAX_TIME = Double.valueOf(options.customOptions.get("dropMaxTime").toString());
		}
		if (options.colors != null && options.colors.length > 0) {
			this.colors = options.colors;
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
		for (int row = 0; row < MAX_ROWS; ++row) {
			for (int col = 0; col < MAX_COLS; ++col) {
				for (Drop drop: drops) {
					double distance = Math.sqrt((row - drop.row) * (row - drop.row) + (col - drop.column) * (col - drop.column));
					double dsq = (distance - drop.radius) * (distance - drop.radius);
					float value = (float) Math.exp(- dsq / WIDTH);
					addPixel(row, col, new Color(drop.hue, drop.saturation, value));
				}
			}
		}
	}

	private void advanceDrops() {
		for (Drop drop: drops) {
			drop.radius += SPEED;
		}
		for (int i = drops.size() - 1; i >= 0; --i) {
			if (drops.get(i).radius > 2 * (MAX_ROWS + MAX_COLS)) {
				drops.remove(i);
			}
		}
	}
	
	private void addDrop()
	{
		if (getTime() > nextDrop) {
			nextDrop = getTime() + lerp(DROP_MIN_TIME, DROP_MAX_TIME, RANDOM.nextDouble());
			Drop newDrop = new Drop();
			newDrop.column = lerp(2, MAX_COLS - 3, RANDOM.nextDouble());
			newDrop.row = lerp(2, MAX_ROWS - 3, RANDOM.nextDouble());
			newDrop.radius = 0;
			
			if(colors.length == 0) {
				newDrop.hue = (float) RANDOM.nextDouble();
				newDrop.saturation = (float) Math.sqrt(RANDOM.nextDouble());
			}
			else {
				int index ;
				if (colors.length == 2)
					index = colorIndex % 2;
				else
					index = RANDOM.nextInt(colors.length);
					
				Color color = colors[index];
				float[] hsv = color.getHSV();
				newDrop.hue = hsv[0];
				newDrop.saturation = hsv[1];
			}
			drops.add(newDrop);
			++colorIndex;
			return;
		}
	}
	
	private class Drop {
		public double column;
		public double row;
		public double radius;
		public float hue, saturation;
	}
}
