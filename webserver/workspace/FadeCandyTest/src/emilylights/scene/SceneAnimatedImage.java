package emilylights.scene;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class SceneAnimatedImage extends Scene{

	List<BufferedImage> img = new ArrayList<BufferedImage>();
	
	int currentFrame = 0;
	double startTime = getTime();
	private final double frameTime;
	
	public SceneAnimatedImage(String[] image, double frameTime) throws IOException {
		this.frameTime = frameTime;
		for(int i = 0; i < image.length; i++) {
			img.add(ImageIO.read(new File("files\\" + image[i] + ".png")));
		}
	}
	
	@Override
	public void draw() {
		double elapsedTime = getTime() - startTime;
		currentFrame = (int)(elapsedTime / frameTime) % img.size();
		
		if(img.get(currentFrame) != null) {
			for(int row = 0; row < MAX_ROWS; row++) {
				for(int col = 0; col < MAX_COLS; col++) {
					int[] rgb = getRGB(col, row);
					setPixel(row, col, rgb[0], rgb[1], rgb[2]);
				}
			}
		}
	}
	
	private int[] getRGB(int x, int y) {
		int rgb = img.get(currentFrame).getRGB(x, y);
		int blue = rgb & 0xFF;
		int green = (rgb >> 8) & 0xFF;
		int red = (rgb >> 16) & 0xFF;
		return new int[] {red, green, blue};
	}
	
}
