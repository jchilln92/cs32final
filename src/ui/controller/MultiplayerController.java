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

public class MultiplayerController {
	private GameMain gameMain;
	private Lobby lobby;
	private LobbyManager lobbyManager;
	private MultiplayerGameSetup gameSetup;
	private MultiplayerWaitScreen waitScreen;
	
	public MultiplayerController(GameMain gameMain) {
		this.gameMain = gameMain;
		
		lobbyManager = new LobbyManager(this);
		lobby = new Lobby(this);
		gameSetup = new MultiplayerGameSetup(this);
		waitScreen = new MultiplayerWaitScreen("","",this);
	}
	
	public void showLobby() {
		gameMain.showScreen(lobby);
	}
	
	public void exitLobby() {
		TitleScreen title = new TitleScreen(gameMain);
		gameMain.showScreen(title);
	}
	
	public void beginGameCreation() {
		gameMain.showScreen(gameSetup);
	}
	
	public void cancelGameCreation() {
		gameMain.showScreen(lobby);
	}
	
	public void completeGameCreation() {
		AvailableGame newHostedGame = new AvailableGame();
		newHostedGame.setGameName(gameSetup.getGameName());
		newHostedGame.setMapName(gameSetup.getMapName());
		lobbyManager.hostNewGame(newHostedGame);
	
		new MultiplayerWaitScreen(gameSetup.getGameName(), gameSetup.getMapName(), this);
		gameMain.showScreen(waitScreen);
	}
	
	public void joinGame(int selectedRow) {
		lobbyManager.joinGame(lobbyManager.getAvailableGames().get(selectedRow));
	}
	
	public void playerAttemptedToJoin(String name) {
		waitScreen.setPotentialOpponent(name);
	}
	
	public void bootPotentialOpponent() {
		lobbyManager.boot();
	}
	
	public void setUsername(String uname) {
		lobbyManager.setPlayerName(uname);
	}
	
	public LobbyManager getLobbyManager() {
		return lobbyManager;
	}
	
	public GameMain getGameMain() {
		return gameMain;
	}

	public void stopHostingGame() {
		lobbyManager.stopHostingGame();
		lobby.updateGameListPane();
		gameMain.showScreen(lobby);
	}

	public void startNetworkGame() {
		startNetworkGame(lobbyManager.acceptPlayer());
	}
	
	public void startNetworkGame(NetworkGame ng) {
		// controls drawing the opponent's map
		NetworkGameController networkController = new NetworkGameController(ng);
		
		// controls drawing our map
		GameController localController = new GameController();
		
		MultiplayerGamePanel gamePanel = new MultiplayerGamePanel(localController,
				networkController, ng, this);
		
		localController.start();
		
		gameMain.showScreen(gamePanel);
	}
}
