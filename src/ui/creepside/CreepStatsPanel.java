package src.ui.creepside;

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
	
	public CreepStatsPanel() {
		super(new GridBagLayout());
	}
	public void setCreep(Creep c) {
		creep = c;
		
		if (creep == null) {
			creepNameLabel.setText(" ");
			healthLabel.setText(" ");
			damageLabel.setText(" ");
			abilitiesLabel.setText(" ");
			costLabel.setText(" ");
		} else {
			creepNameLabel.setText("Type: " + creep.getType().toString());
			healthLabel.setText("Health: " + (int)creep.getHealth());
			double damage = creep.getDamageToBase();
			String damageText = (int)(damage) + "." + (int)((damage - (int)damage)*10) + (int)((damage*10 - (int)(damage*10))*10);
			damageLabel.setText("Damage: " + damageText);
			abilitiesLabel.setText("Abilities: ");
			costLabel.setText("Cost: " + (int)creep.getPrice());
		}
	}
}
