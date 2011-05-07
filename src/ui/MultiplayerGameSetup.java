package src.ui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import src.ui.controller.MultiplayerController;

public class MultiplayerGameSetup extends GameSetup {
	private static final long serialVersionUID = 1L;
	
	private MultiplayerController controller;
	private JTextField nameField;
	public MultiplayerGameSetup(MultiplayerController multiController) {
		this.controller = multiController;
		
		setupLayout();
		setupButtonActions();
		reset();
	}
	
	public void reset() {
		playButton.setEnabled(false);
		nameField.setText("");
		mapList.setSelectedIndex(0);
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
		
		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// kind of hacky code
				JTextField field = (JTextField) e.getSource();

				int code = e.getKeyCode();
				
				String s = field.getText();
				s = s.replaceAll("[^a-zA-Z0-9' ]", "");
			    field.setText(s);
				int length = s.length();

				
				if (length > 0 && code == KeyEvent.VK_BACK_SPACE) {
					length--;
				} else {
					length++;
				}

				if (length <= 0) {
					playButton.setEnabled(false);
				} else {
					playButton.setEnabled(true);
				}
			}
		});
		
		c.insets = new Insets(10, 0, 10, 0);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		add(nameField, c);
	}
	
	@Override
	public void setupButtonActions(){
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cancelGameCreation();
			}
		});
		
		playButton.setText("");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.completeGameCreation();
			}
		});
	}
	
	public String getGameName() {
		return nameField.getText();
	}
}
