package emilylights.scene.testing;

import emilylights.scene.Scene;
import emilylights.scene.options.Color;

public class SceneDummy extends Scene{
	
	@Override
	public void draw() {
		setAllPixels(new Color(255, 0, 0));
		fillCol(0, new Color(255, 255, 255));
		fillRow(0, new Color(255, 255, 255));
	}

}
