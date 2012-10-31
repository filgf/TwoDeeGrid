package twodeegrid.examples.games.snake;

import twodeegrid.examples.games.snake.Tuple;

public class Snake {
	
	Tuple pos;
	Tuple move;
	
	double speed;
	
	int snakeLength; 
			
	Tuple buffer[];
	int bptr; 
	
	final int gridSize;

	public Snake(int gridSize) {
		this.gridSize = gridSize;
		
		buffer = new Tuple[gridSize * gridSize];
		pos = new Tuple(gridSize/2, gridSize/2);
		move = new Tuple(0,0);
		snakeLength = 5;
		speed = 0.15;
		
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = new Tuple(Integer.MIN_VALUE, Integer.MIN_VALUE);
		}
		bptr = 0;
		buffer[0].x=pos.x;
		buffer[0].y=pos.y;
		
	}
	
	public boolean move() {
		
		if ((move.x==0)&&(move.y==0)) {
			return false;
		}
		
		pos.add(move);
		speed *= 0.998;
		
		bptr++;
		if (bptr >= gridSize * gridSize) {
			bptr = 0;
		}
		buffer[bptr].x = pos.x;
		buffer[bptr].y = pos.y;
		return true; 
	}

	public Tuple getTail() {
		int optr = (bptr - snakeLength);
		if (optr<0) {
			optr += buffer.length;
		}
		
		Tuple t = buffer[optr];
		
		if (t.x == Integer.MIN_VALUE) {
			return null;
		}
		
		return buffer[optr];
	}

}
