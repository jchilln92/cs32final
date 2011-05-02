package src.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.net.LobbyManager;
import src.ui.controller.MultiplayerController;

public class MultiplayerWaitScreen extends JPanel {
	private JButton startGameButton;
	private JButton cancelButton;
	private JButton bootButton;
	
	private JLabel waitingLabel;
	private JLabel opponentLabel;
	private JLabel opponentNameLabel;
	
	private MultiplayerController controller;
	
	public MultiplayerWaitScreen(MultiplayerController multiController) {
		this.controller = multiController;
		
		startGameButton = new JButton("Start Game");
		cancelButton = new JButton("Cancel");
		bootButton = new JButton("Boot");
		
		waitingLabel = new JLabel("Waiting for opponents...");
		opponentLabel = new JLabel("Your opponent: ");
		opponentNameLabel = new JLabel("No opponent");
		
		add(startGameButton);
		add(cancelButton);
		add(bootButton);
		add(waitingLabel);
		add(opponentLabel);
		add(opponentNameLabel);
		
		setupButtonHandlers();
	}
	
	public void setupButtonHandlers() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.stopHostingGame();
			}
		});
		
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.startNetworkGame();
			}
		});
		
		bootButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bootPotentialOpponent();
			}
		});
	}
	
	public void setPotentialOpponent(String name) {
		opponentNameLabel.setText(name);
	}
}
