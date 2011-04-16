package src.ui.side;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

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
	
	public PauseQuitPanel(GameController controller) {
		super(new GridBagLayout());
		
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gc.pause(true);
				pauseButton.setText("Resume");
			}
		});
		
		quitButton = new JButton("Quit");
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//do stuff
			}
		});
		

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(pauseButton, c);

		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(quitButton, c);
	}
	

}
