package emilylights.scene;

import emilylights.Main;

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

			int[] color = hsvToRgb((float)bar/11, 1, 1);
			
			this.setLineCol(bar, 8, test, color[0], color[1], color[2]);
		}
	}

}
