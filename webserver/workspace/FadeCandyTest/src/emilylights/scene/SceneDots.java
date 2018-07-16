package emilylights.scene;

import emilylights.scene.options.Color;
import emilylights.scene.options.SceneOptions;

public class SceneDots extends Scene {

	private float advance = 0.02f;
	private float hue = 0f;
	private float hueAdvance = 0.0001f;
	
	private Color[] colors = new Color[0];
	
	private DotData[] dotArray = new DotData[PIXEL_COUNT];
	
	public SceneDots() {
		for(int i = 0; i < PIXEL_COUNT; i++) {
			dotArray[i] = new DotData();
		}
	}
	
	@Override
	public void setOptions(SceneOptions options) {
		if(options.customOptions.containsKey("advance")) {
			this.advance = Float.valueOf(options.customOptions.get("advance").toString());
		}
		if(options.customOptions.containsKey("colorAdvance")) {
			this.hueAdvance = Float.valueOf(options.customOptions.get("colorAdvance").toString());
		}
		if (options.colors != null && options.colors.length >= 1) {
			this.colors = options.colors;
			hueAdvance = 0;
		}
	}
	
	@Override
	public void draw() {
		int colorIndex = 0;
		for(int i = 0; i < PIXEL_COUNT; i++) {
			DotData dd = dotArray[i];
			float lightValue = (float)lerp(dd.start, dd.end, dd.current);
			
			Color color;
			if (colors.length == 0) {
				color = new Color(hue, 1, lightValue);
			}
			else {
				float[] hsv = colors[colorIndex % colors.length].getHSV();
				color = new Color(hsv[0], hsv[1], lightValue);
			}
			
			setPixel(i, color);
			dd.current += advance;
			if (dd.current > 1) {
				dd.start = dd.end;
				dd.end = RANDOM.nextFloat();
				dd.current = 0;
            }
			
			colorIndex += 1;
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
