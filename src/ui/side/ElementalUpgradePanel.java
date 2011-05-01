package src.ui.side;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.ui.ColorConstants;


public class ElementalUpgradePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton neutralButton;
	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;
	private JButton yellowButton;
	
	public ElementalUpgradePanel(){
		super(new GridBagLayout());
		
		neutralButton = new JButton();
		neutralButton.setBackground(ColorConstants.neutralColor);
		
		redButton = new JButton();
		redButton.setBackground(ColorConstants.redColor);
		
		greenButton = new JButton();
		greenButton.setBackground(ColorConstants.greenColor);
		
		blueButton = new JButton();
		blueButton.setBackground(ColorConstants.blueColor);
		
		yellowButton = new JButton();
		yellowButton.setBackground(ColorConstants.yellowColor);
		
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
}
