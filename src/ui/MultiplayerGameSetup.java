package src.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.ui.controller.MultiplayerController;

public class MultiplayerGameSetup extends GameSetup {
	private static final long serialVersionUID = 1L;
	private MultiplayerController controller;
	
	public MultiplayerGameSetup(MultiplayerController multiController) {
		super(multiController.getGameMain());
		this.controller = multiController;
	}
	
	@Override
	public void setupButtonActions(){
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cancelGameCreation();
			}
		});
		
		playButton.setText("Create Game");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.completeGameCreation();
			}
		});
	}
}
