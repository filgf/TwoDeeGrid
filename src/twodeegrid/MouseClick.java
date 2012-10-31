package twodeegrid;


/**
 * Objects of this class are generated and returned by Griddie.waitMouseClick(). 
 * They contain information about the grid coordinates the mouse click occurred on 
 * (starting from 0,0 as the upper-leftmost tile) and which button was clicked
 * @author graf
 *
 */
public class MouseClick {
	
	/**
	 * left mouse button clicked
	 */
	public static int LEFT = 1;

	/**
	 * right mouse button clicked
	 */
	public static int RIGHT = 2;
	
	/**
	 * middle mouse button clicked
	 */
	public static int MIDDLE = 3;
	
	
	private final int x;
	private final int y;
	
	private final int type;

	/**
	 * Create new mouse click object.
	 * @param x horizontal position on grid
	 * @param y vertical position on grid
	 * @param type type of clicked mouse button
	 */
	public MouseClick(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	/**
	 * Get x-coordinate (horizontal)
	 * @return x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get y-coordinate (vertical)
	 * @return y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get type of mouse click (MouseClick.LEFT, MouseClick.RIGHT, MouseClick.MIDDLE). 
	 * @return type of mouse click
	 */
	public int getType() {
		return type;
	}
}
