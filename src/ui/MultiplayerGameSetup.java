package src.ui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.ui.controller.MultiplayerController;

public class MultiplayerGameSetup extends GameSetup {
	private static final long serialVersionUID = 1L;
	private MultiplayerController controller;
	
	public MultiplayerGameSetup(MultiplayerController multiController) {
		this.controller = multiController;
		
		setupLayout();
		setupButtonActions();
	}
	
	@Override
	public void setupLayout() {
		super.setupLayout();
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		add(createNameLabel, c);
		
		c.insets = new Insets(10, 0, 10, 0);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		add(createNameField, c);
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
	
	public String getGameName() {
		return createNameField.getText();
	}
}
