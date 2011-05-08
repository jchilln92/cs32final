package src.ui;

import java.awt.Color;
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
import javax.swing.JPanel;

import src.FilePaths;
import src.GameMain;

public class GameOverPanel extends JPanel {
	
	private JLabel gameOverLabel;
	private JButton returnButton;
	
	private ImageIcon loseBackground;
	private ImageIcon mainMenuIcon;
	private ImageIcon mainMenuPressedIcon;
	private ImageIcon mainMenuHoverIcon;

	public GameOverPanel(GameMain gm) {
		super(null);
		
		final GameMain main = gm;
		
		loseBackground = new ImageIcon(FilePaths.bgPath + "GAME_OVER_SCREEN.png");
		mainMenuIcon = new ImageIcon(FilePaths.buttonPath + "MainMenuButton.png");
		mainMenuPressedIcon = new ImageIcon(FilePaths.buttonPath + "MainMenuButtonDown.png");
		mainMenuHoverIcon = new ImageIcon(FilePaths.buttonPath + "MainMenuButtonHover.png");
				
		returnButton = new JButton(mainMenuIcon);
		returnButton.setBorder(BorderFactory.createEmptyBorder());
		returnButton.setContentAreaFilled(false);
		returnButton.setPressedIcon(mainMenuPressedIcon);
		returnButton.setRolloverIcon(mainMenuHoverIcon);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showScreen(new TitleScreen(main));
			}
		});
		
		returnButton.setBounds(530,450,140,40);
		add(returnButton);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(loseBackground.getImage(), 0 ,0, null);
	}
	
}
