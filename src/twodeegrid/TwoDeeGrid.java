package twodeegrid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TwoDeeGrid {
	int gridElementPixels;
	
	final int xSize;
	final int ySize;
	
	CellColor [][] cells;
	
	private TwoDeeGridComponent gComp;
	
	int lastKey = KeyEvent.CHAR_UNDEFINED;

	
	public TwoDeeGrid() {
		this(8, 8, "TwoDeeGrid");
	}
	
	/**
	 * Create a new instance of Griddie. This initializes a window with a grid size
	 * of xSize x ySize and the given window title and shows it.
	 * 
	 * The window must be closed manually.
	 * 
	 * @param xSize number of grid cells horizontally
	 * @param ySize number of grid cells vertically
	 * @param title window title
	 */
	public TwoDeeGrid(int xSize, int ySize, String title) {
		this.xSize = xSize;
		this.ySize = ySize;
	
		// calc grid size
		Dimension sDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int dim = Math.min(sDimension.width, sDimension.height)-100;
		gridElementPixels = dim / Math.max(xSize, ySize);
		gridElementPixels = Math.max(gridElementPixels, 5);
		gridElementPixels = Math.min(gridElementPixels, 42);
		
		// Create cells
		cells = new CellColor[xSize][ySize];
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				cells[x][y] = CellColor.NONE;		
			}
		}
			
		
		// Create UI
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.WHITE);
		gComp = new TwoDeeGridComponent(this);
		panel.add(gComp);
		gComp.setPreferredSize(new Dimension(getPixelXSize(), getPixelYSize()));
		
		
		
		JFrame frame = new JFrame(title);
//		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon_confused.gif"));
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				lastKey = e.getExtendedKeyCode();
			}
		});
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	int getPixelXSize() {
		return xSize*gridElementPixels+1;
	}

	int getPixelYSize() {
		return ySize*gridElementPixels+1;
	}


	

	
	/**
	 * Get the color of the cell at position x,y. 
	 * <p> 
	 * If either parameter is out of bounds of the actual grid, the parameter ist wrapped (i.e. <code>x%xSize</code> or <code>y%ySize</code>).    
	 * Counting starts at 0: This means <code>getColorAt(0,0)</code> results in the color in the upper left corner. 
	 * 
	 * @param x horizontal position
	 * @param y vertical position
	 * @return CellColor object describing the color
	 */
	public CellColor getColorAt(int x, int y) {
		return cells[signedModulo(x, xSize)][signedModulo(y, ySize)];
	}

	
	/**
	 * Set the color of the cell at position x,y.
	 * <p>
	 * If either parameter is out of bounds of the actual grid, the parameter ist wrapped (i.e. <code>x%xSize</code> or <code>y%ySize</code>).    
	 * Counting starts at 0: This means <code>setColorAt(0,0,...)</code> results in setting color in the upper left corner.
	 *  
	 * @param x horizontal position
	 * @param y vertical position
	 * @param color CellColor object describing the color
	 */
	
	public void setColorAt(int x, int y, CellColor color) {
		cells[signedModulo(x, xSize)][signedModulo(y, ySize)] = color;
		gComp.repaint();
	}
	
	private int signedModulo(int v, int mod) {
		int r = v % mod;
		if (r<0) {
			r+=mod;
		}
		return r;
	}
	
	private static CellColor[][] cloneCellArray(CellColor[][] array) {
	    int rows=array.length ;
	    CellColor[][] newArray =(CellColor[][]) array.clone();
	    for(int row=0;row<rows;row++){
	        newArray[row]=(CellColor[]) array[row].clone();
	    }

	    return newArray;
	}
	
	
	/**
	 * Returns a <b>copy</b> of the whole array of currently shown grid cells. Each entry is a CellColor.
	 * <p>
	 * After doing changes on the array use updateCellArray to show them.
	 * @return two-dimensional array of CellColors
	 */
	public CellColor[][] getCellArray() {
		return cloneCellArray(cells);
	}
	
	/**
	 * Given a two-dimensional array of CellColor objects with fitting size, updates and shows the actual grid with its values.
	 * @param cells the two-dimensional array of CellColors
	 */
	public void updateCellArray(CellColor[][] cells) {
		this.cells = cells;
		gComp.repaint();
	}
	
	/**
	 * Clears the whole grid.
	 */
	public void clear() {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				cells[x][y] = CellColor.NONE;		
			}
		}
		gComp.repaint();
	}

	/**
	 * Wait for a given time (in seconds)
	 * @param time time to wait (in seconds)
	 */
	public void waitTime(double time) {
		try {
			Thread.sleep((long)(1000.0*time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wait until the mouse was clicked on the grid.
	 * @return MouseClick object, with information about which cell was clicked and with which mouse button.
	 */
	public MouseClick waitMouseClick() {
		synchronized (gComp) {
			while (gComp.click == null) {
				try {
					gComp.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			MouseClick c = gComp.click;
			gComp.click = null;
			return c;
		
		}
	}

	public int getKeyPressed() {
		waitTime(0.001);
		int lk = lastKey;
		lastKey = KeyEvent.CHAR_UNDEFINED;
		return lk;
	}
	
	public int getGridSizeX() {
		return xSize;
	}

	public int getGridSizeY() {
		return xSize;
	}
}
