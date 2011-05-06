package src.ui.creepside;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.core.Creep;
import src.ui.controller.GameController;

public class CreepStatsPanel extends JPanel{
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
		add(speedLabel, c);
				
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
			speedLabel.setText(" ");
			costLabel.setText(" ");
			spacingLabel.setText("                            ");
			controller.getSideBar().getPlayerStatsPanel().setShowingGoldChange(false);

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
			speedLabel.setText("Speed: " + creep.getSpeed());
			costLabel.setText("Cost: " + (int)creep.getPrice());
			
			controller.getSideBar().getPlayerStatsPanel().setShowingGoldChange(true);
			controller.getSideBar().getPlayerStatsPanel().setPerTurnIncrease(creep.getAdditionalGoldPerWave());

			/*creepNameLabel.setVisible(true);
			healthLabel.setVisible(true);
			damageLabel.setVisible(true);
			abilitiesLabel.setVisible(true);
			costLabel.setVisible(true);*/
		}
	}
}
