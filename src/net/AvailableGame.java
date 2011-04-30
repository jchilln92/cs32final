package src.net;

public class AvailableGame {
	private String gameName;
	private String mapName;
	private String hostAddress;
	private String hostName;
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
	
	public long getSecondsWaiting() {
		return secondsWaiting;
	}
	
	public void setSecondsWaiting(long secondsWaiting) {
		this.secondsWaiting = secondsWaiting;
	}
	
	public String toString(){
		int minutes = (int)(secondsWaiting/60);
		return gameName + "\t" + hostName + "\t" + mapName + "\t" + minutes+":"+secondsWaiting%60;
	}
}
