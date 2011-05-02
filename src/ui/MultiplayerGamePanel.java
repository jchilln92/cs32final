package src.ui;

import javax.swing.JPanel;

import src.ui.side.Sidebar;

public class MultiplayerGamePanel extends JPanel {
	public MapComponent opponentMap;
	public MapComponent localMap;
	public Sidebar sb;
	
	public MultiplayerGamePanel() {
		opponentMap = new MapComponent(true);
		localMap = new MapComponent(false);
		
		opponentMap.setSize(400, 400);
		localMap.setSize(400, 400);
		
		add(opponentMap);
		add(localMap);
	}
}
