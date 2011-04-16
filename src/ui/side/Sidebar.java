package src.ui.side;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import src.core.Game;

public class Sidebar extends JPanel {
	private static final long serialVersionUID = 1L;
	private Game game;

	public Sidebar(Game g) {
		game = g;
		
		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layoutManager);
		
		PlayerStatsPanel playerStats = new PlayerStatsPanel(game.getPlayer());
		TimeWavePanel timeWave = new TimeWavePanel(game);
		TowerPurchasePanel towerPurchasePanel = new TowerPurchasePanel();

		add(playerStats, BorderLayout.PAGE_START);
		add(timeWave, BorderLayout.PAGE_START);
		add(towerPurchasePanel, BorderLayout.PAGE_START);
	}
} 
