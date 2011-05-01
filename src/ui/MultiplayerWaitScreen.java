package src.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.net.LobbyManager;

public class MultiplayerWaitScreen extends JPanel {
	private JButton startGameButton;
	private JButton cancelButton;
	private JButton bootButton;
	
	private JLabel waitingLabel;
	private JLabel opponentLabel;
	private JLabel opponentNameLabel;
	
	private LobbyManager lm;
	
	public MultiplayerWaitScreen() {
		startGameButton = new JButton("Start Game");
		cancelButton = new JButton("Cancel");
		bootButton = new JButton("Boot");
		
		waitingLabel = new JLabel("Waiting for opponents...");
		opponentLabel = new JLabel("Your opponent: ");
		opponentNameLabel = new JLabel(" ");
		
		add(startGameButton);
		add(cancelButton);
		add(bootButton);
		add(waitingLabel);
		add(opponentLabel);
		add(opponentNameLabel);
	}
	
	public void setLobbyManager(LobbyManager lm) {
		this.lm = lm;
	}
}
