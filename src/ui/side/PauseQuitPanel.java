package src.ui.side;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.FilePaths;
import src.ui.CreepDrawer;
import src.ui.controller.GameController;
import src.ui.controller.MultiplayerController;

/**
 * Small panel for displaying the pause, quit, and toggle health buttons
 */
public class PauseQuitPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameController gc;
	private MultiplayerController multiController;
	
	private JButton heartButton;
	private JButton pauseButton;
	private JButton quitButton;
	private JOptionPane quitPopup;
	
	private ImageIcon pauseIcon;
	private ImageIcon pauseHoverIcon;
	private ImageIcon pausePressedIcon;
	private ImageIcon resumeIcon;
	private ImageIcon resumeHoverIcon;
	private ImageIcon resumePressedIcon;
	private ImageIcon quitIcon;
	private ImageIcon quitHoverIcon;
	private ImageIcon quitPressedIcon;
	private ImageIcon heartIcon;
	private ImageIcon heartPressedIcon;
	
	public PauseQuitPanel(GameController controller) {
		this(controller, false);
	}
	
	public PauseQuitPanel(GameController controller, boolean isMultiplayer) {
		super(new GridBagLayout());
		this.gc = controller;

		pauseIcon = new ImageIcon(FilePaths.buttonPath + "PauseButton.png");
		pauseHoverIcon = new ImageIcon(FilePaths.buttonPath + "PauseButtonHover.png");
		pausePressedIcon = new ImageIcon(FilePaths.buttonPath + "PauseButtonDown.png");
		
		resumeIcon = new ImageIcon(FilePaths.buttonPath + "ResumeButton.png");
		resumeHoverIcon = new ImageIcon(FilePaths.buttonPath + "ResumeButtonHover.png");
		resumePressedIcon = new ImageIcon(FilePaths.buttonPath + "ResumeButtonDown.png");
		
		quitIcon = new ImageIcon(FilePaths.buttonPath + "QuitButton.png");
		quitHoverIcon = new ImageIcon(FilePaths.buttonPath + "QuitButtonHover.png");
		quitPressedIcon = new ImageIcon(FilePaths.buttonPath + "QuitButtonDown.png");

		heartIcon = new ImageIcon(FilePaths.buttonPath + "HeartButton.png");
		heartPressedIcon = new ImageIcon(FilePaths.buttonPath + "HeartButtonDown.png");

		
		
		heartButton = new JButton(heartIcon);
		heartButton.setBorder(BorderFactory.createEmptyBorder());
		heartButton.setContentAreaFilled(false);
		heartButton.setPressedIcon(heartPressedIcon);
		
		//when pressed, toggles health bars for creeps
		heartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreepDrawer.toggleDrawHealthBar(!CreepDrawer.getDrawHealthBar());
			}
		});		
		
		pauseButton = new JButton(pauseIcon);
		pauseButton.setBorder(BorderFactory.createEmptyBorder());
		pauseButton.setContentAreaFilled(false);
		pauseButton.setPressedIcon(pausePressedIcon);
		pauseButton.setRolloverIcon(pauseHoverIcon);
		
		pauseButton.setActionCommand("pause");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("pause")) {
					gc.togglePause(true);
					pauseButton.setActionCommand("resume");
					pauseButton.setIcon(resumeIcon);
					pauseButton.setPressedIcon(resumePressedIcon);
					pauseButton.setRolloverIcon(resumeHoverIcon);
				} else if (e.getActionCommand().equals("resume")) {
					gc.togglePause(false);
					pauseButton.setActionCommand("pause");
					pauseButton.setIcon(pauseIcon);
					pauseButton.setPressedIcon(pausePressedIcon);
					pauseButton.setRolloverIcon(pauseHoverIcon);
				}
			}
		});
		
		quitButton = new JButton(quitIcon);
		quitButton.setBorder(BorderFactory.createEmptyBorder());
		quitButton.setContentAreaFilled(false);
		quitButton.setPressedIcon(quitPressedIcon);
		quitButton.setRolloverIcon(quitHoverIcon);
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makePopup();
			}
		});
		
		//Laying out everything. Note that if the game is multiplayer, the pause button is not added
		GridBagConstraints c = new GridBagConstraints();
		if (!isMultiplayer) {
			c.weightx = 1;
			c.gridx = 0;
			c.gridy = 0;
			c.fill = GridBagConstraints.NONE;
			c.insets.set(0, 10, 0, 0);
			add(heartButton, c);
			
			c.gridx = 1;
			c.gridy = 0;
			c.insets.set(0, 0, 0, 0);
			add(pauseButton, c);
			
			c.gridx = 2;
			c.gridy = 0;
			c.insets.set(0, 0, 0, 10);
			add(quitButton, c);
		} else {
			c.weightx = 1;
			c.gridx = 0;
			c.gridy = 0;
			c.fill = GridBagConstraints.NONE;
			add(heartButton, c);
			
			c.gridx = 1;
			c.gridy = 0;
			add(quitButton, c);
		}
		
	}
	
	public PauseQuitPanel(GameController gc, MultiplayerController multiController) {
		this(gc, true);
		this.multiController = multiController;
	}
	/**
	 * Called whenever the quit button is pressed. Prompt pops up asking user whether they actually want to quit.
	 */
	public void makePopup(){
		Object[] options = {"I want to quit.", "I don't want to quit."};
		int n = JOptionPane.showOptionDialog(this, 
											"Are you sure you want to quit current game?",
											"Quit?",
											JOptionPane.YES_NO_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE,
											null,
											options,
											options[1]);
		
		if (multiController == null) {
			boolean pauseCheck = gc.getPaused();
			gc.togglePause(true);
			
			if (n == 0) { //quitting the game
				gc.quit();
			} else{ //not quitting the game
				if (!pauseCheck)
					gc.togglePause(false);
			}
		} else if (n == 0) {
			multiController.quitNetworkGame();
		}
	}
	

}

