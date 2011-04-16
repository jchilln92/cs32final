package src.ui.side;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.GameController;
import src.core.Damage;
import src.core.Tower;

/**
 * Panel displaying buttons that allow the user to purchase a tower.
 */
public class TowerPurchasePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameController gc;
	
	private static final String purchaseTowersText = "Purchase Towers:";
	
	private JLabel purchaseTowersLabel;
	
	private JButton towerButton;
	
	public TowerPurchasePanel(GameController controller) {
		super(new GridBagLayout());
		
		gc = controller;
		
		purchaseTowersLabel = new JLabel(purchaseTowersText);

		towerButton = new JButton("purchase");
	
		towerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tower testTower = new Tower();
				testTower.setRadius(4);
				testTower.setPrice(100);
				Damage d = new Damage();
				d.setInstantDamage(.1);
				testTower.setDamage(d);
				
				gc.beginPurchasingTower(testTower);
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		//c.anchor = GridBagConstraints.LINE_END;

		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(purchaseTowersLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(towerButton, c);



	}
}
