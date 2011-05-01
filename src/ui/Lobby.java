package src.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import src.net.AvailableGame;
import src.net.LobbyManager;
import src.ui.controller.MultiplayerController;

public class Lobby extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String lobbyText = "Multiplayer Lobby";
	private static final String[] columnHeaders = {"Game Name", "Host", "Map Name", "Time Waiting"};
	
	private JLabel lobbyLabel;
	private JLabel usernameLabel;
	
	private JTextField usernameField;
	private JScrollPane gameTableScrollPane;
	private JTable gameTable;
	
	private JButton refreshButton;
	private JButton exitButton;
	private JButton createGameButton;
	private JButton joinButton;
	
	private MultiplayerController controller;

	public Lobby(MultiplayerController multiController) {
		super(new GridBagLayout());
		setSize(800, 600);
		
		this.controller = multiController;
		
		gameTableScrollPane = new JScrollPane();
		
		lobbyLabel = new JLabel(lobbyText);
		usernameLabel = new JLabel("Username: ");
		
		usernameField = new JTextField(13);
		usernameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// kind of hacky code
				JTextField field = (JTextField) e.getSource();

				int code = e.getKeyCode();
				if (code == KeyEvent.VK_BACK_SPACE) {
					controller.setUsername(field.getText().substring(0, field.getText().length()));
				} else {
					controller.setUsername(field.getText() + e.getKeyChar());
				}
			}
		});
		
		updateGameListPane();
		
		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGameListPane();
			}
		});
		
		exitButton = new JButton("Go back"); 
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//gm.showTitleScreen();
			}
		});
		
		createGameButton = new  JButton("Create Game");
		createGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//gm.showGameSetup(lm);
			}
		});
		
		joinButton = new JButton("Join Game");
		joinButton.setEnabled(false);		

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 0, 0, 0);	
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		add(lobbyLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(usernameLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(20, -20, 0, 0);	
		c.fill = GridBagConstraints.HORIZONTAL;
		add(usernameField, c);
		
		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets(20, -80, 0, 0);	
		c.fill = GridBagConstraints.NONE;
		add(refreshButton, c);
			
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 6;
		c.ipadx = 100;
		c.ipady = 60;
		c.insets = new Insets(20, 0, 0, 0);	
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(gameTableScrollPane, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(20, 0, 0, 0);	
		c.fill = GridBagConstraints.NONE;
		add(exitButton, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(20, 60, 0, 0);	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		add(createGameButton, c);				

		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(20, 15, 0, 0);	
		c.fill = GridBagConstraints.NONE;
		add(joinButton, c);	
	}
	
	private void updateGameListPane() {
		LobbyManager lm = controller.getLobbyManager();
		
		lm.refreshGameList();
		
		System.out.println(lm.getAvailableGames().size());
		String[][] data = new String[lm.getAvailableGames().size()][4];
		
		int i = 0;
		for (AvailableGame ag : lm.getAvailableGames()) {
			data[i][0] = ag.getGameName();
			data[i][1] = ag.getHostName();
			data[i][2] = ag.getMapName();
			data[i][3] = Long.toString(ag.getSecondsWaiting());
			i++;
		}
		
		gameTable = new JTable(data, columnHeaders);
		gameTable.getTableHeader().setReorderingAllowed(false);
		gameTableScrollPane.setViewportView(gameTable);
		gameTable.setFillsViewportHeight(true);
	}
}