package src.ui.controller;

import src.GameMain;
import src.net.AvailableGame;
import src.net.LobbyManager;
import src.ui.Lobby;
import src.ui.MultiplayerGameSetup;
import src.ui.MultiplayerWaitScreen;

public class MultiplayerController {
	private GameMain gameMain;
	private Lobby lobby;
	private LobbyManager lobbyManager;
	private MultiplayerGameSetup gameSetup;
	private MultiplayerWaitScreen waitScreen;
	
	public MultiplayerController(GameMain gameMain) {
		this.gameMain = gameMain;
		
		lobbyManager = new LobbyManager();
		lobby = new Lobby(this);
		gameSetup = new MultiplayerGameSetup(this);
		waitScreen = new MultiplayerWaitScreen();
	}
	
	public void showLobby() {
		gameMain.showScreen(lobby);
	}
	
	public void hostNewGame() {
		AvailableGame newGame = new AvailableGame();
		newGame.setGameName(gameSetup.getGameName());
		newGame.setMapName(gameSetup.getMapName());
		
		lobbyManager.hostNewGame(newGame);
		
		gameMain.showScreen(waitScreen);
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
}
