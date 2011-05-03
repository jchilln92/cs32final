package src.ui;

import javax.swing.JPanel;

import src.net.NetworkGame;
import src.net.NetworkGameController;
import src.ui.controller.GameController;
import src.ui.controller.MultiplayerController;
import src.ui.side.Sidebar;

public class MultiplayerGamePanel extends JPanel {
	private MultiplayerController controller; 
	
	private MapComponent opponentMap;
	private MapComponent localMap;
	private Sidebar sidebar;
	
	public MultiplayerGamePanel(GameController localController, 
								NetworkGameController networkController,
								NetworkGame game,
								MultiplayerController multiController) {
		opponentMap = new MapComponent(true);
		opponentMap.setGridOn(true);
		opponentMap.setSize(400, 400);
		
		localMap = new MapComponent(false);
		localMap.setGridOn(true);
		localMap.setSize(400, 400);
		
		localController.setGame(game);
		networkController.setGame(game);
		
		sidebar = new Sidebar(localController, multiController);
		localController.setSidebar(sidebar);
		
		opponentMap.setGameController(networkController);
		opponentMap.setMap(game.getMap());
		localMap.setGameController(localController);
		localMap.setMap(game.getMap());
		
		add(opponentMap);
		add(localMap);
		add(sidebar);
	}
}
