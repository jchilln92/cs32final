package src.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import src.net.NetworkGame;
import src.net.NetworkGameController;
import src.ui.controller.GameController;
import src.ui.controller.MultiplayerController;
import src.ui.creepside.CreepSideBar;
import src.ui.side.Sidebar;

/**
 * Handles setup of multiplayer display components. 
 */
public class MultiplayerGamePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private MultiplayerController controller; 
	
	private MapComponent opponentMap;
	private MapComponent localMap;
	private Sidebar sidebar;
	
	private JPanel gamePanel;
	
	public MultiplayerGamePanel(GameController localController, 
								NetworkGameController networkController,
								NetworkGame game,
								MultiplayerController multiController) {
		super(new GridBagLayout());
		
		opponentMap = new MapComponent(true);
		opponentMap.setGridOn(true);
		opponentMap.setSize(375, 375);
		
		localMap = new MapComponent(false);
		localMap.setGridOn(true);
		localMap.setSize(375, 375);
		
		localController.setGame(game);
		localController.setMultiplayerController(multiController);
		networkController.setGame(game);
		
		sidebar = new Sidebar(localController, multiController);
		localController.setSidebar(sidebar);
		
		opponentMap.setGameController(networkController);
		opponentMap.setMap(game.getMap());
		localMap.setGameController(localController);
		localMap.setMap(game.getMap());
		
		// layout maps and sidebar
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets.set(0, 0, 0, 10);
		c.anchor = GridBagConstraints.SOUTH;
		add(opponentMap, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.insets.set(0, 0, 0, 10);
		c.anchor = GridBagConstraints.SOUTH;
		add(localMap, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.insets.set(0, 0, 0, 10);
		add(sidebar, c);
		
		// setup side bar
		c.insets.set(0, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		CreepSideBar cs = new CreepSideBar(localController);
		add(cs, c);
	}
}
