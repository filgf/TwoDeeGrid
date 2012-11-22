package twodeegrid.examples;


import twodeegrid.CellColor;
import twodeegrid.MouseClick;
import twodeegrid.TwoDeeGrid;

public abstract class HelloGrid {
	
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(5,5, "Click Cells!");
		
		while(true) {
			MouseClick click = g.waitMouseClick();
			g.setColorAt(click.getX(), click.getY(), CellColor.getRandomCellColor());
		}
	}

}
