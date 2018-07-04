package emilylights.scene;

import java.io.IOException;

public class SceneHandler {

	private Scene currentAnimation = new SceneDummy();
	
	
	public void setAnimation(int an) throws IOException {
		if(an == 0) {
			setAnimation(new SceneDots());
		}
		else if(an == 1) {
			setAnimation(new SceneRainbow());
		}
		else if(an == 2) {
			setAnimation(new SceneCircles());
		}
		else if(an == 3) {
			setAnimation(new SceneRaindrops());
		}
		else if(an == 4) {
			setAnimation(new SceneAnimatedImage(new String[] {"0", "1"}, 1));
		}
		else {
			setAnimation(currentAnimation = new SceneDummy());
		}
	}
	
	public void setAnimation(Scene animation) {
		this.currentAnimation = animation;
	}
	
	public Scene getAnimation() {
		return currentAnimation;
	}
	
}
