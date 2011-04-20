package src.ui.side;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.border.Border;

import src.GameController;

/**
 * This is a panel that displays information on a player's gold and health,
 * which is typically shown in the sidebar.
 */
public class PauseQuitPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameController gc;
	
	private JButton pauseButton;
	private JButton quitButton;
	
	private JOptionPane quitPopup;
	
	
	public PauseQuitPanel(GameController controller) {
		super(new GridBagLayout());
		
		this.gc = controller;
	
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
				gc.togglePause(true);
				makePopup();
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(pauseButton, c);

		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(quitButton, c);
	}
	public void makePopup(){
		Object[] options = {"That is SO Raven.",
        "Totes inapropro, Raven!"};
		int n = JOptionPane.showOptionDialog(this, //we need to replace this with the main panel
		"Are you sure you want to quit current game?",
		"There is racism in the world.",
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		options,
		options[1]);
		if(n == 0){ //quitting the game

		}
		else{ //not quitting the game
			gc.togglePause(false);
		}
	}
	

}

