package src.ui.lobby;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import src.GameMain;

public class Lobby extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String lobbyText = "Multiplayer Lobby";
	private JLabel lobbyLabel;
	private JLabel usernameLabel;
	
	private JTextField usernameField;
	private JScrollPane gameListPane;
	private JButton exitButton;
	private JButton createGameButton;
	private JButton joinButton;
	
	private GameMain gm;

	public Lobby(GameMain gameMain){
		super(new GridBagLayout());
		setSize(800, 600);
		
		this.gm = gameMain;
		
		lobbyLabel = new JLabel(lobbyText);
		usernameLabel = new JLabel("Username: ");
		
		usernameField = new JTextField("");
		//usernameField.setEditable(false);
		
		exitButton = new JButton("Go back"); 
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gm.showTitleScreen();
			}
		});
		
		createGameButton = new  JButton("Create Game");
		createGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gm.showGameSetup(true);
			}
		});
		joinButton = new  JButton("Join Game");
		joinButton.setEnabled(false);		
		
		
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(lobbyLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(usernameLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(usernameField, c);
		

		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		add(exitButton, c);
		
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		add(createGameButton, c);				

		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		add(joinButton, c);
		

		
	}
}