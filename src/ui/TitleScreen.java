package src.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.GameMain;
import src.ui.controller.MultiplayerController;

public class TitleScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel titleLabel;
	private JButton singlePlayerButton;
	private JButton multiPlayerButton;
	private JButton tutorialButton;
	private JButton quitButton;
	
	private GameMain gameMain;
	
	public TitleScreen(GameMain gameMain) {
		super(new GridBagLayout());
		setSize(800, 600);
	
		this.gameMain = gameMain;
		titleLabel = new JLabel("Tower Defense");
		titleLabel.setFont(new Font("Dialog.bold", 10, 64));
		titleLabel.setForeground(Color.RED);
		
		singlePlayerButton = new JButton("Single Player Mode");
		multiPlayerButton = new JButton("Multi-player Mode");
		tutorialButton = new JButton("Look at an image to get help on how to play Tower Defense janx Mode");
		quitButton = new JButton("Quitter Mode");

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
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;		
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(50,0,200,0);
		add(titleLabel, c);

		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(20,0,20,0);
		add(singlePlayerButton, c);

		c.gridy = 2;
		c.fill = GridBagConstraints.NONE;
		add(multiPlayerButton, c);	
		
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		add(tutorialButton, c);	
		
		c.gridy = 4;
		c.fill = GridBagConstraints.NONE;
		add(quitButton, c);	
		
	}
}
