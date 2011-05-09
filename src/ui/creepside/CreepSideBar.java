package src.ui.creepside;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

import src.FilePaths;
import src.ui.controller.GameController;

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
		
		Border borderLine = BorderFactory.createLineBorder(Color.BLACK);
		this.setBorder(borderLine);
		
		setLayout(new GridBagLayout());
		
		creepQueue = new CreepQueuePanel(controller);
		creepInfoPurchase = new CreepPurchasePanel(creepQueue, controller);
		creepSelection = new CreepSelectionPanel(creepInfoPurchase, controller);
		
		creepQueue.setOpaque(false);
		creepInfoPurchase.setOpaque(false);
		creepSelection.setOpaque(false);
		
		creepQueue.setInfoPurchase(creepInfoPurchase);

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
	
	
	public void disableSidebar(){
		for (int x = 0; x < creepSelection.getComponentCount(); x++){
			creepSelection.getComponent(x).setEnabled(false);
		}
		
		for (int x = 0; x < creepInfoPurchase.getComponentCount(); x++){
			creepInfoPurchase.getComponent(x).setEnabled(false);
		}
		
		for (int x = 0; x < creepQueue.getComponentCount(); x++){
			creepQueue.getComponent(x).setEnabled(false);
		}
	}
	
	public void enableSidebar(){
		for (int x = 0; x < creepSelection.getComponentCount(); x++){
			creepSelection.getComponent(x).setEnabled(true);
		}
		
		for (int x = 0; x < creepInfoPurchase.getComponentCount(); x++){
			creepInfoPurchase.getComponent(x).setEnabled(true);
		}
		
		for (int x = 0; x < creepQueue.getComponentCount(); x++){
			creepQueue.getComponent(x).setEnabled(true);
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(creepBackground.getImage(), 0 ,0, null);
	}
} 
