package src.ui.creepside;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;


import src.net.NetworkGameController;
import src.ui.controller.GameController;

public class CreepSideBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	
	private CreepSelectionPanel creepSelection;
	private CreepInfoPurchasePanel creepInfoPurchase;
	private CreepQueuePanel creepQueue;
	
	public CreepSideBar(GameController gc) {
		controller = gc;

		Border borderLine = BorderFactory.createLineBorder(Color.BLACK);
		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.LINE_AXIS);
		setLayout(new BorderLayout());
		creepQueue = new CreepQueuePanel(controller);
		creepInfoPurchase = new CreepInfoPurchasePanel(creepQueue, controller);
		creepSelection = new CreepSelectionPanel(creepInfoPurchase, controller);
		
		creepQueue.setInfoPurchase(creepInfoPurchase);
		
		creepSelection.setBorder(borderLine);
		creepInfoPurchase.setBorder(borderLine);
		creepQueue.setBorder(borderLine);
		this.setBorder(borderLine);

		add(creepSelection, BorderLayout.LINE_START);
		add(creepInfoPurchase, BorderLayout.CENTER);
		add(creepQueue, BorderLayout.PAGE_END);
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
} 
