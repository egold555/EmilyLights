package emilylights.animation;

import java.io.IOException;

public class AnimationHandler {

	private Animation currentAnimation = new DummyAnimation();
	
	
	public void setAnimation(int an) throws IOException {
		if(an == 0) {
			setAnimation(new DotsAnimation());
		}
		else if(an == 1) {
			setAnimation(new RainbowAnimation());
		}
		else if(an == 2) {
			setAnimation(new CirclesAnimation());
		}
		else if(an == 3) {
			setAnimation(new RainAnimation());
		}
		else if(an == 4) {
			setAnimation(new AnimatedImageAnimation(new String[] {"0", "1"}, 1));
		}
		else {
			setAnimation(currentAnimation = new DummyAnimation());
		}
	}
	
	public void setAnimation(Animation animation) {
		this.currentAnimation = animation;
	}
	
	public Animation getAnimation() {
		return currentAnimation;
	}
	
}
