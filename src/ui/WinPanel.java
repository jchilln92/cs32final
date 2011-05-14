package src.ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import src.FilePaths;
import src.ui.controller.MultiplayerController;

/**
 * Displays the winner screen when a player has won a multiplayer game
 */

public class WinPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton returnButton;
	
	private ImageIcon winBackground;
	private ImageIcon mainMenuIcon;
	private ImageIcon mainMenuPressedIcon;
	private ImageIcon mainMenuHoverIcon;
	
	public WinPanel(MultiplayerController gc) {
		super(null);
		final MultiplayerController controller = gc;
		
		winBackground = new ImageIcon(FilePaths.bgPath + "WIN_SCREEN.png");
		mainMenuIcon = new ImageIcon(FilePaths.buttonPath + "MainMenuButton.png");
		mainMenuPressedIcon = new ImageIcon(FilePaths.buttonPath + "MainMenuButtonDown.png");
		mainMenuHoverIcon = new ImageIcon(FilePaths.buttonPath + "MainMenuButtonHover.png");
		
		returnButton = new JButton(mainMenuIcon);
		returnButton.setBorder(BorderFactory.createEmptyBorder());
		returnButton.setContentAreaFilled(false);
		returnButton.setPressedIcon(mainMenuPressedIcon);
		returnButton.setRolloverIcon(mainMenuHoverIcon);
		
		//returnButton brings user back to title screen
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.exitLobby();
			}
		});
		
		returnButton.setBounds(530,450,140,40);
		add(returnButton);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(winBackground.getImage(), 0 ,0, null);
	}
}


