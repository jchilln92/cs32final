package src.ui.creepside;


import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import src.FilePaths;
import src.ui.controller.GameController;

/**
 * This panel is the main panel for the creep bottom bar. Used to assemble and maintain the other four pieces of this side bar together.
 */
public class CreepSideBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	private CreepSelectionPanel creepSelection;
	private CreepPurchasePanel creepInfoPurchase;
	private CreepQueuePanel creepQueue;
	
	private ImageIcon creepBackground;
	
	public CreepSideBar(GameController gc) {
		controller = gc;

		creepBackground = new ImageIcon(FilePaths.bgPath +"MPCreepBGRD.png");
		
		setLayout(new GridBagLayout());
		
		creepQueue = new CreepQueuePanel(controller);
		creepInfoPurchase = new CreepPurchasePanel(creepQueue, controller);
		creepSelection = new CreepSelectionPanel(creepInfoPurchase, controller);
		
		creepQueue.setOpaque(false);
		creepInfoPurchase.setOpaque(false);
		creepSelection.setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		add(creepSelection, c);
		
		c.gridx = 1;
		c.gridy = 0;
		add(creepInfoPurchase, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		add(creepQueue, c);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(creepBackground.getImage(), 0 ,0, null);
	}
} 
