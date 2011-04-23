package src.ui.side;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.GameController;
import src.core.Tower;
import src.core.Upgrade;

/**
 * A display area which presents the selected tower's statistics and upgrade options
 */
public class TowerUpgradePanel extends JPanel {
	private GameController controller;
	
	private TowerStatsPanel towerStats;
	
	private JLabel levelOneLabel;
	private JLabel levelTwoLabel;
	private JLabel levelThreeLabel;
	
	private JButton sellTowerButton;
	private JButton cancelButton;
	private JButton[] upgradeButtons;
		
	public TowerUpgradePanel(GameController gc) {
		super(new GridBagLayout());
		upgradeButtons = new JButton[9];
		controller = gc;
		
		towerStats = new TowerStatsPanel();
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
		
		levelOneLabel = new JLabel("1");
		levelTwoLabel = new JLabel("2");
		levelThreeLabel = new JLabel("3");
		
		upgradeButtons = new JButton[9];
		
		// lay out components
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		add(towerStats, c);
		c.gridwidth = 1;
		
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 1;
		add(cancelButton, c);
		c.gridwidth = 1;
		
		c.gridx = 0;
		c.gridy = 2;
		add(levelThreeLabel, c);
		
		for (int n = 0; n < 3; n++) {
			JButton upgradeButton = new JButton("up3");
			final int i = n;
			upgradeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.applyTowerUpgrade(3, i);
				}
			});
			c.gridx = n + 1;
			c.gridy = 2;
			add(upgradeButton, c);
			upgradeButtons[n] = upgradeButton;
		}
		
		c.gridx = 0;
		c.gridy = 3;
		add(levelTwoLabel, c);
		
		for (int n = 0; n < 3; n++) {
			JButton upgradeButton = new JButton("up2");
			final int i = n;
			upgradeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.applyTowerUpgrade(2, i);
				}
			});
			
			c.gridx = n + 1;
			c.gridy = 3;
			add(upgradeButton, c);
			upgradeButtons[n+3] = upgradeButton;
		}
		
		c.gridx = 0;
		c.gridy = 4;
		add(levelOneLabel, c);
		
		for (int n = 0; n < 3; n++) {
			JButton upgradeButton = new JButton("up1");
			final int i = n;
			upgradeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.applyTowerUpgrade(1, i);
				}
			});
			
			c.gridx = n + 1;
			c.gridy = 4;
			add(upgradeButton, c);
			upgradeButtons[n+6] = upgradeButton;

		}
		
		c.gridx = 0;
		c.gridy = 5;
		add(sellTowerButton, c);
	}
	
	private void updateClickableButtons(){
		Tower tower = controller.getSelectedTower();

		if (tower != null){
			
			//First check our tower's upgrade level
			for (int x = 0; x< upgradeButtons.length;x++) {
				if (tower.getUpgradeLevel() == 3){
					upgradeButtons[x].setEnabled(false);
				}else if (upgradeButtons[x].getText().equals("up1")) {
					if (tower.getUpgradeLevel() != 0)
						upgradeButtons[x].setEnabled(false);
					else
						upgradeButtons[x].setEnabled(true);
				}else if (upgradeButtons[x].getText().equals("up2")) {
					if (tower.getUpgradeLevel() != 1)
						upgradeButtons[x].setEnabled(false);
					else
						upgradeButtons[x].setEnabled(true);
				}else if (upgradeButtons[x].getText().equals("up3")) {
					if (tower.getUpgradeLevel() != 2)
						upgradeButtons[x].setEnabled(false);
					else
						upgradeButtons[x].setEnabled(true);
				}
				
			}

		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		towerStats.setTower(controller.getSelectedTower());	
		updateClickableButtons();
	}
}
