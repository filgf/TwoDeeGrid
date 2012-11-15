package twodeegrid.examples.topics.input;

import java.awt.event.KeyEvent;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;
import twodeegrid.examples.games.tron.TronGame;

public class MoveLeftRight {
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(9, 1, "Press LEFT/RIGHT");
		int pos = 4; 
		int key;

		while (true) {
			g.clear();
			g.setColorAt(pos, 0, CellColor.GREEN);
			
			do {
				key = g.getKeyPressed();
			} while (key == KeyEvent.CHAR_UNDEFINED);
			
			switch (key) {
			case KeyEvent.VK_LEFT:
				pos--;
				break;
			case KeyEvent.VK_RIGHT:
				pos++;
				break;
			}
		}
		
		
	}

}
