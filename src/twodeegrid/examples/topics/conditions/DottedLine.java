package twodeegrid.examples.topics.conditions;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;

public class DottedLine {
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(80, 1, "Italia!");
		
		for (int x = 0; x < 80; x++) {
			switch (x % 3) {
			case 0:
				g.setColorAt(x, 0, CellColor.RED);
				break;
			case 1:
				g.setColorAt(x, 0, CellColor.GREEN);
				break;
			default:
				break;
			}
		}
	}
}
