package emilylights.tester;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import emilylights.animation.Animation;

public class AnimationTester extends JFrame {
  
	private static final long serialVersionUID = -273960672007366769L;

	private Animation animation;
    
	private PixelPanel panel;
	
    public AnimationTester(Animation animation) {
    	this.animation = animation;
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

       

    }
    

}

