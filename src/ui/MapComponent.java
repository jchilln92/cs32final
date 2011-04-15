package src.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComponent;

import src.core.Map;
import src.core.TileType;
import src.core.Tower;

public class MapComponent extends JComponent {
	private static final long serialVersionUID = 1L;

	private Map m;
	public Collection<? extends IDrawableCreep> creeps;
	public Collection<? extends IDrawableTower> towers;
	private boolean gridOn;
	private Tower placingTower;

	public MapComponent(Map m) {
		this.m = m;
		this.placingTower = null;
		this.creeps = new ArrayList<IDrawableCreep>();
		this.towers = new ArrayList<IDrawableTower>();
	}

	public boolean isGridOn() {
		return gridOn;
	}

	public void setGridOn(boolean gridOn) {
		this.gridOn = gridOn;
	}

	public boolean isPlacingTower() {
		return placingTower != null;
	}

	public void setPlacingTower(Tower t) {
		if (t == null) {
			this.placingTower = null;
		} else {
			this.placingTower = t;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// in swing, g is always actually an instance of the more advanced
		// Graphics2D class
		Graphics2D gg = (Graphics2D) g;

		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		double tileWidth = getWidth() / m.getWidth();
		double tileHeight = getHeight() / m.getHeight();

		Rectangle2D tile = new Rectangle2D.Double(0, 0, tileWidth, tileHeight);
		for (int x = 0; x < m.getWidth(); x++) {
			for (int y = 0; y < m.getHeight(); y++) {
				tile.setFrame(x * tileWidth, y * tileHeight, tileWidth,
						tileHeight);
				TileType type = m.getTileType(x, y);
				TileDrawer.paintTile(type, tile, gg);

				if (isGridOn()) {
					gg.setPaint(Color.BLACK);
					gg.draw(tile);
				}
			}
		}

		// draw error box over terrain squares if we are placing a tower
		if (isPlacingTower()) {
			Point mouse = getMousePosition();

			if (mouse != null) {
				int x = (int) (mouse.getX() / tileWidth);
				int y = (int) (mouse.getY() / tileHeight);

				if (m.isTerrain(x, y)) {
					tile.setFrame(x * tileWidth, y * tileHeight, tileWidth,
							tileHeight);
					gg.setColor(ColorConstants.invalidTowerPlacementColor);
					gg.fill(tile);
					gg.setColor(ColorConstants.invalidTowerPlacementColor2);
					gg.draw(tile);
				}
			}
		}

		for (IDrawableCreep c : creeps) {
			CreepDrawer.drawCreep(c, tileHeight, tileWidth, gg);
		}

		for (IDrawableTower t : towers) {
			TowerDrawer.drawTower(t, tileHeight, tileWidth, gg);
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
