package src.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import src.core.Tower;

/**
 * Handles drawing towers on the screen with their respective upgrades and orientations
 */
public class TowerDrawer {
	
	/**
	 * Draws a tower into the the appropriate area in the graphics context g.
	 * @param t The tower to draw
	 * @param tileHeight The height of a tile on the map
	 * @param tileWIdth The width of a tile on the map
	 * @param g The Graphics2D object
	 */
	public static void drawTower(IDrawableTower t, double tileHeight,
			double tileWidth, Graphics2D g) {
		AffineTransform originalAt = g.getTransform();
		g.transform(AffineTransform.getRotateInstance(t.getOrientation(), 
														(t.getX() + .5) * tileWidth, 
														(t.getY() + .5) * tileHeight));
		
		Image towerImage = Tower.getImage(t.getType(), t.getAlignment());
		g.drawImage(towerImage, 
					(int)(t.getX() * tileWidth), 
					(int)(t.getY() * tileHeight),
					(int)tileWidth,
					(int)tileHeight,
					null);
		
		g.setTransform(originalAt);
		
		// draw an indicator of tower upgrade status
		g.setColor(Color.ORANGE);
		Rectangle2D.Double upgradeDot = new Rectangle2D.Double();
		upgradeDot.height = tileHeight / 6;
		upgradeDot.width = tileWidth / 6;
		
		switch (t.getUpgradeLevel()) {
			// the omission of break statements is intentional
			case 3:
				upgradeDot.x = t.getX() * tileWidth + tileWidth / 2 - upgradeDot.width / 2;
				upgradeDot.y =  t.getY() * tileHeight + tileHeight - 2 * upgradeDot.height;
				g.fill(upgradeDot);
			case 2:
				upgradeDot.x = t.getX() * tileWidth + tileWidth / 2 - 2 * upgradeDot.width;
				upgradeDot.y =  t.getY() * tileHeight + tileHeight - 2 * upgradeDot.height;
				g.fill(upgradeDot);
			case 1:
				upgradeDot.x = t.getX() * tileWidth + tileWidth / 2 + upgradeDot.width;
				upgradeDot.y =  t.getY() * tileHeight + tileHeight - 2 * upgradeDot.height;
				g.fill(upgradeDot);
			case 0:
				break;
		}
	}
}
