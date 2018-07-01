package emilylights.animation;

public class DummyAnimation extends Animation{

	@Override
	public void draw() {
		setAllPixels(255, 0, 0);
		fillCol(5, 0, 0, 255);
		fillRow(5, 0, 255, 0);
	}

}
