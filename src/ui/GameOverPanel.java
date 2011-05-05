package src.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.ui.controller.GameController;

public class GameOverPanel extends JPanel {
	private static final String gameOverText = "GAME OVER";
	private static final String returnToTitleText = "Return to Main Menu";
	
	private JLabel gameOverLabel;
	private JButton returnButton;
	
	public GameOverPanel(GameController c) {
		final GameController controller = c;
		
		gameOverLabel = new JLabel(gameOverText);
		gameOverLabel.setForeground(Color.RED);
		gameOverLabel.setFont(new Font("Dialog.bold", 10, 32));
		
		returnButton = new JButton(returnToTitleText);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.quit();
			}
		});
		
		add(gameOverLabel);
		add(returnButton);
	}
}
