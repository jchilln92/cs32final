package src.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import src.ui.controller.GameController;
import src.ui.side.Sidebar;

public class SingleGamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	
	public SingleGamePanel(GameController controller) {
		super(new BorderLayout());
		
		this.gameController = controller;
		
		// setup map
		MapComponent mc = new MapComponent(false);
		mc.setMap(controller.getGame().getMap());
		mc.setSize(600, 600);
		mc.setGridOn(true);
		mc.setGameController(gameController);
		add(mc, BorderLayout.CENTER);
		
		CreepDrawer.toggleDrawHealthBar(true);
		
		Sidebar s = new Sidebar(gameController, null);
		add(s, BorderLayout.EAST);
		gameController.setSidebar(s);
	}
}
