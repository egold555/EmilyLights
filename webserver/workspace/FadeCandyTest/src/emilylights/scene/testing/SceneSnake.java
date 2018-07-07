package emilylights.scene.testing;

import java.util.ArrayList;
import java.util.List;

import emilylights.scene.Scene;

public class SceneSnake extends Scene {

	private long start;
	List<Trail> trail = new ArrayList<Trail>();
	int tail = 5;

	int px = 1, py = 1;
	
	int ax = 5, ay = 5;


	@Override
	public void init() {
		start = System.currentTimeMillis();
	}

	@Override
	public void draw() {

		long elapsedTime = System.currentTimeMillis() - this.start;

		if (elapsedTime > 200) {

			start = System.currentTimeMillis();


			//do screenwrap stuff

			//Drawing snake code
			for(Trail t : trail) {
				setPixel(t.x, t.y, 0, 255, 0);
				if(t.x == px && t.y == py) {
					tail = 5;
					//player died
				}
			}
			trail.add(new Trail(px, py)); //add every time

			if(ax == px && ay == py) {
				//player touched apple
				tail++;
				ax = RANDOM.nextInt(MAX_ROWS);
				ay = RANDOM.nextInt(MAX_COLS);
			}
			
			setPixel(ax, ay, 255, 0, 0);
			
		}

	}

	private class Trail {

		public Trail(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int x;
		public int y;

	}
}
