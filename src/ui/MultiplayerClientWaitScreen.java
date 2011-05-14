package src.ui;

import java.awt.Color;
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
import src.ui.controller.MultiplayerController;

/**
 * Displays the waiting screen for the client before entering a multiplayer game
 */
public class MultiplayerClientWaitScreen extends JPanel {
	private JLabel waitingLabel;
	private JButton cancelButton;
	
	private MultiplayerController controller;
	private ImageIcon background;
	
	private ImageIcon cancelIcon;
	private ImageIcon cancelPressedIcon;
	private ImageIcon cancelHoverIcon;

	
	public MultiplayerClientWaitScreen(MultiplayerController mc) {	
		super(new GridBagLayout());
		this.controller = mc;
		background = new ImageIcon(FilePaths.bgPath + "GenericBGRDsmall.png");
		cancelIcon = new ImageIcon(FilePaths.buttonPath + "CancelButton.png");
		cancelPressedIcon = new ImageIcon(FilePaths.buttonPath + "CancelButtonDown.png");
		cancelHoverIcon = new ImageIcon(FilePaths.buttonPath + "CancelButtonHover.png");

		waitingLabel = new JLabel("Waiting for response from host...");
		waitingLabel.setForeground(Color.WHITE);
		
		cancelButton = new JButton(cancelIcon);
		cancelButton.setBorder(BorderFactory.createEmptyBorder());
		cancelButton.setContentAreaFilled(false);
		cancelButton.setPressedIcon(cancelPressedIcon);
		cancelButton.setRolloverIcon(cancelHoverIcon);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.quitNetworkGame();
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 0, 0, 0);	
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		add(waitingLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(cancelButton, c);
		

	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0 ,0, null);
	}
}
