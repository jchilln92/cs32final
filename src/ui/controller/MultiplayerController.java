package src.ui.controller;

import src.GameMain;
import src.net.AvailableGame;
import src.net.LobbyManager;
import src.net.NetworkGame;
import src.net.NetworkGameController;
import src.ui.Lobby;
import src.ui.MultiplayerGamePanel;
import src.ui.MultiplayerGameSetup;
import src.ui.MultiplayerWaitScreen;
import src.ui.TitleScreen;

/**
 * Handles events occurring with the multiplayer lobby; enables creation, joining, dropping, and starting of games.
 */
public class MultiplayerController {
	private GameMain gameMain;
	private Lobby lobby;
	private LobbyManager lobbyManager;
	private MultiplayerGameSetup gameSetup;
	private MultiplayerWaitScreen waitScreen;
	
	private boolean gameInProgress;
	
	public MultiplayerController(GameMain gameMain) {
		this.gameMain = gameMain;
		
		lobbyManager = new LobbyManager(this);
		lobby = new Lobby(this);
		gameSetup = new MultiplayerGameSetup(this);
		waitScreen = new MultiplayerWaitScreen("", "", this);
		gameInProgress = false;
	}
	
	/*
	 * Lobby control methods
	 */
	public void showLobby() {
		gameMain.showScreen(lobby);
	}
	
	public void exitLobby() {
		TitleScreen title = new TitleScreen(gameMain);
		gameMain.showScreen(title);
	}
	
	/*
	 * Game creation
	 */
	public void beginGameCreation() {
		gameMain.showScreen(gameSetup);
	}
	
	public void cancelGameCreation() {
		gameSetup.reset();
		gameMain.showScreen(lobby);
	}
	
	public void completeGameCreation() {
		AvailableGame newHostedGame = new AvailableGame();
		newHostedGame.setGameName(gameSetup.getGameName());
		newHostedGame.setMapName(gameSetup.getMapName());
		lobbyManager.hostNewGame(newHostedGame);
	
		waitScreen = new MultiplayerWaitScreen(gameSetup.getGameName(), gameSetup.getMapName(), this);
		gameMain.showScreen(waitScreen);
	}
	
	/*
	 * Joining / exiting games
	 */
	public void joinGame(int selectedRow) {
		lobbyManager.joinGame(lobbyManager.getAvailableGames().get(selectedRow));
	}
	
	public void playerAttemptedToJoin(String name) {	
		waitScreen.setPotentialOpponent(name);
	}
	
	public void bootPotentialOpponent() {
		lobbyManager.boot();
		waitScreen.setPotentialOpponent(null);
	}
	
	public void startNetworkGame() {
		startNetworkGame(lobbyManager.acceptPlayer());
	}
	
	/**
	 * Begins a networked game between two players, given a NetworkGame object.
	 * @param ng
	 */
	public void startNetworkGame(NetworkGame ng) {
		gameInProgress = true;
		
		// controls drawing the opponent's map
		NetworkGameController networkController = new NetworkGameController(ng);
		
		// controls drawing our map
		GameController localController = new GameController();
		
		MultiplayerGamePanel gamePanel = new MultiplayerGamePanel(localController,
				networkController, ng, this);
		
		localController.start();
		
		gameMain.showScreen(gamePanel);
		//gameMain.setSize(1600, 600);
	}

	/**
	 * Exits a game in progress and returns the player to the lobby.
	 */
	public void quitNetworkGame() {
		gameInProgress = false;
		lobbyManager.quit();
		lobby.updateGameListPane();
		gameMain.showScreen(lobby);
		gameMain.setSize(800, 600);
	}

	/**
	 * On opponent disconnection, removes opponent from player's "potential opponent slot" 
	 * if player is waiting to start game, or takes player back to the lobby if opponent
	 * disconnected during a game.
	 */
	public void opponentDisconnected() {
		if (lobbyManager.getHostedGame() != null) {
			waitScreen.setPotentialOpponent(null);
		}
		
		if (gameInProgress) {
			quitNetworkGame();
		}
	}
	
	/**
	 * Handles appropriate operations for a player who discontinues hosting a game,
	 * removing the game from the pool and returning the former host to the lobby.
	 */
	public void stopHostingGame() {
		gameSetup.reset();
		lobbyManager.stopHostingGame();
		lobby.updateGameListPane();
		gameMain.showScreen(lobby);
	}
	
	public void setUsername(String uname) {
		lobbyManager.setPlayerName(uname);
	}
	
	public LobbyManager getLobbyManager() {
		return lobbyManager;
	}
	
	public boolean isGameInProgress() {
		return gameInProgress;
	}
	
	public GameMain getGameMain() {
		return gameMain;
	}
}
