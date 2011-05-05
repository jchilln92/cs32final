package src.ui.creepside;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import src.FilePaths;
import src.core.Creep;
import src.core.Upgrade;
import src.ui.controller.GameController;
import src.ui.creepside.CreepStatsPanel;


public class CreepSelectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController controller;
	
	private static final String purchaseCreepsText = "Purchase Creeps:";
	private JLabel purchaseCreepsLabel;
	private CreepStatsPanel creepStats;
	private CreepInfoPurchasePanel creepInfoPurchase;
	
	private JButton[] creepButtons;	
	private Action[] creepButtonActions;
	private Creep.Type[] buttonTypes = {Creep.Type.GENERIC, Creep.Type.FLYING, Creep.Type.BIG_GUY, Creep.Type.ASSASSIN, Creep.Type.FAST}; 
	
	
	public CreepSelectionPanel(CreepInfoPurchasePanel cip, GameController gc){

		super(new GridBagLayout());
		controller = gc;
		this.creepInfoPurchase = cip;
		purchaseCreepsLabel = new JLabel(purchaseCreepsText);
		creepButtons = new JButton[8];
		creepButtonActions = new Action[8];
		
		creepStats = new CreepStatsPanel();
		
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0,20,0,-20);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.HORIZONTAL;

		add(purchaseCreepsLabel, c);
		
		c.insets = new Insets(-20,20,0,-15);
		c.gridwidth = 1;
		// initialize a purchase button for each of the towers

		for (int index = 0; index < 5; index++) {
			String path = FilePaths.imgPath + "creep-icon"+(index+1)+".png";
			
			ImageIcon towerIcon = new ImageIcon(path);
			JButton creepButton = new JButton(towerIcon);
			creepButtons[index] = creepButton;
			
			final Creep.Type type = buttonTypes[index];
			final int setIndex = index;
			creepButtonActions[index] = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					//controller.beginPurchasingTower(Tower.createTower(type));
					creepInfoPurchase.setCreepByIndex(setIndex);
				}
			};
			
			// set up buttons
			creepButton.addActionListener(creepButtonActions[index]);
			
			// set up equivalent key bindings
			//Integer idx = index;
			//this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.forDigit(index + 1, 10)), idx);
			//this.getActionMap().put(idx, creepButtonActions[index]);
			
			// set up mouse hover on buttons
			creepButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					creepStats.setCreep(Creep.createCreep(type));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					creepStats.setCreep(null);
				}
			});

			c.gridx = index;
			c.gridy = 1;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LINE_START;
			add(creepButton, c);
		}
		
		updateAllowedButtons();

		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(20, 20, 0, 0);
		c.gridx = 6;
		c.gridy = 0;
		c.gridheight = 2;
		c.ipadx = 100;
		c.anchor = GridBagConstraints.LINE_END;
		add(creepStats, c);
	}
	
	private void updateAllowedButtons() {
		for (int i = 0; i < 5; i++) {
			JButton b = creepButtons[i];
			Action a = creepButtonActions[i];
			Creep.Type type = buttonTypes[i];
			
			Creep c = Creep.createCreep(type);
			if (!controller.playerCanAfford(c)) {
				b.setEnabled(false);
				a.setEnabled(false);
			} 
			else{
				b.setEnabled(true);
				a.setEnabled(true);
			}
			
		}
	}
	
	public void paintComponent(Graphics g) {
		updateAllowedButtons();
	}
	
	public void disableCreepPurchase(){
		for (int x = 0; x< creepButtons.length; x++){
			creepButtons[x].setEnabled(false);
			creepButtonActions[x].setEnabled(false);
		}
	}
	
	public void enableCreepPurchase(){
		for (int x = 0; x< creepButtons.length; x++){
			creepButtons[x].setEnabled(true);
			creepButtonActions[x].setEnabled(true);
		}
	}
}
