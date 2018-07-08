package emilylights.scene;

import java.util.ArrayList;
import java.util.List;

import emilylights.scene.options.Color;

public class SceneCircles extends Scene {
	private static final double WIDTH = 1.1F;
	private static final double SPEED = 0.07F;
	private static final double DROP_MIN_TIME = 2.5;
	private static final double DROP_MAX_TIME = 8.0;
	
	
	private List<Drop> drops = new ArrayList<Drop>();
	private double nextDrop = getTime();
	
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
			newDrop.hue = (float) RANDOM.nextDouble();
			newDrop.saturation = (float) Math.sqrt(RANDOM.nextDouble());
			drops.add(newDrop);
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
