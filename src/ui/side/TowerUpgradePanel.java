package src.ui.side;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.core.Tower;
import src.core.Upgrade;
import src.ui.controller.GameController;

/**
 * A display area which presents the selected tower's statistics and upgrade options
 */
public class TowerUpgradePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GameController controller;
	
	private TowerStatsPanel towerStats;
	
	private ElementalUpgradePanel elementalUpgrade;
	
	private JLabel levelOneLabel;
	private JLabel levelTwoLabel;
	private JLabel levelThreeLabel;
	
	private JButton sellTowerButton;
	private JButton cancelButton;
	private JButton[][] upgradeButtons;

	public TowerUpgradePanel(GameController gc) {
		super(new GridBagLayout());
		controller = gc;
		
		towerStats = new TowerStatsPanel(controller);
		towerStats.setTower(controller.getSelectedTower());
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unselectTower();
			}
		});
		
		sellTowerButton = new JButton("Sell");
		sellTowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.sellTower();
			}
		});
		
		elementalUpgrade = new ElementalUpgradePanel(gc);
		
		levelOneLabel = new JLabel("1");
		levelTwoLabel = new JLabel("2");
		levelThreeLabel = new JLabel("3");
		
		upgradeButtons = new JButton[3][3];
		
		// lay out components
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;	
		c.insets.set(10, 0, 0, 0);
		c.gridx = 0;
		c.gridwidth = 4;
		c.gridy = 0;
		add(towerStats, c);
		c.gridwidth = 1;
		
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 10, 0);	
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(elementalUpgrade, c);
		c.gridwidth = 1;

		c.anchor = GridBagConstraints.CENTER;
		c.insets.set(0, 0, 10, 0);	
		c.gridx = 0;
		c.gridwidth = 4;
		c.gridy = 2;
		add(cancelButton, c);
		c.gridwidth = 1;
	
		c.insets.set(0, 10, 0, 10);
		c.gridx = 0;
		c.gridy = 3;
		add(levelThreeLabel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(levelTwoLabel, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(levelOneLabel, c);
		c.insets.set(0, 0, 0, 0);
		
		// initialize and lay out each of the upgrade buttons
		for (int l = 0; l < 3; l++) { // there are 3 levels
			for (int n = 0; n < 3; n++) { // and 3 upgrades in each level
				final int level = l + 1;
				final int idx = n;
				
				JButton upgradeButton = new JButton("up" + Integer.toString(level));
				
				upgradeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.applyTowerUpgrade(level, idx);
					}
				});
				
				upgradeButton.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						if (e.getComponent().isEnabled())
							towerStats.setUpgrade(controller.getTowerUpgrade(level, idx));
					}
					
					public void mouseExited(MouseEvent e) {
						towerStats.setUpgrade(null);
					}
				});
				
				c.gridx = idx + 1;
				c.gridy = 5 - level + 1;
				add(upgradeButton, c);
				
				upgradeButtons[l][n] = upgradeButton;
			}
		}

		c.insets = new Insets(10, 0, 10, 0);	
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(sellTowerButton, c);
		

	}
	
	private void updateSellButton() {
		sellTowerButton.setText("Sell for " + controller.getSelectedTower().getInvestment() * GameController.towerRefundPercentage);
	}
	
	private void updateClickableButtons() {
		Tower tower = controller.getSelectedTower();
		
		for (int l = 0; l < 3; l++) {
			for (int n = 0; n < 3; n++) {
				JButton upgradeButton = upgradeButtons[l][n];
				Upgrade potentialUpgrade = controller.getTowerUpgrade(l + 1, n);
				
				// only allow upgrades at the proper level and that the player can afford
				if (l == tower.getUpgradeLevel() && controller.playerCanAfford(potentialUpgrade) && controller.getPaused() == false) {
					upgradeButton.setEnabled(true);
				} else {
					upgradeButton.setEnabled(false);
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		towerStats.setTower(controller.getSelectedTower());
		updateSellButton();
		updateClickableButtons();
	}
	
	public void disableTowerUpgrade() {
		for (int x = 0; x < upgradeButtons.length; x++){
			for (int y = 0; y < upgradeButtons[x].length; y++){
				upgradeButtons[x][y].setEnabled(false);
			}
		}
		
		sellTowerButton.setEnabled(false);
		cancelButton.setEnabled(false);
	}
	
	public void enableTowerUpgrade() {
		for (int x = 0; x < upgradeButtons.length; x++){
			for (int y = 0; y < upgradeButtons[x].length; y++){
				upgradeButtons[x][y].setEnabled(true);
			}
		}
		
		sellTowerButton.setEnabled(true);
		cancelButton.setEnabled(true);
	}
}
