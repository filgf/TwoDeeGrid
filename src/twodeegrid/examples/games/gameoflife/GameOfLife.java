package twodeegrid.examples.games.gameoflife;


import java.awt.event.KeyEvent;
import java.util.Random;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;

public class GameOfLife {
	TwoDeeGrid g = new TwoDeeGrid(40, 40, "Conway's Game of Life");
	
	Random rand = new Random();
	
	public void init() {
		for (int y = 0; y < g.getGridSizeY(); y++) {
			for (int x = 0; x < g.getGridSizeX(); x++) {
				g.setColorAt(x, y, CellColor.getRandomOnOff(0.31));
			}
		}
	}

	public void step() {
		CellColor[][] cellsNew = g.getCellArray();
		
		for (int y = 0; y < g.getGridSizeY(); y++) {
			for (int x = 0; x < g.getGridSizeX(); x++) {

				int numNeighbors = numberOfLivingNeighbors(x, y); 
				
				if (isLiving(x, y)) {
					if ((numNeighbors < 2)||(numNeighbors > 3)) {
						cellsNew[x][y] = CellColor.NONE;
					}
				} else {
					if (numNeighbors == 3) {
						cellsNew[x][y] = CellColor.BLACK;
					}
				}
				
			}
		}

		g.updateCellArray(cellsNew);
	}
	
	private int numberOfLivingNeighbors(int x, int y) {
		int num = countLiving(x-1, y-1) + countLiving(x, y-1) + countLiving(x+1, y-1) 
				+ countLiving(x-1, y)                 		  + countLiving(x+1, y)
				+ countLiving(x-1, y+1) + countLiving(x, y+1) + countLiving(x+1, y+1);
		return num;
		
	}
	
	private boolean isLiving(int x, int y) {
		return g.getColorAt(x, y) != CellColor.NONE;
		
	}
	
	private int countLiving(int x, int y) {
		if (isLiving(x, y)) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
	public void start() {
		init();
		
		while(true) {
			step();
			
			if (g.getKeyPressed() == KeyEvent.VK_SPACE) {
				init();
			}
			g.waitTime(0.1);
		}
	}
	
	public static void main(String[] args) {
		GameOfLife game = new GameOfLife();
		game.start();

		
	}
}
