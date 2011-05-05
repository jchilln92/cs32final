package src.ui.side;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;


import src.net.NetworkGame;
import src.ui.controller.GameController;
import src.ui.controller.MultiplayerController;

public class Sidebar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	private TowerCardPanel towerPanel;
	
	private PlayerStatsPanel playerStats;
	private TimeWavePanel timeWave;
	private PauseQuitPanel pauseQuit;
	
	public Sidebar(GameController gc, MultiplayerController mc) {
		controller = gc;
		boolean isMultiplayer = mc != null;
		
		Border borderLine = BorderFactory.createLineBorder(Color.BLACK);
		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layoutManager);
		
		if (!isMultiplayer) {
			playerStats = new PlayerStatsPanel(controller.getGame().getPlayer());
		} else {
			NetworkGame ng = (NetworkGame) controller.getGame();
			playerStats = new PlayerStatsPanel(controller.getGame().getPlayer(), ng.getOpponent());
		}

		timeWave = new TimeWavePanel(controller, isMultiplayer);
		
		towerPanel = new TowerCardPanel(controller);
		towerPanel.showPurchasePanel(); // by default, show the tower purchase panel

		if (isMultiplayer) {
			pauseQuit = new PauseQuitPanel(controller, mc);
		} else {
			pauseQuit = new PauseQuitPanel(controller);
		}

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
	
	public PlayerStatsPanel getPlayerStatsPanel() {
		return playerStats;
	}
} 
