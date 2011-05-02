package src.ui.creepside;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.core.Creep;
import src.core.IAlignment;
import src.ui.ColorConstants;
import src.ui.side.TowerStatsPanel;

public class CreepInfoPurchasePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Creep c;
	
	private JButton neutralButton;
	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;
	private JButton yellowButton;
	private JButton buyButton;

	
	public CreepInfoPurchasePanel() {
		super(new GridBagLayout());
		
	
		neutralButton = new JButton();
		neutralButton.setBackground(ColorConstants.neutralColor);
		neutralButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(creep != null)
				//	creep.setAlignment(IAlignment.Alignment.NEUTRAL);
			}
		});
		
		redButton = new JButton();
		redButton.setBackground(ColorConstants.redColor);
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});		
		greenButton = new JButton();
		greenButton.setBackground(ColorConstants.greenColor);
		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});			
		blueButton = new JButton();
		blueButton.setBackground(ColorConstants.blueColor);
		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});			
		yellowButton = new JButton();
		yellowButton.setBackground(ColorConstants.yellowColor);
		yellowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});			
		buyButton = new JButton("Buy");
		buyButton.setBackground(Color.ORANGE);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(0, 5, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		add(neutralButton, c);
		c.gridx = 1;
		add(redButton, c);
		c.gridx = 2;
		add(greenButton, c);
		c.gridx = 3;
		add(blueButton, c);
		c.gridx = 4;
		add(yellowButton, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		c.gridheight = 3;
		add(buyButton, c);
	}
	
	
}
