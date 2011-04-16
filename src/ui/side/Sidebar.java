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
	
	public Sidebar(GameController gc) {
		controller = gc;
		Border borderLine = BorderFactory.createLineBorder(Color.BLACK);

		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layoutManager);
		
		PlayerStatsPanel playerStats = new PlayerStatsPanel(controller.getGame().getPlayer());
		TimeWavePanel timeWave = new TimeWavePanel(controller);
		TowerPurchasePanel towerPurchase = new TowerPurchasePanel(controller);
		PauseQuitPanel pauseQuit = new PauseQuitPanel(controller);

		playerStats.setBorder(borderLine);
		timeWave.setBorder(borderLine);
		towerPurchase.setBorder(borderLine);
		pauseQuit.setBorder(borderLine);
		add(playerStats, BorderLayout.LINE_START);
		add(timeWave, BorderLayout.LINE_START);
		add(towerPurchase, BorderLayout.LINE_START);
		add(pauseQuit, BorderLayout.LINE_START);
	}
} 
