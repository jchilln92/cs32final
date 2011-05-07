package src.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import src.net.NetworkGameController;
import src.ui.controller.GameController;
import src.ui.creepside.CreepSideBar;
import src.ui.side.Sidebar;

public class SingleGamePanel extends JPanel {
	private GameController gameController;
	
	public SingleGamePanel(GameController controller) {
		super(new GridBagLayout());
		
		this.gameController = controller;
		
		GridBagConstraints c = new GridBagConstraints();
		
		// setup map
		MapComponent mc = new MapComponent(false);
		mc.setMap(controller.getGame().getMap());
		mc.setSize(600, 600);
		mc.setGridOn(true);
		mc.setGameController(gameController);
		c.gridx = 0;
		c.gridy = 0;
		add(mc, c);
		
		CreepDrawer.toggleDrawHealthBar(true);
		
		Sidebar s = new Sidebar(gameController, null);
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.VERTICAL;
		add(s, c);
		gameController.setSidebar(s);
	}
}
