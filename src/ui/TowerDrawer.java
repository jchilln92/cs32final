package src.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

public class TowerDrawer {
	public static void drawTower(IDrawableTower t, double tileHeight, double tileWidth, Graphics2D g) {
		// TODO: stub method
		Arc2D.Double tower = new Arc2D.Double();
		tower.setArcByCenter(t.getX() * tileWidth + tileWidth / 2, 
				t.getY() * tileHeight + tileHeight / 2, tileWidth/2-5, 0, 360, Arc2D.PIE);
		
		g.setColor(Color.BLUE);
		g.fill(tower);
	}
}
