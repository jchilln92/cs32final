package src.ui.side;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.core.Player;

/**
 * This is a panel that displays information on a player's gold and health,
 * which is typically shown in the sidebar.
 */
public class PlayerStatsPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Player player;
	private Player opponent;

	private static final String goldText = "Gold: ";
	private static final String healthText = "Health: ";
	private static final String opponentHealthText = "Enemy: ";

	private JLabel goldLabel;
	private JLabel healthLabel;
	private JLabel opponentHealthLabel;
	private JLabel goldValueLabel;
	private JLabel healthValueLabel;
	private JLabel opponentHealthValueLabel;
	private JLabel goldChangeLabel;
	
	private boolean showingGoldChange;
	
	public PlayerStatsPanel(Player p) {
		super(new GridBagLayout());
		
		player = p;

		goldLabel = new JLabel(goldText);
		goldLabel.setForeground(Color.YELLOW);
		healthLabel = new JLabel(healthText);
		healthLabel.setForeground(Color.RED);
		goldValueLabel = new JLabel("0");
		healthValueLabel = new JLabel("0");
		goldChangeLabel = new JLabel("");
		
		showingGoldChange = false;
		
		updateDisplay();

		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(goldLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(goldValueLabel, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.insets.set(0, 10, 0, 0);
		add(goldChangeLabel, c);
		c.insets.set(0, 0, 0, 0);

		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(healthLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		add(healthValueLabel, c);
	}
	
	public PlayerStatsPanel(Player localPlayer, Player opponent) {
		this(localPlayer);
		
		this.opponent = opponent;
		
		opponentHealthLabel = new JLabel(opponentHealthText);
		opponentHealthLabel.setForeground(Color.RED);
		opponentHealthValueLabel = new JLabel("0");
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		add(opponentHealthLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		add(opponentHealthValueLabel, c);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateDisplay();
	}

	public void updateDisplay() {
		goldValueLabel.setText(Integer.toString((int)player.getGold()));
		healthValueLabel.setText(Integer.toString((int)player.getHealth()));
		
		if (opponentHealthValueLabel != null)
			opponentHealthValueLabel.setText(Integer.toString((int)opponent.getHealth()));
		
		if (!showingGoldChange) {
			goldChangeLabel.setForeground(Color.BLACK);
			goldChangeLabel.setText(("(+ " + Integer.toString((int)player.getIncomePerWave()) + " / wave)"));
		}
	}
	
	public void setGoldChange(String change) {
		goldChangeLabel.setForeground(Color.RED);
		goldChangeLabel.setText(change);
	}
	
	public void setPerTurnIncrease(double change) {
		double newIncrease = player.getIncomePerWave() + change;
		goldChangeLabel.setForeground(Color.GREEN);
		goldChangeLabel.setText(("(+ " + (Integer.toString((int)newIncrease) + " per wave)")));
	}
	
	public void setShowingGoldChange(boolean b) {
		showingGoldChange = b;
	}
}
