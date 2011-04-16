package src.ui.side;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import src.GameController;

public class Sidebar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	
	public Sidebar(GameController gc) {
		controller = gc;
		
		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layoutManager);
		
		PlayerStatsPanel playerStats = new PlayerStatsPanel(controller.getGame().getPlayer());
		TimeWavePanel timeWave = new TimeWavePanel(controller.getGame());
		TowerPurchasePanel towerPurchase = new TowerPurchasePanel(controller);
		PauseQuitPanel pauseQuit = new PauseQuitPanel(controller);

		add(playerStats, BorderLayout.LINE_START);
		add(timeWave, BorderLayout.LINE_START);
		add(towerPurchase, BorderLayout.LINE_START);
		add(pauseQuit, BorderLayout.LINE_START);
	}
} 
