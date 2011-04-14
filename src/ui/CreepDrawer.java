package src.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

public class CreepDrawer {
	public static void drawCreep(IDrawableCreep c, double tileHeight, double tileWidth, Graphics2D g) {
		// TODO: This is just a stub / test method
		Arc2D.Double creep = new Arc2D.Double();
		creep.setArcByCenter(c.getPosition().getX() * tileWidth, c.getPosition().getY() * tileHeight, 5, 0, 360, Arc2D.PIE);
		g.setColor(Color.BLUE);
		g.fill(creep);
	}
}
