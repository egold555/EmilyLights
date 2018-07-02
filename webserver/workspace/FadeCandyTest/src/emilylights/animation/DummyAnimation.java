package emilylights.animation;

public class DummyAnimation extends Animation{
	
	@Override
	public void draw() {
		for(int i = 0; i < 7; i++) {
			this.fillCol(i, 255, 255, 255);
		}
	}

}
