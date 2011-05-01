package src.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class LobbyManager {
	NetworkPlayer localPlayer;
	ArrayList<AvailableGame> availableGames;
	ArrayList<InetAddress> testAddresses; // part of hack, see below
	Client client;
	
	AvailableGame hostedGame;
	Server server;
	
	public LobbyManager() {
		localPlayer = new NetworkPlayer();
		
		// hack for testing, make a list of hosts to test
		testAddresses = new ArrayList<InetAddress>();
		
		try {
			testAddresses.add(InetAddress.getByName("cslab8h"));
			//testAddresses.add(InetAddress.getByName("cslab8f"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		availableGames = new ArrayList<AvailableGame>();
		
		client = new Client();
		NetworkConstants.registerKryoClasses(client.getKryo());
		client.start();
		
		initializeClientListener();
	}
	
	public void setPlayerName(String name) {
		localPlayer.setUsername(name);
		System.out.println(localPlayer.getUsername());
	}
	
	public void hostNewGame(AvailableGame game) {
		hostedGame = game;
		hostedGame.setHostName(localPlayer.getUsername());
		createServer();
	}
	
	public void createServer(){
		server = new Server();
		NetworkConstants.registerKryoClasses(server.getKryo());
		server.start();
		try {
			server.bind(NetworkConstants.tcpPort, NetworkConstants.udpPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initializeServerListener();
	}
	
	private void initializeServerListener() {
		server.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof GameNegotiationMessage) {
					GameNegotiationMessage m = (GameNegotiationMessage)object;
					GameNegotiationMessage response = new GameNegotiationMessage();
					
					switch (m.type) {
						case GAME_DISCOVER:
							try {
								hostedGame.setHostAddress(InetAddress.getLocalHost().getCanonicalHostName());
							} catch (UnknownHostException e) {
								// pretty sure localhost always exists
							}
							
							response.type = GameNegotiationMessage.Type.GAME_DISCOVER_RESPONSE;
							response.data = hostedGame;
							
							connection.sendTCP(response);
							break;
						case ATTEMPT_TO_JOIN:
							System.out.println("Username " + (String)m.data + "is trying to join");
							System.out.println("Boot?");
							
							Scanner scan = new Scanner(System.in);
							String answer = scan.nextLine();
							boolean shouldBoot = Boolean.parseBoolean(answer);
							
							response.type = GameNegotiationMessage.Type.ATTEMPT_TO_JOIN_RESPONSE;
							if (shouldBoot) {
								response.data = false;
							} else {
								response.data = true;
							}
							
							connection.sendTCP(response);
							break;
					}
				}
			}
		});
	}
	
	private void initializeClientListener() {
		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof GameNegotiationMessage) {
					GameNegotiationMessage m = (GameNegotiationMessage)object;
					
					switch (m.type) {
						case GAME_DISCOVER_RESPONSE:
							AvailableGame ag = (AvailableGame) m.data;
							availableGames.add(ag);
							
							synchronized (client) {
								client.close();
							}
							
							break;
						case ATTEMPT_TO_JOIN_RESPONSE:
							boolean booted = !((Boolean) m.data);
							
							if (booted) {
								System.out.println("You've been kicked");
								client.close();
							}
					}
				}
			}
		});
	}
	
	public void refreshGameList() {
		availableGames.clear();
		
		for (InetAddress addr : testAddresses) {
			try {
				client.connect(NetworkConstants.gameDisoveryTimeout, addr, NetworkConstants.tcpPort, NetworkConstants.udpPort);
				
				// send query to server
				GameNegotiationMessage discoveryMessage = new GameNegotiationMessage();
				discoveryMessage.type = GameNegotiationMessage.Type.GAME_DISCOVER;
				client.sendTCP(discoveryMessage);
				
				while (true) {
					synchronized (client) {
						if (!client.isConnected()) break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void joinGame(AvailableGame ag) {
		try {
			client.connect(NetworkConstants.gameConnectTimeout, 
						   InetAddress.getByName(ag.getHostAddress()), 
						   NetworkConstants.tcpPort, 
						   NetworkConstants.udpPort);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GameNegotiationMessage joinMessage = new GameNegotiationMessage();
		joinMessage.type = GameNegotiationMessage.Type.ATTEMPT_TO_JOIN;
		joinMessage.data = localPlayer.getUsername();
		

		client.sendTCP(joinMessage);
	}
	
	public ArrayList<AvailableGame> getAvailableGames(){
		return availableGames;
	}
}
