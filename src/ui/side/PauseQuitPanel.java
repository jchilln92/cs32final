package src.ui.side;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.FilePaths;
import src.ui.CreepDrawer;
import src.ui.controller.GameController;

/**
 * This is a panel that displays information on a player's gold and health,
 * which is typically shown in the sidebar.
 */
public class PauseQuitPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameController gc;
	
	private JButton heartButton;
	private JButton pauseButton;
	private JButton quitButton;
	
	private JOptionPane quitPopup;
	
	public PauseQuitPanel(GameController controller) {
		super(new GridBagLayout());
		
		this.gc = controller;

		String path = FilePaths.imgPath + "tower-icon"+1+".png";
		
		ImageIcon heartIcon = new ImageIcon(path);
		heartButton = new JButton(heartIcon);
		heartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreepDrawer.toggleDrawHealthBar(!CreepDrawer.getDrawHealthBar());
			}
		});		
		
		pauseButton = new JButton("Pause");
		pauseButton.setActionCommand("pause");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("pause")) {
					gc.togglePause(true);
					pauseButton.setActionCommand("resume");
					pauseButton.setText("Resume");
				} else if (e.getActionCommand().equals("resume")) {
					gc.togglePause(false);
					pauseButton.setActionCommand("pause");
					pauseButton.setText("Pause");
				}
			}
		});
		
		quitButton = new JButton("Quit");
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makePopup();
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(heartButton, c);
		
		c.gridx = 1;
		c.gridy = 0;
		add(pauseButton, c);
		c.gridx = 2;
		c.gridy = 0;
		add(quitButton, c);
		
	}
	
	public void makePopup(){
		boolean check = false;
		if(gc.getPaused()){
			check = true;
		}
		gc.togglePause(true);
		Object[] options = {"I want to quit.", "I don't want to quit."};
		int n = JOptionPane.showOptionDialog(this, //we need to replace this with the main panel
		"Are you sure you want to quit current game?",
		"There is racism in the world.",
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		options,
		options[1]);
		
		if(n == 0){ //quitting the game
			gc.getGameMain().resetGame();
		} else{ //not quitting the game
			if(!check)
				gc.togglePause(false);
		}
	}
	

}

