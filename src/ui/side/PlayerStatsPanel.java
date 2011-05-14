package src.ui.side;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.FilePaths;
import src.core.Player;

/**
 * This is a panel that displays information on a player's gold and health (also opponent's health for multiplayer)
 */
public class PlayerStatsPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Player player;
	private Player opponent;

	private JLabel goldLabel;
	private JLabel healthLabel;
	private JLabel opponentHealthLabel;
	private JLabel goldValueLabel;
	private JLabel healthValueLabel;
	private JLabel opponentHealthValueLabel;
	private JLabel goldChangeLabel;
	
	private ImageIcon goldIcon;
	private ImageIcon healthIcon;
	private ImageIcon enemyHealthIcon;
	
	private boolean showingGoldChange;
	
	//sets up for single player
	public PlayerStatsPanel(Player p) {
		super(new GridBagLayout());
		
		player = p;
		
		goldIcon = new ImageIcon(FilePaths.miscPath + "gold-icon.png");
		healthIcon = new ImageIcon(FilePaths.miscPath + "heart-icon1.png");
		
		goldLabel = new JLabel(goldIcon);
		healthLabel = new JLabel(healthIcon);
		
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
	
	//if multiplayer, also adds in opponent health
	public PlayerStatsPanel(Player localPlayer, Player opponent) {
		this(localPlayer);
		
		this.opponent = opponent;
		enemyHealthIcon = new ImageIcon(FilePaths.miscPath + "heart-icon2.png");
		
		opponentHealthLabel = new JLabel(enemyHealthIcon);
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

	/**
	 * updates the gold and health labels just by constantly checking these values
	 */
	public void updateDisplay() {
		goldValueLabel.setText(Integer.toString((int)player.getGold()));
		healthValueLabel.setText(Integer.toString((int)player.getHealth()));
		
		if (opponentHealthValueLabel != null)
			opponentHealthValueLabel.setText(Integer.toString((int)opponent.getHealth()));
		
		if (!showingGoldChange) { //if something else is displaying the cost of some purchasable item, do not display income per wave
			goldChangeLabel.setForeground(Color.BLACK);
			goldChangeLabel.setText(("(+ " + Integer.toString((int)player.getIncomePerWave()) + " / wave)"));
		}
	}
	
	/**
	 * Called by other classes - whenever attempting to purchase an item, displays how much gold it will detract from our current gold.
	 */
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
