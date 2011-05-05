package src.ui.side;

import java.awt.Color;
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


public class ElementalUpgradePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	private JButton neutralButton;
	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;
	private JButton yellowButton;
	
	public ElementalUpgradePanel(GameController gc){
		super(new GridBagLayout());
		
		controller = gc;
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
