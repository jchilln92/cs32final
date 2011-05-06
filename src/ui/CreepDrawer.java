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

		//double creepRadius = 5;
		double creepRadius = 0.2 * Math.sqrt(tileWidth * tileHeight);

		//Creep color is determined by alignment		
		g.setColor(c.getAlignment().getColor());
		
		//Creep shape is determined by its type
		switch(c.getType()) {
			case BIG_GUY:
				double largeRadius = creepRadius * 1.2;
				GeneralPath square = new GeneralPath();
				square.moveTo(creepCenter.x + largeRadius, creepCenter.y + largeRadius);
				square.lineTo(creepCenter.x - largeRadius, creepCenter.y + largeRadius);
				square.lineTo(creepCenter.x -largeRadius, creepCenter.y - largeRadius);
				square.lineTo(creepCenter.x + largeRadius, creepCenter.y - largeRadius);
				square.closePath();
				g.fill(square);
				break;
			case FAST:
				Arc2D.Double fast = new Arc2D.Double();
				fast.setArcByCenter(creepCenter.getX(), creepCenter.getY(),
						creepRadius * 0.5, 0, 360, Arc2D.PIE);
				g.fill(fast);
				break; //needs to be better aligned
			case FLYING:
				GeneralPath triangle = new GeneralPath();
				triangle.moveTo(creepCenter.x - creepRadius, creepCenter.y - creepRadius);
				triangle.lineTo(creepCenter.x + creepRadius, creepCenter.y - creepRadius);
				triangle.lineTo(creepCenter.x, creepCenter.y + creepRadius);
				triangle.closePath();
				g.fill(triangle);
				break;
			case ASSASSIN:
				GeneralPath diamond = new GeneralPath();
				diamond.moveTo(creepCenter.x , creepCenter.y + creepRadius);
				diamond.lineTo(creepCenter.x + creepRadius, creepCenter.y);
				diamond.lineTo(creepCenter.x , creepCenter.y - creepRadius);
				diamond.lineTo(creepCenter.x - creepRadius, creepCenter.y);
				diamond.closePath();
				g.fill(diamond);
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
