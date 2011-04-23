package src.ui.side;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.GameController;

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
	private JButton testUpgradeButton;
	
	public TowerUpgradePanel(GameController gc) {
		super(new GridBagLayout());
		
		controller = gc;
		
		towerStats = new TowerStatsPanel();
		towerStats.setTower(controller.getSelectedTower());
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unselectTower();
			}
		});
		
		testUpgradeButton = new JButton("Test");
		testUpgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.applyTowerUpgrade(1, 1);
			}
		});
		
		// lay out components
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		add(towerStats, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(cancelButton, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(testUpgradeButton, c);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		towerStats.setTower(controller.getSelectedTower());
	}
}
