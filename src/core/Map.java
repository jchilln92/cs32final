package src.core;

public class Map {
	private int width;
	private int height;
	private TileType tiles[][];
	
	public static Map demoMap() {
		Map demo = new Map(15, 15);
		
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				demo.tiles[x][y] = TileType.GRASS;
			}
		}
		
		demo.tiles[0][7] = TileType.WATER;
		demo.tiles[1][0] = TileType.PATH;
		demo.tiles[1][1] = TileType.PATH;
		demo.tiles[1][2] = TileType.PATH;
		demo.tiles[1][3] = TileType.PATH_TURN_RIGHT_DOWN;
		demo.tiles[1][7] = TileType.WATER;
		demo.tiles[2][3] = TileType.PATH_HORIZONTAL;
		demo.tiles[2][7] = TileType.WATER;
		demo.tiles[3][3] = TileType.PATH_TURN_UP_RIGHT;
		demo.tiles[3][7] = TileType.WATER;
		demo.tiles[4][5] = TileType.PATH_HORIZONTAL;
		demo.tiles[4][7] = TileType.WATER;
		demo.tiles[4][8] = TileType.WATER;
		demo.tiles[4][9] = TileType.WATER;
		demo.tiles[4][10] = TileType.WATER;
		demo.tiles[5][5] = TileType.PATH_HORIZONTAL;
		demo.tiles[5][10] = TileType.WATER;
		demo.tiles[5][11] = TileType.WATER;
		demo.tiles[5][12] = TileType.WATER;
		demo.tiles[5][13] = TileType.WATER;
		demo.tiles[6][5] = TileType.PATH_TURN_DOWN_RIGHT;
		demo.tiles[6][6] = TileType.PATH;
		demo.tiles[6][7] = TileType.PATH;
		demo.tiles[6][8] = TileType.PATH;
		demo.tiles[6][9] = TileType.PATH;
		demo.tiles[6][13] = TileType.WATER;
		demo.tiles[7][1] = TileType.ROCK;
		demo.tiles[7][2] = TileType.ROCK;
		demo.tiles[7][3] = TileType.ROCK;
		demo.tiles[7][9] = TileType.PATH_HORIZONTAL;
		demo.tiles[7][13] = TileType.WATER;
		demo.tiles[8][1] = TileType.ROCK;
		demo.tiles[8][2] = TileType.ROCK;
		demo.tiles[8][3] = TileType.ROCK;
		demo.tiles[8][9] = TileType.PATH_HORIZONTAL;
		demo.tiles[8][13] = TileType.WATER;
		demo.tiles[9][1] = TileType.ROCK;
		demo.tiles[9][2] = TileType.ROCK;
		demo.tiles[9][3] = TileType.ROCK;
		demo.tiles[9][9] = TileType.PATH_HORIZONTAL;
		demo.tiles[9][13] = TileType.WATER;
		demo.tiles[10][1] = TileType.ROCK;
		demo.tiles[10][2] = TileType.ROCK;
		demo.tiles[10][3] = TileType.ROCK;
		demo.tiles[10][9] = TileType.PATH_HORIZONTAL;
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
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		
		tiles = new TileType[width][height];
	}
	
	public TileType getTileType(int x, int y) {
		assert !(x >= width) && !(y >= height) : "Out of bounds";
		
		return tiles[x][y];
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
