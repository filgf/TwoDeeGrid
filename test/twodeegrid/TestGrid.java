package twodeegrid;


import twodeegrid.CellColor;
import twodeegrid.MouseClick;
import twodeegrid.TwoDeeGrid;

public abstract class TestGrid {
	
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(10,10, "<{Griddie Example}>");
		
		while(true) {
			/*
			for (int y = 0; y < 16; y++) {
				for (int x = 0; x < 16; x++) {
					g.setColorAt(x, y, CellColor.getRandomRedBlue());
				}
			}
			g.sleep(0.1);*/
			MouseClick click = g.waitMouseClick();
			g.setColorAt(click.getX(), click.getY(), CellColor.getRandomCellColor());
			
			
			
		}
	}

}
