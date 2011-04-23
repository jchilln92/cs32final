package src.ui.side;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.core.Tower;

/**
 * A reusable panel that is used to display the stats of a given tower.
 * @see TowerUpgradePanel
 * @see TowerPurchasePanel
 */
public class TowerStatsPanel extends JPanel {
	private Tower tower; // the tower we are currently displaying
	
	private JLabel towerNameLabel;
	private JLabel damageLabel;
	private JLabel rangeLabel;
	private JLabel abilitiesLabel;
	private JLabel costLabel;
	
	public TowerStatsPanel() {
		super(new GridBagLayout());
		
		tower = null;
		
		towerNameLabel = new JLabel(" ");
		damageLabel = new JLabel(" ");
		rangeLabel = new JLabel(" ");
		abilitiesLabel = new JLabel(" ");
		costLabel = new JLabel(" ");
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		add(towerNameLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(damageLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(rangeLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(abilitiesLabel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(costLabel, c);
	}
	
	public void setTower(Tower t) {
		tower = t;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (tower == null) {
			towerNameLabel.setText(" ");
			damageLabel.setText(" ");
			rangeLabel.setText(" ");
			abilitiesLabel.setText(" ");
			costLabel.setText(" ");
		} else {
			towerNameLabel.setText("Type: " + tower.getType().toString());
			damageLabel.setText("Damage: " + tower.getDamage().getInstantDamage());
			rangeLabel.setText("Range: " + Double.toString(tower.getRadius()));
			abilitiesLabel.setText("Abilities: ");
			costLabel.setText("Cost: ");
		}
	}
}
