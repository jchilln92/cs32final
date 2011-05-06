package src.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.GameMain;
import src.ui.controller.MultiplayerController;
import src.FilePaths;

public class TitleScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//private JLabel titleLabel;
	private JButton singlePlayerButton;
	private JButton multiPlayerButton;
	private JButton tutorialButton;
	private JButton quitButton;
	
	private GameMain gameMain;
	
	private ImageIcon bg;
	private ImageIcon singlePlayerImage;
	private ImageIcon singlePlayerPressedImage;
	private ImageIcon multiPlayerImage;
	private ImageIcon multiPlayerPressedImage;
	private ImageIcon tutorialImage;
	private ImageIcon tutorialPressedImage;
	private ImageIcon exitImage;
	private ImageIcon exitPressedImage;
	
	public TitleScreen(GameMain gameMain) {
		super(null);
		//super(new GridBagLayout());
		setSize(800, 600);
		
		bg = new ImageIcon(FilePaths.bgPath + "TOWER_WARS_SCREEN.png");
		//bg = Toolkit.getDefaultToolkit().createImage(FilePaths.bgPath + "TOWER_WARS_SCREEN.png");
		singlePlayerImage = new ImageIcon(FilePaths.buttonPath + "SingleButton.png");
		singlePlayerPressedImage = new ImageIcon(FilePaths.buttonPath + "SingleButtonDown.png");
		
		multiPlayerImage = new ImageIcon(FilePaths.buttonPath + "MultiButton.png");
		multiPlayerPressedImage = new ImageIcon(FilePaths.buttonPath + "MultiButtonDown.png");
		
		tutorialImage = new ImageIcon(FilePaths.buttonPath + "TutorialButton.png");
		tutorialPressedImage = new ImageIcon(FilePaths.buttonPath + "TutorialButtonDown.png");

		exitImage = new ImageIcon(FilePaths.buttonPath + "ExitButton.png");
		exitPressedImage = new ImageIcon(FilePaths.buttonPath + "ExitButtonDown.png");

		this.gameMain = gameMain;
		//titleLabel = new JLabel("Tower Defense");
		//titleLabel.setFont(new Font("Dialog.bold", 10, 64));
		//titleLabel.setForeground(Color.RED);
		
		singlePlayerButton = new JButton(singlePlayerImage);
		singlePlayerButton.setBorder(BorderFactory.createEmptyBorder());
		singlePlayerButton.setContentAreaFilled(false);
		singlePlayerButton.setPressedIcon(singlePlayerPressedImage);
	
		multiPlayerButton = new JButton(multiPlayerImage);
		multiPlayerButton.setBorder(BorderFactory.createEmptyBorder());
		multiPlayerButton.setContentAreaFilled(false);
		multiPlayerButton.setPressedIcon(multiPlayerPressedImage);
		
		tutorialButton = new JButton(tutorialImage);
		tutorialButton.setBorder(BorderFactory.createEmptyBorder());
		tutorialButton.setContentAreaFilled(false);
		tutorialButton.setPressedIcon(tutorialPressedImage);
		
		quitButton = new JButton(exitImage);
		quitButton.setBorder(BorderFactory.createEmptyBorder());
		quitButton.setContentAreaFilled(false);
		quitButton.setPressedIcon(exitPressedImage);

		final GameMain main = this.gameMain;
		
		singlePlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameSetup gameSetup = new GameSetup(main);
				main.showScreen(gameSetup);
			}
		});
		
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
		
		//add(singlePlayerButton);
		//singlePlayerButton.setLocation(200, 200);
		
		singlePlayerButton.setBounds(300, 300, singlePlayerButton.getWidth(), singlePlayerButton.getHeight());
		add(singlePlayerButton);
		
		/*
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;		
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(30,0,60,0);
		//add(titleLabel, c);

		c.gridy = 1;
		c.ipadx = 0;
		c.ipady = 0;
		//c.fill = GridBagConstraints.HORIZONTAL;
		//c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,30,0);
		add(singlePlayerButton, c);

		c.gridy = 2;
		add(multiPlayerButton, c);	
		
		c.gridy = 3;
		add(tutorialButton, c);	
		
		c.gridy = 4;
		add(quitButton, c);	
		*/
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg.getImage(), 0 ,0, null);
	}
}
