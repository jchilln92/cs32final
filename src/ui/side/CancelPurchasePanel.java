package src.ui.side;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.GameController;
import src.core.Damage;
import src.core.Tower;

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
	
	private JButton cancelButton;
	
	public CancelPurchasePanel(GameController controller) {
		super(new GridBagLayout());
		
		gc = controller;
		
		purchaseLabel = new JLabel("Purchasing");
		towerNameLabel = new JLabel(gc.getPlacingTower() + ":");
		tipLabel = new JLabel(tipText);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(purchaseLabel, c);
		
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(towerNameLabel, c);
		
		c.gridy = 2;
		c.fill = GridBagConstraints.NONE;
		add(tipLabel, c);
		
		
	}
}
