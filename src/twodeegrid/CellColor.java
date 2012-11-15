package twodeegrid;

import java.awt.Color;
import java.util.Random;

/**
 * Enumeration for defined cell colors. Access using the predefined constants (for example "CellColor.BLACK").
 */
public enum CellColor {

	/**
	 * No color - empty white cell.
	 */
	NONE (Color.WHITE),

	/**
	 * Black  	
	 */
	BLACK (Color.BLACK),

	/**
	 * Light grey	
	 */
	GREY_LIGHT (Color.LIGHT_GRAY),

	/**
	 * Medium grey 	
	 */
	GREY (Color.GRAY),

	/**
	 * Dark grey 	
	 */
	GREY_DARK (Color.DARK_GRAY),

	/**
	 * Lighter green 	
	 */
	GREEN_LIGHT (new Color(0x95F1A0)),

	/**
	 * Green 	
	 */
	GREEN (new Color(0x30DE45)),

	/**
	 * Darker green
	 */
	GREEN_DARK (new Color(0x119622)),
	
	/**
	 * Lighter blue 	
	 */
	BLUE_LIGHT (new Color(0x9AB4EA)),

	/**
	 * Blue	
	 */
	BLUE (new Color(0x3D6BCF)),

	/**
	 * Darker blue	
	 */
	BLUE_DARK (new Color(0x163C8B)),
	
	/**
	 * Lighter orange 	
	 */
	YELLOW_LIGHT (new Color(0xFFDE9E)),

	/**
	 * Orange 	
	 */
	YELLOW (new Color(0xFFBB37)),

	/**
	 * Darker orange
	 */
	YELLOW_DARK (new Color(0xAC7814)),
	
	/**
	 * Lighter red 	
	 */
	RED_LIGHT (new Color(0xFFA39E)),
	
	/**
	 * Red
	 */
	RED (new Color(0xFF4137)),

	/**
	 * Darker red 	
	 */
	RED_DARK (new Color(0xAC1B14));
	
	
	private final Color col; 
	
	private CellColor(Color color) {

		col = color;
	}
	
	Color getJavaColor() {
		return col;		
	}
	
	private static Random rnd = new Random();
	
	
	/**
	 * Returns a completely random color (except CellColor.NONE!). 
	 * @return random cell color
	 */
	public static CellColor getRandomCellColor() {
		int size = CellColor.values().length;
		int index = rnd.nextInt(size-1);
		return CellColor.values()[index+1];
	}
	
	/**
	 * Randomly returns red (CellColor.RED) or blue (CellColor.BLUE).
	 * @return random cell color
	 */
	public static CellColor getRandomRedBlue() {
		return rnd.nextBoolean() ? CellColor.RED : CellColor.BLUE;
	}
	
	/**
	 * Randomly returns colors for "on" (black, CellColor.BLACK) an "off" (CellColor.NONE).
	 * @return random cell color
	 */
	public static CellColor getRandomOnOff() {
		return rnd.nextBoolean() ? CellColor.BLACK : CellColor.NONE;
	}

	/**
	 * Randomly returns colors for "on" (black, CellColor.BLACK) an "off" (CellColor.NONE). A probability bias
	 * can be given.
	 * 
	 * @param probabilityOn probability between 0.0 and 1.0 that the result is "on"
	 * @return random cell color
	 */
	public static CellColor getRandomOnOff(double probabilityOn) {
		double d = rnd.nextDouble();
		return (d<probabilityOn) ? CellColor.BLACK : CellColor.NONE;
	}

	
}
