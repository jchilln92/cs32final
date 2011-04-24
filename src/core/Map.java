package src.core;

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;

import src.FilePaths;
import src.core.xml.MapXMLReader;

/**
 * Represents all of the tiles in a map, as well as the path(s) that creeps can take along that map.
 */
public class Map {
	@Attribute
	private int width;
	
	@Attribute
	private int height;
	
	@ElementArray(entry="column")
	private TileType tiles[][];
	
	@Element(name="creepPath")
	private CreepPath path;
	
	@Element(name="flyingCreepPath")
	private CreepPath flyingPath;
	
	@Attribute
	private String name;

	private static ArrayList<Map> maps = null;
	
	/**
	 * @return An ArrayList containing the names of all available
	 * 			maps.
	 */
	public static ArrayList<String> getMapNames() {
		if (maps == null) loadMaps();
		
		ArrayList<String> names = new ArrayList<String>();
		
		for (Map m : maps) {
			names.add(m.getName());
		}
		
		return names;
	}
	
	/**
	 * Gets a Map (which has been loaded in from XML) by name.
	 * @param name The name of the map to retrieve
	 * @return A Map
	 */
	public static Map getMapByName(String name) {
		if (maps == null) loadMaps();
		
		for (Map m : maps) {
			if (m.getName().equals(name)) return m;
		}
		
		return null;
	}
	
	/**
	 * Loads all available maps from an XML file
	 */
	private static void loadMaps() {
		if (maps == null)
			maps = MapXMLReader.readXML(FilePaths.xmlPath + "maps.xml");
	}
	
	/**
	 * @see CreepPath
	 */
	private Map() {}
	
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

	public String getName() {
		return name;
	}
}
