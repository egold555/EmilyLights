package emilylights.animation;

public class DummyAnimation extends Animation{
	
	@Override
	public void draw() {
		int i = 224;
		this.fillRect(0, 0, MAX_ROWS-1, MAX_COLS-1, i, i, i);
	}

}
