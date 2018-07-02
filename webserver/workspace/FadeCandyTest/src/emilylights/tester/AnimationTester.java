package emilylights.tester;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import emilylights.animation.*;

public class AnimationTester extends JFrame {
    private Animation animation = new RainbowAnimation();
    
	private PixelPanel panel;
	
    public AnimationTester() {

        initUI();
    }

    private void initUI() {
        
        setTitle("Animation tester");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        panel = new PixelPanel();
        this.add(panel);
        
        Timer timer = new Timer(33, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animation.reset();
				animation.draw();
				panel.updateFromAnimation(animation);
			}
        });
        timer.start();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        	AnimationTester ex = new AnimationTester();
            ex.setVisible(true);
        });

    }
    
    class PixelPanel extends JPanel
    {
    	private static final int ROWS = 9;
    	private static final int COLS = 11;
    	
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
    	
    	public void updateFromAnimation(Animation animation)
    	{
    		pixels = animation.getPixels();
    		repaint();
    	}
    }
}

