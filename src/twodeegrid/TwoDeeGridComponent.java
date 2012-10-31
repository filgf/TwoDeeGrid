package twodeegrid;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

class TwoDeeGridComponent extends JComponent {
	private static final long serialVersionUID = -2654674886073253732L;

	TwoDeeGrid parent;
	

	TwoDeeGridComponent(TwoDeeGrid parent) {
		this.parent = parent;
		addMouseListener(new GriddieMouseListener(this));
	}
	
	private final static Stroke gridStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{3.0f}, 0.0f);


	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		// Draw Grid Content
		for (int y = 0; y < parent.ySize; y++) {
			for (int x = 0; x < parent.xSize; x++) {
				Color c = parent.cells[x][y].getJavaColor();
				g2d.setColor(c);
				g2d.fillRect(x*parent.gridElementPixels+1, y*parent.gridElementPixels+1, parent.gridElementPixels-1, parent.gridElementPixels-1);
			}
		}
		
		// Draw Raster
		g2d.setStroke(gridStroke);		
		g2d.setColor(Color.LIGHT_GRAY);
		for(int y=0; y<=parent.ySize; y++) {
	    	g2d.drawLine(0, y*parent.gridElementPixels, parent.getPixelXSize(), y*parent.gridElementPixels);
	    }
	    
    	for(int x=0; x<=parent.xSize; x++){
	    	g2d.drawLine(x*parent.gridElementPixels, 0, x*parent.gridElementPixels, parent.getPixelYSize());
    	}
	}
	
	MouseClick click = null;
	
	final class GriddieMouseListener extends MouseInputAdapter {
		Object obj;
		
		public GriddieMouseListener(Object obj) {
			this.obj = obj;
		}

		@Override
		public synchronized void mouseReleased(MouseEvent e) {
			Point p = e.getPoint();
			
			int gx = p.x/parent.gridElementPixels;
			int gy = p.y/parent.gridElementPixels;
			if ((gx<parent.xSize)&&(gy<parent.ySize)&&(gx>=0)&&(gy>=0)&&(e.getButton()<4)&&(e.getButton()>0)) {
				synchronized (obj) {
					click = new MouseClick(gx, gy, e.getButton());
					obj.notify();
				}
			}
		}
	}

	
}
