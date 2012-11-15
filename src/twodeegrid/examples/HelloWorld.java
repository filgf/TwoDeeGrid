package twodeegrid.examples;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;

public class HelloWorld {
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(3, 3, "Hello World");
		g.setColorAt(1, 1, CellColor.RED);
	}
}