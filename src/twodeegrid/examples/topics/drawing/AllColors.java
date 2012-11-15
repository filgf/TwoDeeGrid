package twodeegrid.examples.topics.drawing;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;

public class AllColors {
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(1,CellColor.values().length, "Colors");
		
		int i = 0;
		
		for (CellColor c : CellColor.values()) {
			g.setColorAt(0, i, c);
			i++;
		}
		
		
	}

}
