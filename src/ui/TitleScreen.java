package src.ui;


import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import src.GameMain;
import src.ui.controller.MultiplayerController;
import src.FilePaths;

/**
 * Displays the title screen and handles all of the button operations of the title screen
 */
public class TitleScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton singlePlayerButton;
	private JButton multiPlayerButton;
	private JButton tutorialButton;
	private JButton quitButton;
	
	private GameMain gameMain;
	
	private ImageIcon bg;
	private ImageIcon singlePlayerImage;
	private ImageIcon singlePlayerPressedImage;
	private ImageIcon singlePlayerHoverImage;
	
	private ImageIcon multiPlayerImage;
	private ImageIcon multiPlayerPressedImage;
	private ImageIcon multiPlayerHoverImage;
	
	private ImageIcon tutorialImage;
	private ImageIcon tutorialPressedImage;
	private ImageIcon tutorialHoverImage;

	private ImageIcon exitImage;
	private ImageIcon exitPressedImage;
	private ImageIcon exitHoverImage;
	
	public TitleScreen(GameMain gameMain) {
		super(null);
		setSize(800, 600);
		this.gameMain = gameMain;
		final GameMain main = this.gameMain;

		//setting up ImageIcons
		bg = new ImageIcon(FilePaths.bgPath + "TOWER_WARS_SCREEN.png");
		singlePlayerImage = new ImageIcon(FilePaths.buttonPath + "SingleButton.png");
		singlePlayerPressedImage = new ImageIcon(FilePaths.buttonPath + "SingleButtonDown.png");
		singlePlayerHoverImage = new ImageIcon(FilePaths.buttonPath + "SingleButtonHover.png");
		
		multiPlayerImage = new ImageIcon(FilePaths.buttonPath + "MultiButton.png");
		multiPlayerPressedImage = new ImageIcon(FilePaths.buttonPath + "MultiButtonDown.png");
		multiPlayerHoverImage = new ImageIcon(FilePaths.buttonPath + "MultiButtonHover.png");

		tutorialImage = new ImageIcon(FilePaths.buttonPath + "TutorialButton.png");
		tutorialPressedImage = new ImageIcon(FilePaths.buttonPath + "TutorialButtonDown.png");
		tutorialHoverImage = new ImageIcon(FilePaths.buttonPath + "TutorialButtonHover.png");

		exitImage = new ImageIcon(FilePaths.buttonPath + "ExitButton.png");
		exitPressedImage = new ImageIcon(FilePaths.buttonPath + "ExitButtonDown.png");
		exitHoverImage = new ImageIcon(FilePaths.buttonPath + "ExitButtonHover.png");
		
		//creating all of the buttons properly
		singlePlayerButton = new JButton(singlePlayerImage);
		singlePlayerButton.setBorder(BorderFactory.createEmptyBorder());
		singlePlayerButton.setContentAreaFilled(false);
		singlePlayerButton.setPressedIcon(singlePlayerPressedImage);
		singlePlayerButton.setRolloverIcon(singlePlayerHoverImage);
	
		multiPlayerButton = new JButton(multiPlayerImage);
		multiPlayerButton.setBorder(BorderFactory.createEmptyBorder());
		multiPlayerButton.setContentAreaFilled(false);
		multiPlayerButton.setPressedIcon(multiPlayerPressedImage);
		multiPlayerButton.setRolloverIcon(multiPlayerHoverImage);

		tutorialButton = new JButton(tutorialImage);
		tutorialButton.setBorder(BorderFactory.createEmptyBorder());
		tutorialButton.setContentAreaFilled(false);
		tutorialButton.setPressedIcon(tutorialPressedImage);
		tutorialButton.setRolloverIcon(tutorialHoverImage);

		quitButton = new JButton(exitImage);
		quitButton.setBorder(BorderFactory.createEmptyBorder());
		quitButton.setContentAreaFilled(false);
		quitButton.setPressedIcon(exitPressedImage);
		quitButton.setRolloverIcon(exitHoverImage);
		//Done painful code
		
		//singlePlayerButton brings user to single player gameSetup screen
		singlePlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameSetup gameSetup = new GameSetup(main);
				main.showScreen(gameSetup);
			}
		});
		
		//multiPlayerButton brings up the multiplayer lobby screen
		multiPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MultiplayerController controller = new MultiplayerController(main);
				controller.showLobby();
			}
		});
		
		tutorialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//since no layout manager used, all of these buttons are added by specific x,y coordinates
		singlePlayerButton.setBounds(100, 250, 280, 60);
		add(singlePlayerButton);
		
		multiPlayerButton.setBounds(100, 320, 280, 60);
		add(multiPlayerButton);
		
		tutorialButton.setBounds(100, 390, 280, 60);
		add(tutorialButton);
		
		quitButton.setBounds(100, 460, 280, 60);
		add(quitButton);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg.getImage(), 0 ,0, null);
	}
}
