package emilylights.animation;

public class AnimationHandler {

	private Animation currentAnimation = new DummyAnimation();
	
	
	public void setAnimation(int an) {
		if(an == 0) {
			currentAnimation = new DotsAnimation();
		}
		else if(an == 1) {
			currentAnimation = new RainbowAnimation();
		}
		else {
			currentAnimation = new DummyAnimation();
		}
	}
	
	public Animation getAnimation() {
		return currentAnimation;
	}
	
}
