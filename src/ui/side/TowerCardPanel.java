package src.ui.side;

import java.awt.CardLayout;

import javax.swing.JPanel;

import src.ui.controller.GameController;

/**
 * A display component that uses CardLayout to display various UI's for different aspects
 * of editing or purchasing towers.  Sub-panels such as TowerPurchasePanel and CancelPurchasePanel
 * are managed by this UI component.
 */
public class TowerCardPanel extends JPanel {
	private GameController controller;
	private TowerPurchasePanel tpPanel;
	private CancelPurchasePanel cpPanel;
	private TowerUpgradePanel tuPanel;
	
	private enum PanelID {
		PURCHASE, CANCEL_PURCHASE, STATS_UPGRADE
	}
	
	public TowerCardPanel(GameController gc) {
		super(new CardLayout());
		controller = gc;
		
		// initialize subpanels
		tpPanel = new TowerPurchasePanel(controller);
		cpPanel = new CancelPurchasePanel(controller);
		tuPanel = new TowerUpgradePanel(controller);
		
		// for some reason you can add an arbitrary object as an identifier
		// for cardlayout, but it only accepts a string for the method show.
		add(tpPanel, PanelID.PURCHASE.toString());
		add(cpPanel, PanelID.CANCEL_PURCHASE.toString());
		add(tuPanel, PanelID.STATS_UPGRADE.toString());
	}
	
	public void showPurchasePanel() {
		CardLayout layout = (CardLayout) getLayout();
		layout.show(this, PanelID.PURCHASE.toString());
	}
	
	public void showCancelPanel() {
		CardLayout layout = (CardLayout) getLayout();
		layout.show(this, PanelID.CANCEL_PURCHASE.toString());
	}
	
	public void showUpgradePanel() {
		CardLayout layout = (CardLayout) getLayout();
		layout.show(this, PanelID.STATS_UPGRADE.toString());
	}
	
	public void disableCardPanel() {
		for (int x = 0; x < cpPanel.getComponentCount(); x++){
			cpPanel.getComponent(x).setEnabled(false);
		}
		
		tpPanel.disableTowerPurchase();
		tuPanel.disableTowerUpgrade();
	}
	
	public void enableCardPanel() {
		for (int x = 0; x < cpPanel.getComponentCount(); x++){
			cpPanel.getComponent(x).setEnabled(true);
		}	
		
		tpPanel.enableTowerPurchase();
		tuPanel.enableTowerUpgrade();

	}
}
