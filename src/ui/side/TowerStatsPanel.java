package src.ui.side;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.core.Tower;
import src.core.Upgrade;
import src.core.IAlignment.Alignment;

/**
 * A reusable panel that is used to display the stats of a given tower.
 * @see TowerUpgradePanel
 * @see TowerPurchasePanel
 */
public class TowerStatsPanel extends JPanel {
	private Tower tower; // the tower we are currently displaying
	private Upgrade upgrade; // an upgrade that should be applied to the tower's stats before showing them
	
	private JLabel towerNameLabel;
	private JLabel damageLabel;
	private JLabel rangeLabel;
	private JLabel abilitiesLabel;
	private JLabel costLabel;
//	private JLabel descriptionLabel;
	private JLabel firePeriodLabel;
	
	private JLabel damageChangeLabel;
	private JLabel rangeChangeLabel;
	private JLabel abilitiesChangeLabel;
	
	public TowerStatsPanel() {
		super(new GridBagLayout());
		
		tower = null;
		upgrade = null;
		
		towerNameLabel = new JLabel(" ");
		damageLabel = new JLabel(" ");
		rangeLabel = new JLabel(" ");
		abilitiesLabel = new JLabel(" ");
		costLabel = new JLabel(" ");
//		descriptionLabel = new JLabel(" ");
		firePeriodLabel = new JLabel(" ");
		
		damageChangeLabel = new JLabel(" ");
		rangeChangeLabel = new JLabel(" ");
		abilitiesChangeLabel = new JLabel(" ");
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		//c.fill = GridBagConstraints.HORIZONTAL;
		add(towerNameLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(damageLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		add(damageChangeLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(rangeLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		add(rangeChangeLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(abilitiesLabel, c);
		
		c.gridx = 1;
		c.gridy = 3;
		add(abilitiesChangeLabel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(costLabel, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(firePeriodLabel, c);
		
	}
	
	public void setTower(Tower t) {
		tower = t;
		
		if (tower == null) {
			towerNameLabel.setText(" ");
			damageLabel.setText(" ");
			rangeLabel.setText(" ");
			abilitiesLabel.setText(" ");
			costLabel.setText(" ");
//			descriptionLabel.setText(" ");
			firePeriodLabel.setText(" ");
		} else {
			towerNameLabel.setText("Type: " + tower.getType().toString());
			double damage = tower.getDamage().getInstantDamage();
			String damageText = (int)(damage) + "." + (int)((damage - (int)damage)*10) + (int)((damage*10 - (int)(damage*10))*10);
			damageLabel.setText("Damage: " + damageText);
			double radius = tower.getRadius();
			String radiusText = (int)(radius) + "." + (int)((radius - (int)radius)*10) + (int)((radius*10 - (int)(radius*10))*10);
			rangeLabel.setText("Range: " + radiusText);
			abilitiesLabel.setText("Abilities: ");
			costLabel.setText("Cost: " + (int)tower.getPrice());//Double.toString(tower.getPrice()));
//			descriptionLabel.setText(tower.getDescription());
			firePeriodLabel.setText("Fire Period: " + tower.getFirePeriod());
		}
	}
	
	public void setUpgrade(Upgrade u) {
		upgrade = u;
		
		// TODO: make this include more fields
		if (upgrade == null) {
			damageChangeLabel.setText(" ");
			rangeChangeLabel.setText(" ");
			abilitiesChangeLabel.setText(" ");
		} else {
			String modifier = "";
			
			if (upgrade.getInstantDamageChange() > 0) {
				damageChangeLabel.setForeground(Color.GREEN);
				modifier = " +";
			} else {
				damageChangeLabel.setForeground(Color.RED);
				modifier = " -";
			}
			
			damageChangeLabel.setText(modifier + Double.toString(upgrade.getInstantDamageChange() * 100) + "%");
			
			if (upgrade.getRadiusChange() > 0) {
				rangeChangeLabel.setForeground(Color.GREEN);
				modifier = " +";
			} else {
				rangeChangeLabel.setForeground(Color.RED);
				modifier = " -";
			}
			
			rangeChangeLabel.setText(modifier + Double.toString(upgrade.getRadiusChange() * 100) + "%");
		}
	}
}
