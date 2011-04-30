package src.net;

import java.net.InetAddress;

public class AvailableGame {
	private String gameName;
	private String mapName;
	private InetAddress hostAddress;
	private long secondsWaiting;
	
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
	
	public InetAddress getHostAddress() {
		return hostAddress;
	}
	
	public void setHostAddress(InetAddress hostAddress) {
		this.hostAddress = hostAddress;
	}
	
	public long getSecondsWaiting() {
		return secondsWaiting;
	}
	
	public void setSecondsWaiting(long secondsWaiting) {
		this.secondsWaiting = secondsWaiting;
	}
}
