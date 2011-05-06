package src.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
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

		//Creep color is determined by alignment		
		g.setColor(c.getAlignment().getColor());
		
		//Creep shape is determined by its type
		switch(c.getType()) {
			case BIG_GUY:
				Arc2D.Double big = new Arc2D.Double();
				big.setArcByCenter(creepCenter.getX(), creepCenter.getY(),
						creepRadius * 1.5, 0, 360, Arc2D.PIE);
				g.fill(big);
				break;
			/*case FAST:
				Ellipse2D.Double fast = new Ellipse2D.Double(creepCenter.x - (tileWidth / 4), creepCenter.y - (tileHeight / 3), tileHeight * .25, tileWidth / 3);
				g.fill(fast);
				break; //needs to be better aligned
				*/
			case FLYING:
				GeneralPath triangle = new GeneralPath();
				triangle.moveTo(creepCenter.x - 5, creepCenter.y - 5);
				triangle.lineTo(creepCenter.x + 5, creepCenter.y - 5);
				triangle.lineTo(creepCenter.x, creepCenter.y + 5);
				triangle.closePath();
				g.fill(triangle);
				break;
				
			default: //generic creep
				Arc2D.Double creep = new Arc2D.Double();
				creep.setArcByCenter(creepCenter.getX(), creepCenter.getY(),
					creepRadius, 0, 360, Arc2D.PIE);
				g.fill(creep);
		}
		
		
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
