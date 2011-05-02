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

public class CreepSideBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private NetworkGameController controller;
	
	private CreepSelectionPanel creepSelection;
	private CreepInfoPurchasePanel creepInfoPurchase;
	private CreepQueuePanel creepQueue;
	
	public CreepSideBar() {
		//controller = ngc;

		Border borderLine = BorderFactory.createLineBorder(Color.BLACK);
		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.LINE_AXIS);
		setLayout(layoutManager);
		
		creepSelection = new CreepSelectionPanel();
		creepInfoPurchase = new CreepInfoPurchasePanel();
		creepQueue = new CreepQueuePanel();

		creepSelection.setBorder(borderLine);
		creepInfoPurchase.setBorder(borderLine);
		creepQueue.setBorder(borderLine);
		this.setBorder(borderLine);

		add(creepSelection, BorderLayout.LINE_START);
		add(creepInfoPurchase, BorderLayout.LINE_START);
		add(creepQueue, BorderLayout.LINE_START);
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
