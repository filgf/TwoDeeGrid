package twodeegrid.examples.games.tron;


import java.awt.event.KeyEvent;

import twodeegrid.CellColor;
import twodeegrid.TwoDeeGrid;

public class TronGame {

	TwoDeeGrid g;

	int xPos, yPos;

	int moveX, moveY;
	
	double speed;

	public TronGame() {
		g = new TwoDeeGrid(30, 30, "Tron!");
	}
	
	private void playerInput() {
		int key = g.getKeyPressed();
		switch (key) {
		case KeyEvent.VK_LEFT:
			moveX = -1; moveY = 0;
			break;
		case KeyEvent.VK_RIGHT:
			moveX = 1; moveY = 0;
			break;
		case KeyEvent.VK_UP:
			moveX = 0; moveY = -1;
			break;
		case KeyEvent.VK_DOWN:
			moveX = 0; moveY = 1;
			break;
		}
	}

	private void movePlayer() {
		xPos += moveX;
		yPos += moveY;
		speed *= 0.998;
	}
	
	
	private void startGame() {
		boolean alive = true;	
		xPos = 15;
		yPos = 15;
		moveX = 1;
		moveY = 0;
		speed = 0.15;
		
		g.setColorAt(xPos, yPos, CellColor.GREEN);

		while(alive) {
			g.setColorAt(xPos, yPos, CellColor.GREEN);
			g.waitTime(speed);
			
			playerInput();

			g.setColorAt(xPos, yPos, CellColor.GREY_DARK);

			movePlayer();
			
			if (g.getColorAt(xPos, yPos) != CellColor.NONE) {
				alive = false;
				g.setColorAt(xPos, yPos, CellColor.RED);				
			}	
		}
	}

	public static void main(String[] args) {
		TronGame game = new TronGame();
		game.startGame();
		
	}


}
