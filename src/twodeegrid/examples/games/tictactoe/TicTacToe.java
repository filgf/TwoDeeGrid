package twodeegrid.examples.games.tictactoe;

import twodeegrid.CellColor;
import twodeegrid.MouseClick;
import twodeegrid.TwoDeeGrid;

public class TicTacToe {
	TwoDeeGrid g = new TwoDeeGrid(3, 5, "Tic Tac Toe!");
	
	private void init() {
		g.clear();
		for (int x = 0; x < 3; x++) {
			g.setColorAt(x, 3, CellColor.GREY_LIGHT);
			g.setColorAt(x, 4, CellColor.GREY_LIGHT);
		}
	}
	
	public void start() {
		while(true) {
			init();
			playGame();
			g.waitMouseClick();
		}
	}
	
	private void playGame() {
		CellColor colorTurn = CellColor.RED;
		int emptyFields = 9;
		
		while (true) {
			g.setColorAt(0, 4, colorTurn);
			
			MouseClick click;
			do {
				click = g.waitMouseClick();
			} while (g.getColorAt(click.getX(), click.getY()) != CellColor.NONE);

			g.setColorAt(click.getX(), click.getY(), colorTurn);
			emptyFields--;

			if (gameWon()) {
				for (int x = 0; x < 3; x++) {
					g.setColorAt(x, 4, colorTurn);
				}
				break;
			} else if (emptyFields == 0) {
				for (int x = 0; x < 3; x++) {
					g.setColorAt(x, 4, CellColor.GREY_LIGHT);
				}
				break;
			}
			
			if (colorTurn == CellColor.RED) {
				colorTurn = CellColor.GREEN;
			} else { 
				colorTurn = CellColor.RED;
			}
		}
	}
	
	private boolean gameWon() {
		for (int y = 0; y < 3; y++) {
			if ((g.getColorAt(0, y)!=CellColor.NONE)
					&& (g.getColorAt(0, y) == g.getColorAt(1, y))
					&& (g.getColorAt(1, y) == g.getColorAt(2, y))) {
				return true;
			}
		}
		
		for (int x = 0; x < 3; x++) {
			if ((g.getColorAt(x, 0)!=CellColor.NONE)
					&& (g.getColorAt(x, 0) == g.getColorAt(x, 1))
					&& (g.getColorAt(x, 1) == g.getColorAt(x, 2))) {
				return true;
			}
		}
		
		if ((g.getColorAt(0, 0)!=CellColor.NONE)
				&& (g.getColorAt(0, 0) == g.getColorAt(1, 1))
				&& (g.getColorAt(1, 1)==g.getColorAt(2, 2))) {
			return true;
		}
		
		if ((g.getColorAt(2, 0)!=CellColor.NONE)
				&& (g.getColorAt(2, 0) == g.getColorAt(1, 1))
				&& (g.getColorAt(1, 1)==g.getColorAt(0, 2))) {
			return true;
		}

		return false;
	}
	

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.start();
	}
}
