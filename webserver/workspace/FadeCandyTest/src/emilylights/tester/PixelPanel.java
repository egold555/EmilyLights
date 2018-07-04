package emilylights.tester;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import emilylights.scene.Scene;

public class PixelPanel extends JPanel{

	private static final long serialVersionUID = -2167633649304409566L;
	
	private static final int ROWS = Scene.MAX_ROWS;
	private static final int COLS = Scene.MAX_COLS;

	byte[] pixels;

	@Override
	public void paintComponent(Graphics g)
	{
		int width = getWidth();
		int height = getHeight();

		g.setColor(Color.black);
		g.fillRect(0,  0, width,  height);

		if (pixels != null) {
			for (int row = 0; row < ROWS; ++row) {
				for (int col = 0; col < COLS; ++col) {
					int offset  = (row + col * ROWS) * 3;
					drawLight(g, row, col, new Color(pixels[offset] & 0xFF, pixels[offset + 1] & 0xFF, pixels[offset + 2] & 0xFF));
				}
			}
		}
	}

	private void drawLight(Graphics g, int row, int col, Color color)
	{
		int width = getWidth();
		int height = getHeight();
		int lightSpacingWidth = width / (COLS + 1);
		int lightSpacingHeight = height / (ROWS + 1);

		g.setColor(color);
		g.fillOval((1 + col) * lightSpacingWidth, (1 + row) * lightSpacingHeight, lightSpacingWidth / 5, lightSpacingHeight / 5);
	}

	public void updateFromAnimation(Scene animation)
	{
		pixels = animation.getPixels();
		repaint();
	}
}
