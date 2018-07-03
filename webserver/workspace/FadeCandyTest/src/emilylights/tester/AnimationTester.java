package emilylights.tester;

import javax.swing.JFrame;

public class AnimationTester extends JFrame {
  
	private static final long serialVersionUID = -273960672007366769L;
    
	public PixelPanel panel;
	
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
    }

}

