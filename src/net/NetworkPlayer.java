package src.net;

import src.core.Player;

/**
 * Represents a Player on the network with a username for identifiability purposes.
 */
public class NetworkPlayer extends Player {
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String uname) {
		username = uname;
	}
}
