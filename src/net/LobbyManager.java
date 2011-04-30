package src.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class LobbyManager {
	NetworkPlayer localPlayer;
	ArrayList<AvailableGame> availableGames;
	ArrayList<InetAddress> testAddresses; // part of hack, see below
	
	Client client;
	Server server;
	
	public LobbyManager() {
		// hack for testing, make a list of hosts to test
		testAddresses = new ArrayList<InetAddress>();
		
		try {
			testAddresses.add(InetAddress.getByName("cslab8h"));
			testAddresses.add(InetAddress.getByName("cslab8f"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		availableGames = new ArrayList<AvailableGame>();
		
		client = new Client();
		NetworkConstants.registerKryoClasses(client.getKryo());
		
		server = new Server();
		NetworkConstants.registerKryoClasses(server.getKryo());
		try {
			server.bind(NetworkConstants.tcpPort, NetworkConstants.udpPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initializeListeners();
	}
	
	private void initializeListeners() {
		server.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof GameNegotiationMessage) {
					GameNegotiationMessage m = (GameNegotiationMessage)object;
					
					if (m.type == GameNegotiationMessage.Type.GAME_DISCOVER) {
						AvailableGame mock = new AvailableGame();
						mock.setGameName("This is a test game");
						mock.setMapName("Sandy Shores");
						mock.setSecondsWaiting(10);
						
						try {
							mock.setHostAddress(InetAddress.getLocalHost());
						} catch (UnknownHostException e) {
							// pretty sure localhost always exists
						}
						
						connection.sendTCP(mock);
					}
				}
			}
		});
		
		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof GameNegotiationMessage) {
					GameNegotiationMessage m = (GameNegotiationMessage)object;
					
					if (m.type == GameNegotiationMessage.Type.GAME_DISCOVER_RESPONSE) {
						AvailableGame ag = (AvailableGame) m.data;
						availableGames.add(ag);
					}
				}
			}
		});
	}
	
	public void refreshGameList() {
		availableGames.clear();
		
		for (InetAddress addr : testAddresses) {
			try {
				client.connect(1000, addr, NetworkConstants.tcpPort);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// send query to server
			GameNegotiationMessage discoveryMessage = new GameNegotiationMessage();
			discoveryMessage.type = GameNegotiationMessage.Type.GAME_DISCOVER;
			client.sendTCP(discoveryMessage);
		}
		
		System.out.println("Available Games:");
		for (AvailableGame ag : availableGames) {
			System.out.println(ag.getGameName());
		}
	}
}
