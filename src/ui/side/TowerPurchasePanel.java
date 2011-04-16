package src.ui.side;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class TowerPurchasePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameController gc;
	
	private static final String purchaseTowersText = "Purchase Towers:";
	
	private JLabel purchaseTowersLabel;
	
	private ArrayList<JButton> towerButtons;
	
	private JButton testButton;
	
	public TowerPurchasePanel(GameController controller) {
		super(new GridBagLayout());
		
		gc = controller;
		
		purchaseTowersLabel = new JLabel(purchaseTowersText);
		
		towerButtons = new ArrayList<JButton>();
		
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(purchaseTowersLabel, c);
		String path = "assets/tower-icon1.png";
		ImageIcon towerIcon = new ImageIcon(path);
		for(int index = 0; index < 8; index++){
			JButton towerButton = new JButton(towerIcon);
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
			
			towerButtons.add(towerButton);
			c.gridx = 0+index/3;
			c.gridy = 1+index%3;
			c.fill = GridBagConstraints.NONE;
			add(towerButton, c);
		}
		testButton = new JButton(towerIcon);
		c.gridx = 2;
		c.gridy = 4;
		c.fill = GridBagConstraints.NONE;
		add(testButton);		
	
		
		




	}
}
