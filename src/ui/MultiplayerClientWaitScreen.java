package src.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.ui.controller.MultiplayerController;

public class MultiplayerClientWaitScreen extends JPanel {
	private JLabel waitingLabel;
	private JButton cancelButton;
	
	private MultiplayerController controller;
	
	public MultiplayerClientWaitScreen(MultiplayerController c) {		
		this.controller = c;
		
		waitingLabel = new JLabel("Waiting for response from host...");
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.quitNetworkGame();
			}
		});
		
		add(waitingLabel);
		add(cancelButton);
	}
}
