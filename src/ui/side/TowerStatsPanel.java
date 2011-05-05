package src.ui.side;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.Runner;
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
	private JLabel fireRateLabel;
	
	private JLabel damageChangeLabel;
	private JLabel rangeChangeLabel;
	private JLabel rateChangeLabel;
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
		fireRateLabel = new JLabel(" ");
		
		damageChangeLabel = new JLabel(" ");
		rangeChangeLabel = new JLabel(" ");
		rateChangeLabel = new JLabel(" ");
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
		add(fireRateLabel, c);
		
		c.gridx = 1;
		c.gridy = 3;
		add(rateChangeLabel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(abilitiesLabel, c);
		
		c.gridx = 1;
		c.gridy = 4;
		add(abilitiesChangeLabel, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(costLabel, c);
	}
	
	public void setTower(Tower t) {
		tower = t;
		
		if (tower == null) {
			towerNameLabel.setText(" ");
			damageLabel.setText(" ");
			rangeLabel.setText(" ");
			fireRateLabel.setText(" ");
			abilitiesLabel.setText(" ");
			costLabel.setText(" ");
		} else {
			towerNameLabel.setText("Type: " + tower.getType().toString());
			
			NumberFormat.getInstance().setMaximumFractionDigits(2);
			damageLabel.setText("Damage: " +NumberFormat.getInstance().format(tower.getDamage().getInstantDamage()));
			rangeLabel.setText("Range: " + NumberFormat.getInstance().format(tower.getRadius()));
			fireRateLabel.setText("Fire Rate: " + NumberFormat.getInstance().format(1/tower.getFirePeriod() * 1000/Runner.tickDuration) + " / s");
			
			abilitiesLabel.setText("Abilities: ");
			costLabel.setText("Cost: " + (int)tower.getPrice());
		}
	}
	
	public void setUpgrade(Upgrade u) {
		upgrade = u;
		
		if (upgrade == null) {
			damageChangeLabel.setText(" ");
			rangeChangeLabel.setText(" ");
			rateChangeLabel.setText(" ");
			abilitiesChangeLabel.setText(" ");
		} else {
			String modifier = "";
			
			// damage change
			if (upgrade.getInstantDamageChange() > 0) {
				damageChangeLabel.setForeground(Color.GREEN);
				modifier = " +";
			} else {
				damageChangeLabel.setForeground(Color.RED);
				modifier = " -";
			}
			
			if (upgrade.getInstantDamageChange() != 0)
				damageChangeLabel.setText(modifier + Double.toString(upgrade.getInstantDamageChange() * 100) + "%");
			
			// range change
			if (upgrade.getRadiusChange() > 0) {
				rangeChangeLabel.setForeground(Color.GREEN);
				modifier = " +";
			} else {
				rangeChangeLabel.setForeground(Color.RED);
				modifier = " -";
			}
			
			if (upgrade.getRadiusChange() != 0)
				rangeChangeLabel.setText(modifier + Double.toString(upgrade.getRadiusChange() * 100) + "%");
			
			// fire rate change
			if (upgrade.getFirePeriodChange() > 0) {
				rateChangeLabel.setForeground(Color.GREEN);
				modifier = " +";
			} else {
				rateChangeLabel.setForeground(Color.RED);
				modifier = " -";
			}
			
			if (upgrade.getFirePeriodChange() != 0)
				rateChangeLabel.setText(modifier + Double.toString(upgrade.getFirePeriodChange() * 100) + "%");
		}
	}
}
