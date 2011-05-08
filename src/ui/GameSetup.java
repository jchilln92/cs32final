package src.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.FilePaths;
import src.GameMain;
import src.core.Game;
import src.core.Map;
import src.ui.controller.GameController;

/**
 * Handles code for the layout and operations involved with creating a multiplayer game.
 */
public class GameSetup extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String createGameText = "Create your Game:";
	private static final String createNameText = "Game Name:";

	private JLabel createGameLabel;
	private JLabel mapLabel;
	
	protected JButton playButton;
	protected JButton cancelButton;
	
	protected JLabel createNameLabel;
	protected JTextField createNameField;
	
	private JScrollPane mapListPane;
	protected JList mapList;

	private MapComponent mc;
	protected GameMain gameMain;
	
	private ImageIcon background;
	private ImageIcon beginGameIcon;
	private ImageIcon beginGamePressedIcon;
	private ImageIcon beginGameHoverIcon;
	private ImageIcon mainMenuIcon;
	private ImageIcon mainMenuPressedIcon;
	private ImageIcon mainMenuHoverIcon;

	public GameSetup() {
		super(new GridBagLayout());
		setSize(800, 600);
		
		mc = new MapComponent(true);
		mc.setGridOn(true);
		mc.setSize(400, 400);
			
		background = new ImageIcon(FilePaths.bgPath + "GenericBGRDsmall.png");
		beginGameIcon = new ImageIcon(FilePaths.buttonPath + "GameStartButton.png");
		beginGamePressedIcon = new ImageIcon(FilePaths.buttonPath + "GameStartButtonDown.png");
		beginGameHoverIcon = new ImageIcon(FilePaths.buttonPath + "GameStartButtonHover.png");
		
		mainMenuIcon = new ImageIcon(FilePaths.buttonPath + "MainMenuButton.png");
		mainMenuPressedIcon = new ImageIcon(FilePaths.buttonPath + "MainMenuButtonDown.png");
		mainMenuHoverIcon = new ImageIcon(FilePaths.buttonPath + "MainMenuButtonHover.png");

		
		Font creationFont = new Font("test", Font.BOLD, 18);
		createGameLabel = new JLabel(createGameText);
		createGameLabel.setForeground(Color.WHITE);
		createGameLabel.setFont(creationFont);

		mapLabel = new JLabel("Map:");
		mapLabel.setForeground(Color.WHITE);
		
		Font nameFont = new Font("test", Font.BOLD, 14);
		createNameLabel = new JLabel(createNameText);
		createNameLabel.setForeground(Color.WHITE);
		createNameLabel.setFont(creationFont);
		createNameField = new JTextField("");
		
		cancelButton = new JButton(mainMenuIcon); //go back to main menu if cancel game creation
		cancelButton.setBorder(BorderFactory.createEmptyBorder());
		cancelButton.setContentAreaFilled(false);
		cancelButton.setPressedIcon(mainMenuPressedIcon);
		cancelButton.setRolloverIcon(mainMenuHoverIcon);

		
		playButton = new JButton(beginGameIcon);
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		playButton.setPressedIcon(beginGamePressedIcon);
		playButton.setRolloverIcon(beginGameHoverIcon);
		
		Object mapNames[] = Map.getMapNames().toArray();
		mapList = new JList(mapNames);
		mapList.setSelectedIndex(0);
		mc.setMap(Map.getMapByName((String)mapNames[0]));
		mapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mapList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mc.setMap(Map.getMapByName((String)mapList.getSelectedValue()));
			}
		});
		
		mapListPane = new JScrollPane(mapList);
	}
	
	public GameSetup(GameMain gameMain) {
		this();
		this.gameMain = gameMain;
		
		setupLayout();
		setupButtonActions();
	}

	public void setupLayout(){
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,0,10,0);
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		add(createGameLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		add(mapLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		add(mapListPane, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,20,10,0);
		mc.setPreferredSize(new Dimension(400, 400));
		add(mc, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.ipady = 20;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0,0,0,0);
		add(cancelButton, c);
		
		c.gridx = 3;
		c.gridy = 4;
		c.ipady = 20;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_END;
		add(playButton, c);
	}
	
	public void setupButtonActions() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TitleScreen titleScreen = new TitleScreen(gameMain);
				gameMain.showScreen(titleScreen);
			}
		});
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map m = Map.getMapByName(getMapName());
				Game g = new Game();
				g.setMap(m);
				
				GameController gc = new GameController();
				gc.setGame(g);
				gc.setGameMain(gameMain);
				
				SingleGamePanel gamePanel = new SingleGamePanel(gc);
				
				gameMain.showScreen(gamePanel);
				gc.start();
			}
		});
	}
	
	public String getMapName() {
		return (String) mapList.getSelectedValue();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0 ,0, null);
	}
	
	}
