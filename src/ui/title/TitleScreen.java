package src.ui.title;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.GameMain;

public class TitleScreen extends JPanel {

	private JLabel titleLabel;
	private JButton splayerButton;
	private JButton mplayerButton;
	private JButton tutorialButton;
	private JButton quitButton;
	
	private GameMain gm;
	
	public TitleScreen(GameMain gameMain) {
		super(new GridBagLayout());
		setSize(800, 600);
	
		gm = gameMain;
		titleLabel = new JLabel("Tower Defense");
		titleLabel.setFont(new Font("Dialog.bold", 10, 64));
		titleLabel.setForeground(Color.RED);
		System.out.println(titleLabel.getFont().getStyle());
		splayerButton = new JButton("Single Player Mode");
		mplayerButton = new JButton("Multi-player Mode");
		tutorialButton = new JButton("Look at an image to get help on how to play Tower Defense janx Mode");
		quitButton = new JButton("Quitter Mode");

		splayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gm.createGame();
			}
		});
		mplayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		c.weighty = 1;
		c.gridx = 0;
		
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(titleLabel, c);
		
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(splayerButton, c);

		c.gridy = 2;
		c.fill = GridBagConstraints.NONE;
		add(mplayerButton, c);	
		
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		add(tutorialButton, c);	
		
		c.gridy = 4;
		c.fill = GridBagConstraints.NONE;
		add(quitButton, c);	
		
	}
}
