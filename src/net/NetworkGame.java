package src.net;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import src.core.Creep;
import src.core.Game;

/**
 * Acts just like a normal game, but adds network functionality
 * This is a pretty experimental class structure
 */
public class NetworkGame extends Game {
	private Connection remoteConnection; // the connection with our opponent
	
	private NetworkGameController getOpponentGC() {
		return ObjectSpace.getRemoteObject(remoteConnection, 
				1, NetworkGameController.class);
	}
}
