package emilylights.scene.testing;

import emilylights.Main;
import emilylights.scene.Scene;
import emilylights.scene.options.Color;

public class SceneMusicGraph extends Scene {

	@Override
	public void draw() {

		for(int bar = 0; bar < 11; bar++) {

			int[] audioLevel = Main.audio.processAudio(Main.audioPropertiers);

			int test = (audioLevel[0] + audioLevel[1]) / 2;

			test -= 4; //cause of fan

			test/= 10;

			test = 9 - test;
			
			System.out.println("Audio: " + test);
			
			this.setLineCol(bar, 8, test, new Color((float)bar/11, 1, 1));
		}
	}

}
