package src.ui.creepside;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.core.Creep;
import src.ui.controller.GameController;

/**
 * Panel for displaying the stats of the currently hovered over creep. Whenever a creep in the selectionPanel is hovered over, this panel
 * is updated to display that creeps cost, abilities, and so on.
 */
public class CreepStatsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Creep creep; // the creep we are currently displaying
	private JLabel creepNameLabel;
	private JLabel healthLabel;
	private JLabel damageLabel;
	private JLabel costLabel;
	private JLabel speedLabel;
	private JLabel spacingLabel;
	private GameController controller;
	
	public CreepStatsPanel(GameController gc) {
		super(new GridBagLayout());
		
		this.setOpaque(false);
		
		controller = gc;
		
		creepNameLabel = new JLabel(" ");
		healthLabel = new JLabel(" ");
		damageLabel = new JLabel(" ");
		speedLabel = new JLabel(" ");
		costLabel = new JLabel(" ");
		spacingLabel = new JLabel("                            ");

		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		add(creepNameLabel, c);
		
		c.gridy = 1;
		add(healthLabel, c);
		
		c.gridy = 2;
		add(damageLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(speedLabel, c);
				
		c.gridx = 0;
		c.gridy = 4;
		add(costLabel, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(spacingLabel, c);
		
	}
	
	/**
	 * When given a creep c, displays c's stats (health, name, cost, etc).
	 * @param creep to display stats for
	 */
	public void setCreepStats(Creep c) {
		creep = c;
		
		if (creep == null) {
			creepNameLabel.setText(" ");
			healthLabel.setText(" ");
			damageLabel.setText(" ");
			speedLabel.setText(" ");
			costLabel.setText(" ");
			spacingLabel.setText("                            ");
			controller.getSideBar().getPlayerStatsPanel().setShowingGoldChange(false);
		} else {
			creepNameLabel.setText("Type: " + creep.getType().toString());
			healthLabel.setText("Health: " + (int)creep.getHealth());
			String damageText = NumberFormat.getIntegerInstance().format(creep.getDamageToBase());
			damageLabel.setText("Damage: " + damageText);
			speedLabel.setText("Speed: " + creep.getSpeed());
			costLabel.setText("Cost: " + (int)creep.getPrice());
			
			controller.getSideBar().getPlayerStatsPanel().setShowingGoldChange(true);
			controller.getSideBar().getPlayerStatsPanel().setPerTurnIncrease(creep.getAdditionalGoldPerWave());
		}
	}
}
