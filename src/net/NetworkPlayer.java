package src.net;

import src.core.Player;

public class NetworkPlayer extends Player {
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String uname) {
		username = uname;
	}
}
