package emilylights.animation;

import java.io.IOException;

public class StaticImageAnimation extends AnimatedImageAnimation{
	
	public StaticImageAnimation(String image) throws IOException {
		super(new String[] {image}, 1);
	}

}
