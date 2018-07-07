package emilylights.scene.testing;

import emilylights.scene.Scene;

public class SceneDummy extends Scene{
	
	@Override
	public void draw() {
		setAllPixels(255, 0, 0);
	}

}
