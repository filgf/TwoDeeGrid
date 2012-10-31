package twodeegrid.examples.games.snake;

public class Tuple {

	public int x;
	public int y;

	public Tuple(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Tuple t) {
		x += t.x;
		y += t.y;
	}
	
	public boolean equals(Tuple t) {
		return ((x==t.x)&&(y==t.y));
	}

}
