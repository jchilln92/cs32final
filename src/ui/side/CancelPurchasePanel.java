package src.ui.side;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.ui.controller.GameController;

/**
 * Panel displaying buttons that allow the user to purchase a tower.
 */
public class CancelPurchasePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameController gc;
	
	private static final String tipText = "<html>Tip: pressing<br>Esc will cancel<br>your purchase.</html>";
	private JLabel purchaseLabel;
	private JLabel towerNameLabel;
	private JLabel tipLabel;
	private JLabel towerCostLabel;
	
	private JButton cancelButton;
	
	public CancelPurchasePanel(GameController controller) {
		super(new GridBagLayout());
		
		gc = controller;
		
		purchaseLabel = new JLabel("Purchasing");
		towerNameLabel = new JLabel();
		towerCostLabel = new JLabel();
		
		if (gc.getPlacingTower() == null)
			towerNameLabel.setText("Magical Tower:");
		else
			towerNameLabel.setText(gc.getPlacingTower().getType() + ":");

		tipLabel = new JLabel(tipText);
		
		cancelButton = new JButton("Cancel Purchase");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gc.cancelTowerPurchase();
			}
		});
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(purchaseLabel, c);
		
		c.gridy = 1;
		add(towerNameLabel, c);
		
		c.gridy = 2;
		c.insets.set(20, 0, 0, 0);
		add(towerCostLabel, c);

		c.gridy = 3;
		c.insets.set(5, 0, 0, 0);
		add(cancelButton,c);
		
		c.gridy = 5;
		c.insets.set(40, 0, 0, 0);
		add(tipLabel, c);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// make sure that when this view comes to the front and is redrawn, the name of the
		// tower being placed is displayed
		if (gc.getPlacingTower() == null) {
			towerNameLabel.setText("Magical Tower:");
			towerCostLabel.setText("");
		}
		else {
			towerNameLabel.setText(gc.getPlacingTower().getType() + ":");
			towerCostLabel.setText("Price of tower: " + gc.getPlacingTower().getPrice());
		}
	}
}
