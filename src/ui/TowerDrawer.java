package src.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class TowerDrawer {
	public static void drawTower(IDrawableTower t, double tileHeight,
			double tileWidth, Graphics2D g) {
		// TODO: stub method
		Arc2D.Double tower = new Arc2D.Double();
		Arc2D.Double alignmentBack = new Arc2D.Double();
		Arc2D.Double alignmentShow = new Arc2D.Double();
		
		double centX = ((t.getX() * tileWidth) + (tileWidth / 2));
		double centY = ((t.getY() * tileHeight) + (tileHeight / 2));
		
		tower.setArcByCenter(centX, centY, tileWidth / 2 - 5, 0, 360,
				Arc2D.PIE);
		alignmentBack.setArcByCenter(centX, centY, tileWidth / 3 - 5, 0, 360, Arc2D.PIE);
		alignmentShow.setArcByCenter(centX, centY, tileWidth / 3 - 6, 0, 360, Arc2D.PIE);
		
		g.setColor(t.getType().getColor());
		
		g.fill(tower);
		g.setColor(Color.BLACK);
		g.fill(alignmentBack);
		g.setColor(t.getAlignment().getColor());
		g.fill(alignmentShow);
		
		// draw an indicator of tower upgrade status
		g.setColor(Color.ORANGE);
		Rectangle2D.Double upgradeDot = new Rectangle2D.Double();
		upgradeDot.height = tileHeight / 6;
		upgradeDot.width = tileWidth / 6;
		
		switch (t.getUpgradeLevel()) {
			// the omission of break statements is intentional
			case 3:
				upgradeDot.x = t.getX() * 
					tileWidth + tileWidth / 2 - upgradeDot.width / 2;
				upgradeDot.y =  t.getY() * tileHeight + upgradeDot.height / 2;
				g.fill(upgradeDot);
			case 2:
				upgradeDot.x = t.getX() * 
					tileWidth + tileWidth / 2 - 2 * upgradeDot.width;
				upgradeDot.y =  t.getY() * tileHeight + tileHeight - 2 * upgradeDot.height;
				g.fill(upgradeDot);
			case 1:
				upgradeDot.x = t.getX() * 
					tileWidth + tileWidth / 2 + upgradeDot.width;
				upgradeDot.y =  t.getY() * tileHeight + tileHeight - 2 * upgradeDot.height;
				g.fill(upgradeDot);
			case 0:
				break;
		}
	}
}
