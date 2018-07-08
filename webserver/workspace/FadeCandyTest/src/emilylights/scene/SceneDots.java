package emilylights.scene;

import emilylights.scene.options.Color;

public class SceneDots extends Scene {

	private static final float advance = 0.02f;
	private float hue = 0f;
	private static final float hueAdvance = 0.0001f;
	
	private DotData[] dotArray = new DotData[PIXEL_COUNT];
	
	public SceneDots() {
		for(int i = 0; i < PIXEL_COUNT; i++) {
			dotArray[i] = new DotData();
		}
	}
	
	@Override
	public void draw() {
		for(int i = 0; i < PIXEL_COUNT; i++) {
			DotData dd = dotArray[i];
			float lightValue = (float)lerp(dd.start, dd.end, dd.current);
			setPixel(i, new Color(hue, 1, lightValue));
			dd.current += advance;
			if (dd.current > 1) {
				dd.start = dd.end;
				dd.end = RANDOM.nextFloat();
				dd.current = 0;
            }
		}
		hue += hueAdvance;
	}
	
	private class DotData {
		
		public DotData() {
			start = RANDOM.nextFloat();
			end = RANDOM.nextFloat();
			current = RANDOM.nextFloat();
		}
		
		public float start;
		public float end; 
		public float current;
	}

}
