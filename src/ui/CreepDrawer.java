package src.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import src.core.Creep;

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
		double creepRadius = 0.25 * Math.sqrt(tileWidth * tileHeight);

		//Creep shape is determined by its type
		Image creepImage = Creep.getImage(c.getType(), c.getAlignment());
		g.drawImage(creepImage, 
				(int)(c.getPosition().getX() * tileWidth) - (int)creepRadius, 
				(int)(c.getPosition().getY() * tileHeight) - (int)creepRadius,
				(int)(2 * creepRadius),
				(int)(2 * creepRadius),
				null);
		
		if (drawHealthBar) {
			double healthBarWidth = 2 * creepRadius - 3;
			
			// draw a health bar over the creep
			Rectangle2D.Double backgroundBar = new Rectangle2D.Double(c.getPosition().getX() * tileWidth - healthBarWidth / 2, 
																	  c.getPosition().getY() * tileHeight - creepRadius - 5,
																	  2 * creepRadius - 4, 3);
	
			g.setColor(Color.RED);
			g.fill(backgroundBar);


			Rectangle2D.Double healthBar = new Rectangle2D.Double(c.getPosition().getX() * tileWidth - healthBarWidth / 2, 
																  c.getPosition().getY() * tileHeight - creepRadius - 5, 
																  c.getHealthFraction() * healthBarWidth, 3);

			g.setColor(Color.GREEN);
			g.fill(healthBar);
		}
	}
}
