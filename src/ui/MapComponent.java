package src.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import src.core.Map;
import src.core.TileType;

public class MapComponent extends JComponent {
	private Map m;
	private boolean gridOn;
	
	public MapComponent(Map m) {
		this.m = m;
	}
	
	public boolean isGridOn() {
		return gridOn;
	}

	public void setGridOn(boolean gridOn) {
		this.gridOn = gridOn;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// in swing, g is always actually an instance of the more advanced Graphics2D class
		Graphics2D gg = (Graphics2D) g;
		
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    						RenderingHints.VALUE_ANTIALIAS_ON);
		
		double tileWidth = getWidth() / m.getWidth();
		double tileHeight = getHeight() / m.getHeight();
		
		Rectangle2D tile = new Rectangle2D.Double(0, 0, tileWidth, tileHeight);
		for (int x = 0; x < m.getWidth(); x++) {
			for (int y = 0; y < m.getHeight(); y++) {
				tile.setFrame(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
				TileType type = m.getTileType(x, y);
				TileDrawer.paintTile(type, tile, gg);
				
				if (isGridOn()) {
					gg.setPaint(Color.BLACK);
					gg.draw(tile);
				}
			}
		}
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }
    
	@Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    
	@Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
