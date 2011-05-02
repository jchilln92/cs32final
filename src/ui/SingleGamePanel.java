package src.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import src.net.NetworkGameController;
import src.ui.controller.GameController;
import src.ui.creepside.CreepSideBar;
import src.ui.side.Sidebar;

public class SingleGamePanel extends JPanel {
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
		
		// setup sidebar
		Sidebar s = new Sidebar(gameController);
		add(s, BorderLayout.LINE_END);
		gameController.setSidebar(s);
		
		CreepSideBar cs = new CreepSideBar();
		add(cs, BorderLayout.PAGE_END);
	}
}
