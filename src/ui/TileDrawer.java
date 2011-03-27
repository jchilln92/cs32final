package src.ui;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import src.core.TileType;

public class TileDrawer {
	public static void paintTile(TileType type, Rectangle2D tile, Graphics2D g) {
		switch (type) {
		case WATER:
			g.setPaint(ColorConstants.waterColor);
			g.fill(tile);
			break;
		case ROCK:
			g.setPaint(ColorConstants.rockColor);
			g.fill(tile);
			break;
		case GRASS:
			g.setPaint(ColorConstants.grassColor);
			g.fill(tile);
			break;
		case PATH:
		case PATH_VERTICAL:
		case PATH_HORIZONTAL:
		case PATH_TURN_UP_RIGHT:
		case PATH_TURN_UP_LEFT:
		case PATH_TURN_DOWN_RIGHT:
		case PATH_TURN_DOWN_LEFT:
		case PATH_TURN_LEFT_UP:
		case PATH_TURN_LEFT_DOWN:
		case PATH_TURN_RIGHT_UP:
		case PATH_TURN_RIGHT_DOWN:
			paintPathTile(type, tile, g);
			break;
		}
	}
	
	public static void paintPathTile(TileType type, Rectangle2D tile, Graphics2D g) {
		AffineTransform oldT = g.getTransform();
		
		// define the gradient used for straight path pieces - this one is the correct gradient for drawing
		// vertical path pieces.  It will be rotated using an affine transform to make a horizontal tile
		Point2D p1 = new Point2D.Double(tile.getX() - tile.getWidth() * .3, tile.getCenterY());
		Point2D p2 = new Point2D.Double(tile.getX() + tile.getWidth() * 1.3, tile.getCenterY());
		float[] dist = {.01f, .5f, .99f};
		Color[] colors = {ColorConstants.pathColor, ColorConstants.pathColor2, ColorConstants.pathColor};
		LinearGradientPaint straightPaint = new LinearGradientPaint(p1, p2, dist, colors);
		
		// define the radial gradient used for drawing curved path segments
		double radius = tile.getWidth() * 1.3;
		Point2D center = new Point2D.Double(tile.getX(), tile.getMaxY());
		Point2D focus = new Point2D.Double(tile.getX() - (tile.getWidth() * .3)/Math.sqrt(2), 
										   tile.getMaxY() - (tile.getWidth() * .3)/Math.sqrt(2));
		RadialGradientPaint curvedPaint = new RadialGradientPaint(center, (float)radius, focus, 
				dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
		
		Arc2D sector = new Arc2D.Double();
		sector.setArcByCenter(tile.getX(), tile.getMaxY(), tile.getWidth(), 0, 90, Arc2D.PIE);
		
		switch (type) {
		case PATH_HORIZONTAL:
			g.setPaint(straightPaint);
			g.rotate(Math.PI/2, tile.getCenterX(), tile.getCenterY());
			g.fill(tile);
			break;
		case PATH:
		case PATH_VERTICAL:
			g.setPaint(straightPaint);
			g.fill(tile);
			break;
		case PATH_TURN_UP_RIGHT:
			g.rotate(-Math.PI/2, tile.getCenterX(), tile.getCenterY());
		case PATH_TURN_UP_LEFT:
		case PATH_TURN_DOWN_LEFT:
		case PATH_TURN_LEFT_UP:
		case PATH_TURN_LEFT_DOWN:
		case PATH_TURN_RIGHT_UP:
		case PATH_TURN_RIGHT_DOWN:
			g.rotate(Math.PI, tile.getCenterX(), tile.getCenterY());
		case PATH_TURN_DOWN_RIGHT:
			g.setPaint(ColorConstants.grassColor);
			g.fill(tile);
			g.setPaint(curvedPaint);
			g.fill(sector);
			break;
		}
		
		g.setTransform(oldT);
	}
}
