package twodeegrid.examples.topics.array;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;


public class HelloArray {
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(5, 5, "Hello Array!");
		
		CellColor[][] cells = g.getCellArray();
		
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				cells[x][y] = CellColor.getRandomRedBlue();
			}
		}
		
		g.updateCellArray(cells);
	
	}

}
