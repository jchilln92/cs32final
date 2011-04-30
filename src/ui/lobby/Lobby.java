package src.ui.lobby;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.GameMain;
import src.core.Map;
import src.net.LobbyManager;

public class Lobby extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String lobbyText = "Multiplayer Lobby";
	private JLabel lobbyLabel;
	private JLabel usernameLabel;
	
	private JTextField usernameField;
	
	private JScrollPane gameListPane;
	
	private JButton refreshButton;
	private JButton exitButton;
	private JButton createGameButton;
	private JButton joinButton;
	
	private LobbyManager lm;
	private GameMain gm;

	public Lobby(GameMain gameMain){
		super(new GridBagLayout());
		setSize(800, 600);
		
		lm = new LobbyManager();
		gm = gameMain;
		
		lobbyLabel = new JLabel(lobbyText);
		usernameLabel = new JLabel("Username: ");
		
		usernameField = new JTextField(13);
		
		updateGameListPane();
		
		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lm.createServer();
				lm.refreshGameList();
				updateGameListPane();
			}
		});
		
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
		c.fill = GridBagConstraints.NONE;
		add(usernameField, c);
		
		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets(20, -80, 0, 0);	
		c.fill = GridBagConstraints.NONE;
		add(refreshButton, c);
			
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 6;
		c.ipadx = 140;
		c.ipady = 60;
		c.insets = new Insets(20, 0, 0, 0);	
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		add(gameListPane, c);
		
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
	
	private void updateGameListPane(){
		Object games[] = lm.getAvailableGames().toArray();
		Object gameNames[] = new String[games.length];
		for(int gameIndex = 0; gameIndex < games.length; gameIndex++){
			gameNames[gameIndex] = "a";//games[gameIndex].getGameName();
		}
		JList gameList = new JList(gameNames);

		
		/*gameList.setSelectedIndex(-1);
		gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
			if(gameList.getSelectedIndex() == -1)
				joinButton.setEnabled(false);
			else
				joinButton.setEnabled(true);
			}
		});
		*/
		String categories[] = {"A              \n    \tA\tA\t"};
		Object mapNames[] = Map.getMapNames().toArray();
		JList mapList = new JList(categories);
		
		JList mapList2 = new JList(mapNames);
		JPanel lists = new JPanel();
		lists.add(gameList);
		lists.add(mapList);
		lists.add(mapList2);
		gameListPane = new JScrollPane(lists);
	}
}