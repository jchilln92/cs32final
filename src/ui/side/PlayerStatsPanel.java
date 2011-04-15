package src.ui.side;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.core.Player;

/**
 * This is a panel that displays information on a player's gold and health, which is typically shown in
 * the sidebar.
 */
public class PlayerStatsPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Player player;
	
	private static final String goldText = "Gold:";
	private static final String healthText = "Health:";
	
	private JLabel goldLabel;
	private JLabel healthLabel;
	private JLabel goldValueLabel;
	private JLabel healthValueLabel;
	
	public PlayerStatsPanel() {
		super(new GridBagLayout());
		
		goldLabel = new JLabel(goldText);
		healthLabel = new JLabel(healthText);
		goldValueLabel = new JLabel("0");
		healthValueLabel = new JLabel("0");
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(goldLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(goldValueLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(healthLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		add(healthValueLabel, c);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void updateDisplay() {
		goldValueLabel.setText(Double.toString(player.getGold()));
		healthValueLabel.setText(Double.toString(player.getHealth()));
	}
}
