package twodeegrid.examples.games.snake;


import java.awt.event.KeyEvent;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;

public class SnakeGame {

	
	private static int GRID_SIZE = 30;
	private static int NUMBER_FOOD = 20;
	
	TwoDeeGrid g;
	Snake snake;
	Food food;


	public SnakeGame() {
		g = new TwoDeeGrid(GRID_SIZE, GRID_SIZE, "Snake!");
	}
	
	private void playerInput() {
		int key = g.getKeyPressed();
		switch (key) {
		case KeyEvent.VK_LEFT:
			snake.move.x = -1; snake.move.y = 0;
			break;
		case KeyEvent.VK_RIGHT:
			snake.move.x = 1; snake.move.y = 0;
			break;
		case KeyEvent.VK_UP:
			snake.move.x = 0; snake.move.y = -1;
			break;
		case KeyEvent.VK_DOWN:
			snake.move.x = 0; snake.move.y = 1;
			break;
		}
	}
	


	
	private void startGame() {

		snake = new Snake(GRID_SIZE);
		food = new Food(NUMBER_FOOD, g);
		
		boolean alive = true;
		
		while(alive) {
			
			playerInput();

			g.setColorAt(snake.pos.x, snake.pos.y, CellColor.GREY_DARK);
			
			if (snake.move()) {
			
				Tuple tail = snake.getTail();
				if (tail != null) {
					g.setColorAt(tail.x, tail.y, CellColor.NONE);
				}
				
				if (food.consumeFoodAt(snake.pos)) {
					snake.snakeLength++;
				}
			}

			g.setColorAt(snake.pos.x, snake.pos.y, CellColor.GREEN);
			
			if (g.getColorAt(snake.pos.x, snake.pos.y) == CellColor.GREY_DARK) {
				alive = false;
				g.setColorAt(snake.pos.x, snake.pos.y, CellColor.RED);				
			}
			
			g.waitTime(snake.speed);

		}
	}






	public static void main(String[] args) {
		SnakeGame game = new SnakeGame();
		game.startGame();
		
	}


}
