package twodeegrid.examples.topics.loops;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;

public class RectangleLoops {
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(10, 10, "Colorful Rectangle!");
		
		// Top line
		for (int x = 1; x <= 8; x++) {
			g.setColorAt(x, 1, CellColor.GREEN);
			g.waitTime(0.2);
		}

		// Bottom line
		int x = 8;
		while (x >= 1) {
			g.setColorAt(x, 8, CellColor.RED);
			x--;
			g.waitTime(0.2);
		}

		// Sides
		int y = 2;
		do {
			g.setColorAt(1, y, CellColor.BLUE);
			g.setColorAt(8, y, CellColor.BLUE);
			y++;
			g.waitTime(0.2);
		} while (y < 8);
	}
}
