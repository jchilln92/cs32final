package src.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.GameMain;
import src.net.AvailableGame;
import src.net.LobbyManager;
import src.ui.controller.MultiplayerController;

public class MultiplayerGameSetup extends GameSetup {
	private MultiplayerController controller;
	
	public MultiplayerGameSetup(MultiplayerController multiController) {
		super(multiController.getGameMain());
		this.controller = multiController;
	}
	
	@Override
	public void setupButtonActions(){
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//gm.showTitleScreen();
			}
		});
		
		playButton.setText("Create Game");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.hostNewGame();
			}
		});
	}
}
