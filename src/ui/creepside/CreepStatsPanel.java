package src.ui.creepside;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.core.Creep;

public class CreepStatsPanel extends JPanel{
	private Creep creep; // the creep we are currently displaying
	private JLabel creepNameLabel;
	private JLabel healthLabel;
	private JLabel damageLabel;
	private JLabel abilitiesLabel;
	private JLabel costLabel;
	private JLabel spacingLabel;
	
	public CreepStatsPanel() {
		super(new GridBagLayout());
		creepNameLabel = new JLabel(" ");
		healthLabel = new JLabel(" ");
		damageLabel = new JLabel(" ");
		abilitiesLabel = new JLabel(" ");
		costLabel = new JLabel(" ");
		spacingLabel = new JLabel("                            ");

		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		//c.ipadx = 100;
		c.anchor = GridBagConstraints.CENTER;
		//c.fill = GridBagConstraints.HORIZONTAL;
		add(creepNameLabel, c);
		
		c.gridy = 1;
		add(healthLabel, c);
		
		c.gridy = 2;
		add(damageLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(abilitiesLabel, c);
				
		c.gridx = 0;
		c.gridy = 4;
		add(costLabel, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(spacingLabel, c);
		
		
		
	}
	public void setCreep(Creep c) {
		creep = c;
		
		if (creep == null) {
			creepNameLabel.setText(" ");
			healthLabel.setText(" ");
			damageLabel.setText(" ");
			abilitiesLabel.setText(" ");
			costLabel.setText(" ");
			spacingLabel.setText("                            ");
			/*creepNameLabel.setVisible(false);
			healthLabel.setVisible(false);
			damageLabel.setVisible(false);
			abilitiesLabel.setVisible(false);
			costLabel.setVisible(false);
			spacingLabel.setVisible(false);*/
		} else {
			creepNameLabel.setText("Type: " + creep.getType().toString());
			healthLabel.setText("Health: " + (int)creep.getHealth());
			double damage = creep.getDamageToBase();
			String damageText = (int)(damage) + "." + (int)((damage - (int)damage)*10) + (int)((damage*10 - (int)(damage*10))*10);
			damageLabel.setText("Damage: " + damageText);
			abilitiesLabel.setText("Abilities: ");
			costLabel.setText("Cost: " + (int)creep.getPrice());
			
			/*creepNameLabel.setVisible(true);
			healthLabel.setVisible(true);
			damageLabel.setVisible(true);
			abilitiesLabel.setVisible(true);
			costLabel.setVisible(true);*/
		}
	}
}
