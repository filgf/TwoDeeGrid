package twodeegrid.examples.games.snake;


import java.util.Random;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;

public class Food {
	public Tuple[] foodPosition;
	
	final static Random r = new Random();
	TwoDeeGrid g;
	
	public Food(int numberFood, TwoDeeGrid g) {
		this.g = g;
		foodPosition = new Tuple[numberFood];
		for (int i = 0; i < foodPosition.length; i++) {
			foodPosition[i] = createNewTuple();
			drawFoodAt(foodPosition[i]);
		}
	}
	
	private Tuple createNewTuple() {
		Tuple t;
		do {
			t = new Tuple(r.nextInt(g.getGridSizeX()), r.nextInt(g.getGridSizeY()));
		} while (g.getColorAt(t.x, t.y) != CellColor.NONE);
		return t;
	}
	
	public boolean consumeFoodAt(Tuple p) {
		for (int i = 0; i < foodPosition.length; i++) {
			if (foodPosition[i].equals(p)) {
				foodPosition[i] = createNewTuple();
				drawFoodAt(foodPosition[i]);
				return true;
			}
		}
		return false;
	}
	
	private void drawFoodAt(Tuple pos) {
		g.setColorAt(pos.x, pos.y, CellColor.YELLOW);
	}
	
}
