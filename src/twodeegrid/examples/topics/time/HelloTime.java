package twodeegrid.examples.topics.time;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;

public class HelloTime {
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(3, 3, "Blink!");
		
		while(true) {
			g.setColorAt(1, 1, CellColor.RED);
			g.waitTime(0.3);
			g.setColorAt(1, 1, CellColor.NONE);
			g.waitTime(0.6);
		}
	}
}
