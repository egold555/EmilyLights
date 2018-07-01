package emilylights.animation;

public class AnimationHandler {

	private Animation currentAnimation = new DummyAnimation();
	
	
	public void setAnimation(int an) {
		if(an == 0) {
			setAnimation(new DotsAnimation());
		}
		else if(an == 1) {
			setAnimation(new RainbowAnimation());
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
