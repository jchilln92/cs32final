package src.ui.side;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;


import src.GameController;

public class Sidebar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	private TowerCardPanel towerPanel;
	
	public Sidebar(GameController gc) {
		controller = gc;

		Border borderLine = BorderFactory.createLineBorder(Color.BLACK);
		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layoutManager);
		
		PlayerStatsPanel playerStats = new PlayerStatsPanel(controller.getGame().getPlayer());
		TimeWavePanel timeWave = new TimeWavePanel(controller);
		
		towerPanel = new TowerCardPanel(controller);
		towerPanel.showPurchasePanel(); // by default, show the tower purchase panel

		PauseQuitPanel pauseQuit = new PauseQuitPanel(controller);

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
		// TODO: implement
	}
} 
