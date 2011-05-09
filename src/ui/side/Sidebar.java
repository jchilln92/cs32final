package src.ui.side;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;


import src.FilePaths;
import src.net.NetworkGame;
import src.ui.controller.GameController;
import src.ui.controller.MultiplayerController;

/**
 * Contains and shows components and subpanels in the sidebar.
 */
public class Sidebar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	private TowerCardPanel towerPanel;
	
	private PlayerStatsPanel playerStats;
	private TimeWavePanel timeWave;
	private PauseQuitPanel pauseQuit;
	private ImageIcon background;
	public Sidebar(GameController gc, MultiplayerController mc) {
		controller = gc;
		boolean isMultiplayer = mc != null;
		
		
		if (!isMultiplayer) {
			background = new ImageIcon(FilePaths.bgPath + "SidebarBGRD.png");
		} else {
			background = new ImageIcon(FilePaths.bgPath + "MPSidebarBGRD.png");
		}

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


		add(playerStats, BorderLayout.LINE_START);
		add(timeWave, BorderLayout.LINE_START);
		add(towerPanel, BorderLayout.LINE_START);
		add(pauseQuit, BorderLayout.LINE_START);
		
		
		playerStats.setOpaque(false);
		timeWave.setOpaque(false);
		towerPanel.setOpaque(false);
		pauseQuit.setOpaque(false);
		
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
		timeWave.disableTimeWave();
		towerPanel.disableCardPanel();
	}
	
	public void enableSidebar(){
		timeWave.enableTimeWave();
		towerPanel.enableCardPanel();
	}
	
	public PlayerStatsPanel getPlayerStatsPanel() {
		return playerStats;
	}
	
	
	public void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0 ,0, null);
	}
	
	
} 
