package src.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CreepDrawer {
	private static boolean drawHealthBar = true; 

	public static void toggleDrawHealthBar(boolean shouldDraw){
		drawHealthBar = shouldDraw;
	}

	public static boolean getDrawHealthBar(){
		return drawHealthBar;
	}

	/**
	 * Draws a creep into the the appropriate area in the graphics context g.
	 */
	public static void drawCreep(IDrawableCreep c, double tileHeight,
			double tileWidth, Graphics2D g) {
		// TODO: This is just a stub / test method
		Point2D.Double creepCenter = new Point2D.Double(c.getPosition().getX()
				* tileWidth, c.getPosition().getY() * tileHeight);

		double creepRadius = 5;

		Arc2D.Double creep = new Arc2D.Double();
		creep.setArcByCenter(creepCenter.getX(), creepCenter.getY(),
				creepRadius, 0, 360, Arc2D.PIE);
		
		Color creepColor = Color.GRAY;
		switch(c.getAlignment()) {
			case BLUE:
				creepColor = Color.BLUE;
				break;
			case YELLOW:
				creepColor = Color.YELLOW;
				break;
			case RED:
				creepColor = Color.RED;
				break;
			case GREEN:
				creepColor = Color.GREEN;
				break;
			default:
				creepColor = Color.GRAY;
		}
		g.setColor(creepColor);
		g.fill(creep);
		
		if(drawHealthBar){
			// draw a health bar over the creep
			Rectangle2D.Double backgroundBar = new Rectangle2D.Double(creepCenter
					.getX()
					- creepRadius, creepCenter.getY() - creepRadius - 5,
					2 * creepRadius, 3);
	
			g.setColor(Color.RED);
			g.fill(backgroundBar);


			Rectangle2D.Double healthBar = new Rectangle2D.Double(creepCenter
				.getX()
				- creepRadius, creepCenter.getY() - creepRadius - 5, c
				.getHealthFraction()
				* 2 * creepRadius, 3);

			g.setColor(Color.GREEN);
			g.fill(healthBar);
		}
	}
}
