package src.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import src.core.Map;
import src.net.LobbyManager;
import src.ui.controller.MultiplayerController;

public class MultiplayerWaitScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton startGameButton;
	private JButton cancelButton;
	private JButton bootButton;
	
	private JPanel waitingPanel;
	private JPanel gridPanel;
	
	private JLabel gameLabel;
	private JLabel mapNameLabel;
	private JLabel waitingLabel;
	private JLabel opponentLabel;
	private JLabel opponentNameLabel;
	
	private MapComponent mc;
	
	private MultiplayerController controller;
	
	public MultiplayerWaitScreen(String gameName, String mapName, MultiplayerController multiController) {
		super(new BorderLayout());
		this.controller = multiController;
		
		//LobbyManager lm = controller.getLobbyManager();
		
		startGameButton = new JButton("Start Game");
		cancelButton = new JButton("Cancel");
		bootButton = new JButton("Boot");
		
		Border borderLine = BorderFactory.createLineBorder(Color.BLACK);
		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.PAGE_AXIS);	
		
		waitingLabel = new JLabel("Waiting for opponents...");
		waitingPanel = new JPanel();
		waitingPanel.setBorder(borderLine);
		waitingPanel.add(waitingLabel);
		
		mc = new MapComponent(true);
		mc.setGridOn(true);
		mc.setSize(400, 400);
		mc.setMap(Map.getMapByName(Map.getMapNames().get(0)));
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridBagLayout());
		gameLabel = new JLabel(gameName);
		gameLabel.setFont(new Font("Dialog.bold", 10, 32));
		mapNameLabel = new JLabel(mapName);
		//mapNameLabel.setFont(new Font("Dialog.bold", 10, 32));
		opponentLabel = new JLabel("Your opponent: ");
		opponentNameLabel = new JLabel("No opponent");
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(0, 0, 30, 0);
		c.gridx = 1;
		c.gridy = 0;
	
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 3;

		c.gridy = 1;
		gridPanel.add(gameLabel, c);
			
		c.gridx = 3;
		c.gridy = 2;
	
		c.insets = new Insets(0, 150, 0, 0);
		c.gridwidth = 1;
		gridPanel.add(mapNameLabel, c);
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 3;
		c.gridheight = 2;
		gridPanel.add(mc, c);
		c.insets = new Insets(30, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		gridPanel.add(opponentLabel, c);
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(60, 0, 0, 0);
		gridPanel.add(opponentNameLabel, c);
		c.gridy = 4;
		c.gridx = 0;
		c.insets = new Insets(90, 0, 0, 0);
		gridPanel.add(bootButton, c);
		c.insets = new Insets(0, 0, 0, 0);
		c.gridy = 5;
		c.gridx = 0;
		c.gridwidth = 2;
		gridPanel.add(cancelButton, c);
		c.gridx = 2;
		gridPanel.add(startGameButton, c);

	
		add(waitingPanel, BorderLayout.PAGE_START);
		add(gridPanel, BorderLayout.CENTER);
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
	
	public void setGameName(String name){
		gameLabel.setText(name);
		System.out.println("okay");
		
	}
	public void setPotentialOpponent(String name) {
		opponentNameLabel.setText(name);
	}
}
