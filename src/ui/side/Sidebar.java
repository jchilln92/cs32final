package src.ui.side;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;


import src.ui.controller.GameController;

public class Sidebar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	private TowerCardPanel towerPanel;
	
	private PlayerStatsPanel playerStats;
	private TimeWavePanel timeWave;
	private PauseQuitPanel pauseQuit;
	
	public Sidebar(GameController gc) {
		controller = gc;

		Border borderLine = BorderFactory.createLineBorder(Color.BLACK);
		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layoutManager);
		
		playerStats = new PlayerStatsPanel(controller.getGame().getPlayer());
		timeWave = new TimeWavePanel(controller);
		
		towerPanel = new TowerCardPanel(controller);
		towerPanel.showPurchasePanel(); // by default, show the tower purchase panel

		pauseQuit = new PauseQuitPanel(controller);

		playerStats.setBorder(borderLine);
		timeWave.setBorder(borderLine);
		towerPanel.setBorder(borderLine);
		pauseQuit.setBorder(borderLine);
		this.setBorder(borderLine);
		add(playerStats, BorderLayout.LINE_START);
		add(timeWave, BorderLayout.LINE_START);
		add(towerPanel, BorderLayout.LINE_START);
		add(pauseQuit, BorderLayout.LINE_START);
	}
	
	public void showTowerPurchase() {
		towerPanel.showPurchasePanel();
	}
	
	public void showTowerPurchaseCancel() {
		towerPanel.showCancelPanel();
	}

	public void showTowerUpgrade() {
		towerPanel.showUpgradePanel();
	}
	
	public void disableSidebar(){
		for (int x = 0; x < playerStats.getComponentCount(); x++){
			playerStats.getComponent(x).setEnabled(false);
		}
		for (int x = 0; x < timeWave.getComponentCount(); x++){
			timeWave.getComponent(x).setEnabled(false);
		}
		towerPanel.disableCardPanel();
	}
	
	public void enableSidebar(){
		for (int x = 0; x < playerStats.getComponentCount(); x++){
			playerStats.getComponent(x).setEnabled(true);
		}
		for (int x = 0; x < timeWave.getComponentCount(); x++){
			timeWave.getComponent(x).setEnabled(true);
		}
		towerPanel.enableCardPanel();
	}
} 
