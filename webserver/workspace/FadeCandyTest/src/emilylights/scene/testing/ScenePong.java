package emilylights.scene.testing;

import emilylights.scene.Scene;

public class ScenePong extends Scene {

	int paddle1Y = 0, paddle1Direction = 1;
	int paddle2Y = 0, paddle2Direction = 1;
    long start;
    
    int ballX = 2, ballY = 2, ballVelX = 1, ballVelY = 1;
    
    boolean hit = false;

	@Override
	public void init() {
		start = System.currentTimeMillis();
	}

	@Override
	public void draw() {

		long elapsedTime = System.currentTimeMillis() - this.start;
		
		if (elapsedTime > 200) {

			start = System.currentTimeMillis();
			
			hit = false;
			
			if (ballY <= 0) {
				ballVelY = -ballVelY;
				
			}
			
			if (ballY >= MAX_ROWS-1) { 
				ballVelY = -ballVelY;
				
			}
			
			if (ballX <= 0) {
				ballVelX = -ballVelX;
				hit = true;
			}
			
			if (ballX >= MAX_COLS-1) { 
				ballVelX = -ballVelX;
				hit = true;
			}
			
			ballX += ballVelX;
			ballY += ballVelY;
			
			
			int delta = ballY - (paddle1Y + 1);
			if (delta > 0) {
				paddle1Y += (paddle1Y < MAX_ROWS - 1) ? 1 : 0;
			}
			else if (delta < 0) {
				paddle1Y -= (paddle1Y > 0) ? 1 : 0;
			}
			
			if(this.paddle1Y > 6) {
				paddle1Y = 6;
			}
			else if (this.paddle1Y <= 0) {
				paddle1Y = 0;
			}
			
			
		}

		
		
		this.setLineCol(0, this.paddle1Y, this.paddle1Y + 3, 255, 0, 0); //draw paddle Left
		this.setLineCol(10, this.paddle1Y, this.paddle1Y + 3, 0, 0, 255); //draw paddle Left
		
		this.setPixel(ballY, ballX, hit ? 0 : 255,  255, hit ? 0 : 255);
		
	}

}
