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

import src.ui.controller.GameController;

public class WinPanel extends JPanel{
	private static final String winText = "YOU WIN";
	private static final String returnToTitleText = "Return to Main Menu";
	
	private JLabel winLabel;
	private JButton returnButton;
	
	public WinPanel(GameController gc) {
		super(new GridBagLayout());
		final GameController controller = gc;
		
		winLabel = new JLabel(winText);
		winLabel.setForeground(Color.BLUE);
		winLabel.setFont(new Font("Dialog.bold", 10, 32));
		
		returnButton = new JButton(returnToTitleText);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.quit();
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(40, 0, 40, 0);
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(winLabel, c);
		
		c.gridy = 1;
		add(returnButton, c);
	}
}


