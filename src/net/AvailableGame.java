package src.net;


/**
 * Any multiplayer game that only has one player and has not begun yet.
 * This will be transformed into a real network game when the game begins
 */
public class AvailableGame {
	private String gameName;
	private String mapName;
	private String hostAddress;
	private String hostName;
	
	public String getGameName() {
		return gameName;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public String getMapName() {
		return mapName;
	}
	
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	public String getHostAddress() {
		return hostAddress;
	}
	
	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getHostName() {
		return hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}
