package emilylights.scene.testing;

import java.io.IOException;

public class SceneStaticImage extends SceneAnimatedImage{
	
	public SceneStaticImage(String image) throws IOException {
		super(new String[] {image}, 1);
	}

}
