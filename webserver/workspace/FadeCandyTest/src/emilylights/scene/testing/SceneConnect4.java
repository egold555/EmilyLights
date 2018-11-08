package emilylights.scene.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import emilylights.scene.Scene;
import emilylights.scene.options.Color;

public class SceneConnect4 extends Scene {

	private static final int PLAYER_1 = 0;
	private static final int PLAYER_2 = 1;
	
	private static final Color COLOR_BORDER = Color.COLOR_YELLOW;
	private static final Color COLOR_PLAYER_1 = Color.COLOR_RED;
	private static final Color COLOR_PLAYER_2 = Color.COLOR_BLUE;
	
	private List<Play> plays = new ArrayList<Play>();
	
	@Override
	public void draw() {
		
		fillCol(0, COLOR_BORDER);
		fillCol(10, COLOR_BORDER);
		fillRow(0, COLOR_BORDER);
		fillRow(8, COLOR_BORDER);
		
		
//		int col = 1;
//		int lastEmpty = getLastEmptyCell(col);
//		setPixel(lastEmpty, col, COLOR_PLAYER_2);
//		lastEmpty = getLastEmptyCell(col);
//		setPixel(lastEmpty, col, COLOR_PLAYER_2);
		
		for(Play play : plays) {
			play.render(this);
		}
		
		System.out.println("Won: " + hasWon());
		
	}
	
	@Override
	public void onRecieveData(String body, String[] urlParts) {
		System.out.println("Connect4: Got request " + Arrays.asList(urlParts));
		
		List<String> url = new ArrayList<String>(Arrays.asList(urlParts));
		url.remove(0);
		
		if(url.size() != 2) {
			return;
		}
		
		int player = Integer.parseInt(url.get(0));
		int col = Integer.parseInt(url.get(1));
		
		System.out.println("Drop: " + col + " for player " + player);
		
		int lastEmpty = getLastEmptyCell(col);
		
		plays.add(new Play(lastEmpty, col, getColorForPlayer(player)));
		
	}
	
	private Color getColorForPlayer(int player) {
		if(player > PLAYER_2) {
			return Color.COLOR_GREEN; //debug
		}
		return player == PLAYER_1 ? COLOR_PLAYER_1 : COLOR_PLAYER_2;
	}
	
	private int getPlayerForColor(Color color) {
		if(color.equals(COLOR_PLAYER_1)) {
			return PLAYER_1;
		} else if(color.equals(COLOR_PLAYER_2)) {
			return PLAYER_2;
		}
		return -1;
	}
	
	private int getLastEmptyCell(int col) {
		
		if(col == 0 || col == MAX_COLS) {
			return -1;
		}
		
		int maxRow = Scene.MAX_ROWS;
		for(int row = 1; row < maxRow; row++) {
			Color color = getPixel(row, col);
			//System.out.println(row + "x" + col + " " + color.toString());
			if(!(color.equals(Color.COLOR_BLACK))) {
				
				return row-1;
			}
		}
		
		return -2;
		
	}
	
	private int hasWon() {
		
		//Check all rows if 4 in row
		for(int col = 0; col < MAX_COLS-3; col++) {
			for(int row = 0; row < MAX_ROWS; row++) {
				Color c1 = getPixel(row, col);
				Color c2 = getPixel(row, col+1);
				Color c3 = getPixel(row, col+2);
				Color c4 = getPixel(row, col+3);

				if((c1.equals(COLOR_PLAYER_1) || c1.equals(COLOR_PLAYER_2)) && c1.equals(c2) && c2.equals(c3) && c3.equals(c4)) {
					//Winner winner!
					return getPlayerForColor(c1);
				}
			}
		}
		//check all cols if 4 in a row
		for(int col = 0; col < MAX_COLS; col++) {
			for(int row = 0; row < MAX_ROWS-3; row++) {
				Color c1 = getPixel(row, col);
				Color c2 = getPixel(row+1, col);
				Color c3 = getPixel(row+2, col);
				Color c4 = getPixel(row+3, col);

				if((c1.equals(COLOR_PLAYER_1) || c1.equals(COLOR_PLAYER_2)) && c1.equals(c2) && c2.equals(c3) && c3.equals(c4)) {
					//Winner winner!
					return getPlayerForColor(c1);
				}
			}
		}
		
		//Check diag 1
		for(int col = 0; col < MAX_COLS-3; col++) {
			for(int row = 0; row < MAX_ROWS-3; row++) {
				Color c1 = getPixel(row, col);
				Color c2 = getPixel(row+1, col+1);
				Color c3 = getPixel(row+2, col+2);
				Color c4 = getPixel(row+3, col+3);

				if((c1.equals(COLOR_PLAYER_1) || c1.equals(COLOR_PLAYER_2)) && c1.equals(c2) && c2.equals(c3) && c3.equals(c4)) {
					//Winner winner!
					return getPlayerForColor(c1);
				}
			}
		}
		
		//check diag 2
		for(int col = 0; col < MAX_COLS-3; col++) {
			for(int row = 3; row < MAX_ROWS; row++) {
				Color c1 = getPixel(row, col);
				Color c2 = getPixel(row-1, col+1);
				Color c3 = getPixel(row-2, col+2);
				Color c4 = getPixel(row-3, col+3);

				if((c1.equals(COLOR_PLAYER_1) || c1.equals(COLOR_PLAYER_2)) && c1.equals(c2) && c2.equals(c3) && c3.equals(c4)) {
					//Winner winner!
					return getPlayerForColor(c1);
				}
			}
		}
		
		return -1;
	}
	
	private class Play {
		private final int row;
		private final int col;
		private final Color color;
		
		public Play(int row, int col, Color color) {
			this.row = row;
			this.col = col;
			this.color = color;
		}
		
		public void render(Scene scene) {
			scene.setPixel(row, col, color);
		}
	}
	
	

}
