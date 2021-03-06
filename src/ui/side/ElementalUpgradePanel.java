package src.ui.side;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.core.IAlignment.Alignment;
import src.ui.ColorConstants;
import src.ui.controller.GameController;

/**
 * Small panel that is used by the TowerUpgradePanel. Contains buttons for the 5 different alignments for a tower
 */
public class ElementalUpgradePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	private JButton neutralButton;
	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;
	private JButton yellowButton;
	
	private JButton[] eButtons;
	
	public ElementalUpgradePanel(GameController gc){
		super(new GridBagLayout());
		
		this.setOpaque(false);
		
		controller = gc;
		
		//Long drawn out process of setting up each of the buttons. Whenever one is pressed, applies the proper alignment
		neutralButton = new JButton();
		neutralButton.setBackground(ColorConstants.neutralColor);
		neutralButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.applyAlignment(Alignment.NEUTRAL);
			}
		});
		
		redButton = new JButton();
		redButton.setBackground(ColorConstants.redColor);
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.applyAlignment(Alignment.RED);
			}
		});
		
		greenButton = new JButton();
		greenButton.setBackground(ColorConstants.greenColor);
		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.applyAlignment(Alignment.GREEN);
			}
		});
		
		blueButton = new JButton();
		blueButton.setBackground(ColorConstants.blueColor);
		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.applyAlignment(Alignment.BLUE);
			}
		});
		
		yellowButton = new JButton();
		yellowButton.setBackground(ColorConstants.yellowColor);
		yellowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.applyAlignment(Alignment.YELLOW);
			}
		});

		eButtons = new JButton[5];
		eButtons[0] = neutralButton;
		eButtons[1] = redButton;
		eButtons[2] = greenButton;
		eButtons[3] = blueButton;
		eButtons[4] = yellowButton;
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		c.insets = new Insets(0, 5, 0, 0);
		add(neutralButton, c);
		c.gridx = 1;
		add(redButton, c);
		c.gridx = 2;
		add(greenButton, c);
		c.gridx = 3;
		add(blueButton, c);
		c.gridx = 4;
		add(yellowButton, c);
	}
	
	public void paintComponent(Graphics g) {
		updateAlignmentButtons();
	}

	/**
	 * Updates whether or not you can purchase an alignment
	 */
	private void updateAlignmentButtons() {
		//create a dummy alignment to check if player can afford as all alignments are of same cost
		Alignment temporaryAlignment = Alignment.NEUTRAL;
		Alignment currentAlignment = controller.getSelectedTower().getAlignment();
	
		int currentAlignmentIndex = 0;
		
		switch (currentAlignment) {
			case NEUTRAL:
				currentAlignmentIndex = 0;
				break;
			case RED:
				currentAlignmentIndex = 1;
				break;
			case GREEN:
				currentAlignmentIndex = 2;
				break;
			case BLUE:
				currentAlignmentIndex = 3;
				break;
			case YELLOW:
				currentAlignmentIndex = 4;
				break;
		}
				
		if (controller.playerCanAfford(temporaryAlignment)) { //if can afford alignment
			for (int x = 0; x < eButtons.length; x++) {
				if (currentAlignmentIndex != x) //check if we haven't already purchased this alignment
					eButtons[x].setEnabled(true);
				else
					eButtons[x].setEnabled(false);
			}
		} else {
			for (int x = 0; x < eButtons.length; x++) {
				eButtons[x].setEnabled(false);
			}
		}
	}
	
}
