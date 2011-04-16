package src.ui.side;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	
	private JButton towerButton;
	
	public TowerPurchasePanel(GameController controller) {
		gc = controller;
		
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
		
		add(towerButton);
	}
}
