package src.ui;

import java.awt.Graphics2D;
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
			g.setPaint(ColorConstants.pathColor);
			g.fill(tile);
			break;
		}
	}
}
