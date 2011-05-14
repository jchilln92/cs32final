package src.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import src.core.Map;
import src.ui.controller.MultiplayerController;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

/**
 * Handles all network communications related to setting up / managing a game.
 * Is capable of producing NetworkGame objects, which can then be played.
 */
public class LobbyManager {
	private NetworkPlayer localPlayer;
	private ArrayList<AvailableGame> availableGames; // every game that we know about
	private Client client;
	
	// if we're currently playing with someone this is the connection over which we're doing it
	private Connection opponentConnection;
	
	private AvailableGame hostedGame; // if we're hosting a game, this is it
	private Server server; // if we're hosting a game, we do it with this
	
	private MultiplayerController controller;
	
	/**
	 * Initialize a lobby manager, which is controller by a MultiplayerController
	 * @param multiController
	 */
	public LobbyManager(MultiplayerController multiController) {
		this.controller = multiController;
		
		localPlayer = new NetworkPlayer();
		availableGames = new ArrayList<AvailableGame>();
		
		client = new Client(NetworkConstants.bufferSize, NetworkConstants.bufferSize);
		NetworkConstants.registerKryoClasses(client.getKryo());
		client.start();
		
		initializeClientListener();
	}

	/**
	 * Set the public name of our player (the local player)
	 */
	public void setPlayerName(String name) {
		localPlayer.setUsername(name);
	}
	
	/**
	 * Quits any game in which the local player is currently taking part.
	 */
	public void quit() {
		GameNegotiationMessage quitMessage = new GameNegotiationMessage();
		quitMessage.type = GameNegotiationMessage.Type.QUIT_GAME;
		
		if (opponentConnection != null) // only try to quit if we're actually playing
			opponentConnection.sendTCP(quitMessage);
		
		if (server != null) { // if we were the host, shut down our server after quitting
			shutdownServer();
		}

		resetOpponentConnection();
	}
	
	/**
	 * Sends a message to the opponent, letting them know that we have detected that the game is over
	 * Sends the opponent a boolean indicating whether or not they won so that they know which screen to display.
	 * @param opponentWon Whether or not our opponent won.
	 */
	public void sendGameOverMessage(boolean opponentWon) {
		if (opponentConnection == null) return;
		
		GameNegotiationMessage gameOver = new GameNegotiationMessage();
		gameOver.type = GameNegotiationMessage.Type.GAME_OVER;
		gameOver.data = opponentWon;
		
		opponentConnection.sendTCP(gameOver);
	}
	
	/*--------------------------------------------
	 * Methods related to functioning as a host
	 ---------------------------------------------*/
	public void hostNewGame(AvailableGame game) {
		hostedGame = game;
		game.setHostName(localPlayer.getUsername());
		createServer();
	}
	
	/**
	 * Stop hosting a game that has not yet been started, booting any players that
	 * are currently waiting for you to press "Start Game".
	 */
	public void stopHostingGame() {
		hostedGame = null;
		boot(); // boot other players
		shutdownServer(); // shut down our server
		resetOpponentConnection(); // there's no longer an opponent connected
	}
	
	/**
	 * Starts up a server that will be used to host a game.
	 */
	private void createServer() {
		server = new Server(NetworkConstants.bufferSize, NetworkConstants.bufferSize);
		NetworkConstants.registerKryoClasses(server.getKryo());
		server.start();
		
		try {
			server.bind(NetworkConstants.tcpPort, NetworkConstants.udpPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initializeServerListener();
	}

	/**
	 * Shuts down our server, if we have one open.
	 */
	public void shutdownServer() {
		if (server != null) {
			server.close();
			server = null;
		}
	}
	
	/**
	 * Sets up the listeners that handle communication with the client.  These are responsible for handling all
	 * incoming messages.
	 */
	private void initializeServerListener() {
		server.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof GameNegotiationMessage) {
					GameNegotiationMessage m = (GameNegotiationMessage)object;
					GameNegotiationMessage response = new GameNegotiationMessage();

					switch (m.type) {
						case GAME_DISCOVER:
							// if we already have somebody connected and waiting to play, don't show this game to other people
							if (opponentConnection != null) {
								response.type = GameNegotiationMessage.Type.GAME_DISCOVER_RESPONSE;
								response.data = null;
								
								connection.sendTCP(response);
							} else { // otherwise send them an instance of AvailableGame
								try {
									hostedGame.setHostAddress(InetAddress.getLocalHost().getCanonicalHostName());
								} catch (UnknownHostException e) {
									// pretty sure localhost always exists
								}

								response.type = GameNegotiationMessage.Type.GAME_DISCOVER_RESPONSE;
								response.data = hostedGame;

								connection.sendTCP(response);
							}
							
							break;
						case ATTEMPT_TO_JOIN:
							// only let people join our game if we don't already have an opponent
							if (opponentConnection == null) {
								opponentConnection = connection;
							} else {
								boot(connection);
								break;
							}
								
							controller.playerAttemptedToJoin((String) m.data);
							break;
						// handle quit and game over conditions
						case QUIT_GAME:
							opponentConnection = null;
							controller.opponentDisconnected();
							break;
						case GAME_OVER:
							opponentConnection = null;
							controller.networkGameFinished((Boolean)m.data);
							break;
					}
				}
			}
			
			public void disconnected(Connection c) { // handle any other unexpected disconnections (such as the network failing)
				if (opponentConnection != null && c.getID() == opponentConnection.getID())
					controller.opponentDisconnected();
			}
		});
	}
	
	public void resetOpponentConnection() {
		opponentConnection = null;
	}
	
	/**
	 * Boot a player currently connected to our server.
	 * @param c The Connection representing the player to boot.
	 */
	private void boot(Connection c) {
		if (c == null) return;
		if (c.getID() == opponentConnection.getID()) resetOpponentConnection();
		
		GameNegotiationMessage response = new GameNegotiationMessage();
		response.type = GameNegotiationMessage.Type.ATTEMPT_TO_JOIN_RESPONSE;
		response.data = null;
		
		c.sendTCP(response);
	}
	
	/**
	 * See above.  By default, boots any currently connected opponent.
	 */
	public void boot() {
		boot(opponentConnection);
	}
	
	/**
	 * Accept a player who has joined our game and is waiting to play.  This starts the game.
	 */
	public NetworkGame acceptPlayer() {
		GameNegotiationMessage response = new GameNegotiationMessage();
		response.type = GameNegotiationMessage.Type.ATTEMPT_TO_JOIN_RESPONSE;
		response.data = hostedGame.getMapName();
		
		NetworkGame ng = new NetworkGame(server.getConnections()[0]);
		ng.setMap(Map.getMapByName(hostedGame.getMapName()));
		
		opponentConnection.sendTCP(response);
		hostedGame = null;

		return ng;
	}
	
	/*---------------------------------------------
	 * Methods related to functioning as a client
	 ----------------------------------------------*/
	/**
	 * Starts up the client listeners, which handle all external messages.
	 * @see initializeServerListener
	 */
	private void initializeClientListener() {
		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof GameNegotiationMessage) {
					GameNegotiationMessage m = (GameNegotiationMessage)object;
					
					switch (m.type) {
						case GAME_DISCOVER_RESPONSE:
							AvailableGame ag = (AvailableGame) m.data;
							
							if (ag != null)
								availableGames.add(ag);
							
							synchronized (client) {
								client.close();
							}
							
							break;
						case ATTEMPT_TO_JOIN_RESPONSE:
							String mapName = (String) m.data;
							
							if (mapName == null) {
								controller.wasBootedFromGame();
							} else {
								opponentConnection = connection;
								NetworkGame game = new NetworkGame(connection);
								game.setMap(Map.getMapByName(mapName));
								controller.startNetworkGame(game);
							}

							break;
						case QUIT_GAME:
							opponentConnection = null;
							controller.opponentDisconnected();
						case GAME_OVER:
							opponentConnection = null;
							controller.networkGameFinished((Boolean)m.data);
							break;
					}
				}
			}
			
			public void disconnected(Connection c) {
				if (opponentConnection != null && c.getID() == opponentConnection.getID())
					controller.opponentDisconnected();
			}
		});
	}
	
	/**
	 * Get an updated list of available games on the network.
	 */
	public void refreshGameList() {
		synchronized (availableGames) {
			availableGames.clear();

			for (InetAddress addr : SunlabAutodiscoverHack.getSunlabAddresses()) {
				try {
					client.connect(20, addr, NetworkConstants.tcpPort, NetworkConstants.udpPort);

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
					// There is no server at this address, ignore
				}
			}
		}
	}
	
	/**
	 * Try to join a game.
	 */
	public void joinGame(AvailableGame ag) {
		try {
			client.connect(NetworkConstants.gameConnectTimeout, 
						   InetAddress.getByName(ag.getHostAddress()), 
						   NetworkConstants.tcpPort, 
						   NetworkConstants.udpPort);
			
			controller.waitingToJoinGame();
			
			GameNegotiationMessage joinMessage = new GameNegotiationMessage();
			joinMessage.type = GameNegotiationMessage.Type.ATTEMPT_TO_JOIN;
			joinMessage.data = localPlayer.getUsername();

			client.sendTCP(joinMessage);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// we probably tried to join a game that doesn't exist any more
			// so refresh
			controller.refreshLobby();
		}
	}
	
	public ArrayList<AvailableGame> getAvailableGames(){
		return availableGames;
	}

	public Server getServer() {
		return server;
	}

	public AvailableGame getHostedGame() {
		return hostedGame;
	}
}
