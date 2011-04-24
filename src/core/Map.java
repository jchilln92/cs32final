package src.core;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Represents all of the tiles in a map, as well as the path(s) that creeps can take along that map.
 */
public class Map {
	private int width;
	private int height;
	private TileType tiles[][];
	private CreepPath path;
	private CreepPath flyingPath;
	private String name;

	/**
	 * @return A hard-coded demo map
	 */
	public static Map demoMap() {
		Map demo = new Map(15, 15);
		
		demo.name = "Magical Crap Farm";

		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				demo.tiles[x][y] = TileType.GRASS;
			}
		}

		ArrayList<Point2D.Double> path = new ArrayList<Point2D.Double>();
		path.add(new Point2D.Double(1.5, 0));
		path.add(new Point2D.Double(1.5, 3.5));
		path.add(new Point2D.Double(3.5, 3.5));
		path.add(new Point2D.Double(3.5, 5.5));
		path.add(new Point2D.Double(6.5, 5.5));
		path.add(new Point2D.Double(6.5, 9.5));
		path.add(new Point2D.Double(11.5, 9.5));
		path.add(new Point2D.Double(11.5, 12.5));
		path.add(new Point2D.Double(12.5, 12.5));
		path.add(new Point2D.Double(12.5, 15));
		demo.path = new CreepPath(path);
		
		ArrayList<Point2D.Double> flyingPath = new ArrayList<Point2D.Double>();
		flyingPath.add(new Point2D.Double(1.5, 0));
		flyingPath.add(new Point2D.Double(12.5, 15));
		demo.flyingPath = new CreepPath(flyingPath);

		demo.tiles[0][7] = TileType.WATER;
		demo.tiles[1][0] = TileType.PATH;
		demo.tiles[1][1] = TileType.PATH;
		demo.tiles[1][2] = TileType.PATH;
		demo.tiles[1][3] = TileType.PATH;
		demo.tiles[1][7] = TileType.WATER;
		demo.tiles[2][3] = TileType.PATH;
		demo.tiles[2][7] = TileType.WATER;
		demo.tiles[3][3] = TileType.PATH;
		demo.tiles[3][4] = TileType.PATH;
		demo.tiles[3][5] = TileType.PATH;
		demo.tiles[3][7] = TileType.WATER;
		demo.tiles[4][5] = TileType.PATH;
		demo.tiles[4][7] = TileType.WATER;
		demo.tiles[4][8] = TileType.WATER;
		demo.tiles[4][9] = TileType.WATER;
		demo.tiles[4][10] = TileType.WATER;
		demo.tiles[5][5] = TileType.PATH;
		demo.tiles[5][10] = TileType.WATER;
		demo.tiles[5][11] = TileType.WATER;
		demo.tiles[5][12] = TileType.WATER;
		demo.tiles[5][13] = TileType.WATER;
		demo.tiles[6][5] = TileType.PATH;
		demo.tiles[6][6] = TileType.PATH;
		demo.tiles[6][7] = TileType.PATH;
		demo.tiles[6][8] = TileType.PATH;
		demo.tiles[6][9] = TileType.PATH;
		demo.tiles[6][13] = TileType.WATER;
		demo.tiles[7][1] = TileType.ROCK;
		demo.tiles[7][2] = TileType.ROCK;
		demo.tiles[7][3] = TileType.ROCK;
		demo.tiles[7][9] = TileType.PATH;
		demo.tiles[7][13] = TileType.WATER;
		demo.tiles[8][1] = TileType.ROCK;
		demo.tiles[8][2] = TileType.ROCK;
		demo.tiles[8][3] = TileType.ROCK;
		demo.tiles[8][9] = TileType.PATH;
		demo.tiles[8][13] = TileType.WATER;
		demo.tiles[9][1] = TileType.ROCK;
		demo.tiles[9][2] = TileType.ROCK;
		demo.tiles[9][3] = TileType.ROCK;
		demo.tiles[9][9] = TileType.PATH;
		demo.tiles[9][13] = TileType.WATER;
		demo.tiles[10][1] = TileType.ROCK;
		demo.tiles[10][2] = TileType.ROCK;
		demo.tiles[10][3] = TileType.ROCK;
		demo.tiles[10][9] = TileType.PATH;
		demo.tiles[10][13] = TileType.WATER;
		demo.tiles[10][14] = TileType.WATER;
		demo.tiles[11][9] = TileType.PATH;
		demo.tiles[11][10] = TileType.PATH;
		demo.tiles[11][11] = TileType.PATH;
		demo.tiles[11][12] = TileType.PATH;
		demo.tiles[12][12] = TileType.PATH;
		demo.tiles[12][13] = TileType.PATH;
		demo.tiles[12][14] = TileType.PATH;

		return demo;
	}

	public static Map demoMap2() {
		Map demo = new Map(15, 15);

		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				demo.tiles[x][y] = TileType.GRASS;
			}
		}

		ArrayList<Point2D.Double> path = new ArrayList<Point2D.Double>();
		path.add(new Point2D.Double(1.5, 0));
		path.add(new Point2D.Double(1.5, 3.5));
		path.add(new Point2D.Double(3.5, 3.5));
		path.add(new Point2D.Double(3.5, 5.5));
		path.add(new Point2D.Double(6.5, 5.5));
		path.add(new Point2D.Double(6.5, 9.5));
		path.add(new Point2D.Double(11.5, 9.5));
		path.add(new Point2D.Double(11.5, 12.5));
		path.add(new Point2D.Double(12.5, 12.5));
		path.add(new Point2D.Double(12.5, 15));
		demo.path = new CreepPath(path);
		
		ArrayList<Point2D.Double> flyingPath = new ArrayList<Point2D.Double>();
		flyingPath.add(new Point2D.Double(1.5, 0));
		flyingPath.add(new Point2D.Double(12.5, 15));
		demo.flyingPath = new CreepPath(flyingPath);

		demo.tiles[1][0] = TileType.PATH;
		demo.tiles[1][1] = TileType.PATH;
		demo.tiles[1][2] = TileType.PATH;
		demo.tiles[1][3] = TileType.PATH;
		demo.tiles[2][3] = TileType.PATH;
		demo.tiles[3][3] = TileType.PATH;
		demo.tiles[3][4] = TileType.PATH;
		demo.tiles[3][5] = TileType.PATH;
		demo.tiles[4][5] = TileType.PATH;
		demo.tiles[5][5] = TileType.PATH;
		demo.tiles[6][5] = TileType.PATH;
		demo.tiles[6][6] = TileType.PATH;
		demo.tiles[6][7] = TileType.PATH;
		demo.tiles[6][8] = TileType.PATH;
		demo.tiles[6][9] = TileType.PATH;
		demo.tiles[7][9] = TileType.PATH;
		demo.tiles[8][9] = TileType.PATH;
		demo.tiles[9][9] = TileType.PATH;
		demo.tiles[10][9] = TileType.PATH;
		demo.tiles[11][9] = TileType.PATH;
		demo.tiles[11][10] = TileType.PATH;
		demo.tiles[11][11] = TileType.PATH;
		demo.tiles[11][12] = TileType.PATH;
		demo.tiles[12][12] = TileType.PATH;
		demo.tiles[12][13] = TileType.PATH;
		demo.tiles[12][14] = TileType.PATH;

		return demo;
	}
	public Map(int width, int height) {
		this.width = width;
		this.height = height;

		tiles = new TileType[width][height];
	}

	/**
	 * Returns the type of the tile at the given position.
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return The type of the tile in question
	 */
	public TileType getTileType(int x, int y) {
		assert !(x >= width) && !(y >= height) : "Out of bounds";

		return tiles[x][y];
	}

	/**
	 * Determines whether a given tile is a terrain tile (i.e. it is water or rock)
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return A boolean indicating whether the tile in question is
	 * 			a terrain tile.
	 */
	public boolean isTerrain(int x, int y) {
		switch (getTileType(x, y)) {
			case ROCK:
			case WATER:
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * Determines whether a given tile is path of the path.
	 * @see isTerrain
	 */
	public boolean isPath(int x, int y) {
		if (getTileType(x, y) == TileType.PATH) return true;
		
		return false;
	}

	public CreepPath getPath() {
		return path;
	}
	
	public CreepPath getFlyingPath() {
		return flyingPath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
