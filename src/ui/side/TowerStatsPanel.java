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
import src.ui.controller.GameController;

/**
 * A reusable panel that is used to display the stats of a given tower.
 * @see TowerUpgradePanel
 * @see TowerPurchasePanel
 */
public class TowerStatsPanel extends JPanel {
	private Tower tower; // the tower we are currently displaying
	private Upgrade upgrade; // an upgrade that should be applied to the tower's stats before showing them
	GameController controller;
	private JLabel towerNameLabel;
	private JLabel damageLabel;
	private JLabel rangeLabel;
	private JLabel abilitiesLabel;
	private JLabel fireRateLabel;
	private JLabel extraDamageLabel; // extraDamageLabels used to represent either slowing or over time damage
	
	private JLabel damageChangeLabel;
	private JLabel rangeChangeLabel;
	private JLabel rateChangeLabel;
	private JLabel abilitiesChangeLabel;
	private JLabel extraDamageChangeLabel;
	
	public TowerStatsPanel(GameController gc) {
		super(new GridBagLayout());
		
		controller = gc;
		
		tower = null;
		upgrade = null;
		
		towerNameLabel = new JLabel(" ");
		damageLabel = new JLabel(" ");
		rangeLabel = new JLabel(" ");
		abilitiesLabel = new JLabel(" ");
		fireRateLabel = new JLabel(" ");
		extraDamageLabel = new JLabel(" ");
		
		damageChangeLabel = new JLabel(" ");
		rangeChangeLabel = new JLabel(" ");
		rateChangeLabel = new JLabel(" ");
		abilitiesChangeLabel = new JLabel(" ");
		extraDamageChangeLabel =  new JLabel(" ");
		
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
		
		c.gridx= 0;
		c.gridy = 5;
		add(extraDamageLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		add(extraDamageChangeLabel, c);
		
	}
	
	public void setTower(Tower t) {
		tower = t;
		
		if (tower == null) {
			towerNameLabel.setText(" ");
			damageLabel.setText(" ");
			rangeLabel.setText(" ");
			fireRateLabel.setText(" ");
			abilitiesLabel.setText(" ");
			extraDamageLabel.setText(" ");
		} else {
			towerNameLabel.setText("Type: " + tower.getType().toString());
			
			NumberFormat.getInstance().setMaximumFractionDigits(2);
			damageLabel.setText("Damage: " +NumberFormat.getInstance().format(tower.getDamage().getInstantDamage()));
			rangeLabel.setText("Range: " + NumberFormat.getInstance().format(tower.getRadius()));
			fireRateLabel.setText("Fire Rate: " + NumberFormat.getInstance().format(1/tower.getFirePeriod() * 1000/Runner.tickDuration) + " / s");
			
			setCurrentAbilities(tower);
			
			
			//properly set the extra damage label for slowing or damage over time
			if (tower.getDamage().getSpeedChange() != 0) {
				extraDamageLabel.setText("Slow Effect: " + NumberFormat.getInstance().format(tower.getDamage().getSpeedChange() * 100) + "%");

			} else if (tower.getDamage().getTimeDamage() != 0) {
				extraDamageLabel.setText("Damage over Time: " + NumberFormat.getInstance().format(tower.getDamage().getTimeDamage()));		
			}
			
		}
	}
	
	public void setUpgrade(Upgrade u) {
		upgrade = u;
		
		if (upgrade == null) {
			damageChangeLabel.setText(" ");
			rangeChangeLabel.setText(" ");
			rateChangeLabel.setText(" ");
			abilitiesChangeLabel.setText(" ");
			controller.getSideBar().getPlayerStatsPanel().setGoldChange("");
			extraDamageChangeLabel.setText(" ");

		} else {
			String modifier = "";
			
			controller.getSideBar().getPlayerStatsPanel().setGoldChange(" -" + u.getPrice());
			
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
			
			
			setCurrentAbilities(tower);
			String newAbilities = "+ ";
			
			if (u.getHitsFlyingChange() && !tower.getTargeting().isHitsFlying())
				newAbilities += "Anti-air, ";
			
			//TODO: decide whether or not we want to add this in. As the game is built right now, it is
			//		impossible for a tower to without damage over time or slowing to gain it as everything
			//		is percentage based
			
			/*
			if (u.getTimeDamageChange() != 0 && tower.getDamage().getTimeDamage() == 0)
				newAbilities += "DoT, ";
			if (u.speedEffectChange() != 0 && tower.getDamage().getSpeedChange() == 0)
				newAbilities += "slowing, ";
			*/
			newAbilities = newAbilities.substring(0, newAbilities.length() -2);
			abilitiesChangeLabel.setForeground(Color.GREEN);
			abilitiesChangeLabel.setText(newAbilities);
			
			//For first extraDamageChangeLabel, check for slowing effect
			if (upgrade.speedEffectChange() != 0) {
				if (upgrade.speedEffectChange() > 0) {
					extraDamageChangeLabel.setForeground(Color.GREEN);
					modifier = " -";
				} else {
					extraDamageChangeLabel.setForeground(Color.RED);
					modifier = " +";
				}
				extraDamageChangeLabel.setText(modifier + Double.toString(upgrade.speedEffectChange() * 100) + "%");
			} else if (upgrade.getTimeDamageChange() != 0) { //second, check for damage over time
				if (extraDamageChangeLabel.getText().equals(" ")) {				
					if (upgrade.getTimeDamageChange() > 0) {
						extraDamageChangeLabel.setForeground(Color.GREEN);
						modifier = " +";
					} else {
						extraDamageChangeLabel.setForeground(Color.RED);
						modifier = " -";
					}				
					extraDamageChangeLabel.setText(modifier + Double.toString(upgrade.getTimeDamageChange() * 100) + "%");
				}
			}
		}
	}
	
	//Takes in a tower and sets it's abilitiesLabel 
	public void setCurrentAbilities(Tower tower){
		String abilitiesText = "";
		if (tower.getTargeting().isHitsFlying())
			abilitiesText += "Anti-air, ";
		if (tower.getDamage().getSpeedChange() != 0.0)
			abilitiesText += "Slowing, ";
		if (tower.getDamage().getTimeDamage() != 0.0)
			abilitiesText += "DoT, ";
		
		if (abilitiesText.length() > 0)
			abilitiesText = abilitiesText.substring(0, abilitiesText.length() - 2);

		abilitiesLabel.setText("Abilities: " + abilitiesText);
	}
}
