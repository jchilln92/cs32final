package src.ui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import src.FilePaths;
import src.ui.controller.MultiplayerController;

/**
 * Displays the multiplayer game setup screen (adds a game name textfield).
 */
public class MultiplayerGameSetup extends GameSetup {
	private static final long serialVersionUID = 1L;
	
	private MultiplayerController controller;
	private JTextField nameField;
	
	private ImageIcon createIcon;
	private ImageIcon createHoverIcon;
	private ImageIcon createPressedIcon;
	private ImageIcon createDisabledIcon;
	
	public MultiplayerGameSetup(MultiplayerController multiController) {
		this.controller = multiController;
		
		//setting up image icons for create game button (from start game in single player)
		createIcon = new ImageIcon(FilePaths.buttonPath + "SlimCreateButton.png");
		createPressedIcon = new ImageIcon(FilePaths.buttonPath + "SlimCreateButtonDown.png");
		createHoverIcon = new ImageIcon(FilePaths.buttonPath + "SlimCreateButtonHover.png");
		createDisabledIcon = new ImageIcon(FilePaths.buttonPath + "SlimCreateButtonDisabled.png");
		playButton = new JButton(createIcon);
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		playButton.setPressedIcon(createPressedIcon);
		playButton.setRolloverIcon(createHoverIcon);
		playButton.setDisabledIcon(createDisabledIcon);
		
		setupLayout();
		setupButtonActions();
		reset();
	}
	/**
	 * resets the screen to its original state (so when this screen is revisited later, 
	 * old game name and map choice do not remain.
	 */
	public void reset() {
		playButton.setEnabled(false);
		nameField.setText("");
		mapList.setSelectedIndex(0);
	}
	
	/**
	 * sets up layout using GridBag
	 */
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
		// Allows text entry into the game name field. disables the create game button if game name is empty
		nameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char[] key = {e.getKeyChar()};
				String charString = new String(key);
				
				if (charString.matches("[^a-zA-Z0-9' ]") && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				} else {
					int length = nameField.getText().length();
					
					if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
						length++;
					}
					
					if (length > 0) {
						playButton.setEnabled(true);
					} else {
						playButton.setEnabled(false);
					}
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
		//cancelButton takes user back to multiplayer lobby screen
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.cancelGameCreation();
			}
		});
		
		playButton.setText("");
		//playButton brings user to multiplayer host wait screen
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
