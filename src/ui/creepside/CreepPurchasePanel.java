package src.ui.creepside;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.FilePaths;
import src.core.Creep;
import src.core.IAlignment;
import src.ui.ColorConstants;
import src.ui.controller.GameController;

/**
 * This class represents the part of the creep purchasing bottom-bar that is used for actually buying creeps.
 * Contains all of the potential alignments to attach to a creep, an image of the creep about to be purchased,
 * and a purchase button.
 */
public class CreepPurchasePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController gc;
	
	private CreepQueuePanel creepQueue;
	private Creep creep;
	private ImageIcon creepIcon;
	private JLabel iconLabel;
	
	private JButton neutralButton;
	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;
	private JButton yellowButton;
	private JButton buyButton;
	
	private ImageIcon buyCreepIcon;
	private ImageIcon buyCreepHoverIcon;
	private ImageIcon buyCreepPressedIcon;
	private ImageIcon buyCreepDisabledIcon;

	private int creepIndex;
	
	private Creep.Type[] creepTypes = {Creep.Type.GENERIC, Creep.Type.FLYING, Creep.Type.BIG_GUY, Creep.Type.ASSASSIN, Creep.Type.FAST};

	public CreepPurchasePanel(CreepQueuePanel cq, GameController controller) {
		super(new GridBagLayout());
		gc = controller;
		creepQueue = cq;
		creepIndex = -1;
		
		//set up ImageIcons
		buyCreepIcon = new ImageIcon(FilePaths.buttonPath + "BuyCreepButton.png");
		buyCreepHoverIcon = new ImageIcon(FilePaths.buttonPath + "BuyCreepButtonHover.png");
		buyCreepPressedIcon = new ImageIcon(FilePaths.buttonPath + "BuyCreepButtonDown.png");
		buyCreepDisabledIcon = new ImageIcon(FilePaths.buttonPath + "BuyCreepButtonDisabled.png");
		
		//First, set up all of the alignment buttons
		neutralButton = new JButton();
		neutralButton.setBackground(ColorConstants.neutralColor);
		neutralButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //if pressed, set potential creep to purchase alignment to neutral
				if(creep != null) {
					creep.setAlignment(IAlignment.Alignment.NEUTRAL);
					setIconLabel(creep);
				}
			}
		});
		
		redButton = new JButton();
		redButton.setBackground(ColorConstants.redColor);
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(creep != null) {
					creep.setAlignment(IAlignment.Alignment.RED);
					setIconLabel(creep);
				}
			}
		});		
		
		greenButton = new JButton();
		greenButton.setBackground(ColorConstants.greenColor);
		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(creep != null) {
					creep.setAlignment(IAlignment.Alignment.GREEN);
					setIconLabel(creep);
				}
			}
		});		
		
		blueButton = new JButton();
		blueButton.setBackground(ColorConstants.blueColor);
		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(creep != null) {
					creep.setAlignment(IAlignment.Alignment.BLUE);
					setIconLabel(creep);
				}
			}
		});		
		
		yellowButton = new JButton();
		yellowButton.setBackground(ColorConstants.yellowColor);
		yellowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(creep != null) {
					creep.setAlignment(IAlignment.Alignment.YELLOW);
					setIconLabel(creep);
				}
			}
		});			
		
		//Setting up the buy button
		buyButton = new JButton(buyCreepIcon);
		buyButton.setBorder(BorderFactory.createEmptyBorder());
		buyButton.setFocusPainted(false);
		buyButton.setContentAreaFilled(false);	
		buyButton.setPressedIcon(buyCreepPressedIcon);
		buyButton.setRolloverIcon(buyCreepHoverIcon);
		buyButton.setDisabledIcon(buyCreepDisabledIcon);

		//On pressing button, add creep to queue and modify player's income and current gold accordingly
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Creep creepToAdd = Creep.copyCreep(creep);
				gc.getGame().getPlayer().increaseIncomePerWave(creepToAdd.getAdditionalGoldPerWave());
				gc.getGame().getPlayer().purchase(creepToAdd);
				creepQueue.enqueue(creepToAdd, creepIndex);
				gc.getSideBar().getPlayerStatsPanel().setGoldChange(" -" + creepToAdd.getPrice());
			}
		});
		
		//On mouseover, show potential change in player's gold
		buyButton.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (creep != null) {
						gc.getSideBar().getPlayerStatsPanel().setShowingGoldChange(true);
						gc.getSideBar().getPlayerStatsPanel().setGoldChange(" -" + creep.getPrice());
					}
				}
				
				public void mouseExited(MouseEvent e) {
					gc.getSideBar().getPlayerStatsPanel().setShowingGoldChange(false);
					if (buyButton.isEnabled())
						gc.getSideBar().getPlayerStatsPanel().setGoldChange(" ");
				}
			
		});

		//setting up the image to show the potential creep to purchase
		String path = FilePaths.imgPath + "blank.png";
		creepIcon = new ImageIcon(path);
		
		Image i = creepIcon.getImage();
		i = i.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
		creepIcon = new ImageIcon(i);  
		iconLabel = new JLabel(creepIcon);
		
		//laying everything out with gridbag
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 5, 0, 10);
		
		c.gridx = 1;
		c.gridy = 1;
		add(iconLabel, c);
		
		c.gridwidth = 1;
		c.gridheight = 3;
		c.ipady = 0;
		
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 10;
		c.insets.set(20, 0, 5, 5);
		c.gridx = 1;
		c.gridy = 0;
		add(neutralButton, c);
		c.gridx = 2;
		add(redButton, c);
		c.gridx = 3;
		add(greenButton, c);
		c.gridx = 4;
		add(blueButton, c);
		c.gridx = 5;
		c.insets.set(20, 0, 5, 0);
		add(yellowButton, c);
		
		updateAllowedButtons();
		
		c.ipady = 0;
		c.insets.set(0, 0, 5, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 5;
		add(buyButton, c);
	}

	/**
	 * Checks what buttons should be enabled and disabled. Whenever a creep is not selected or player cannot afford to buy creep,
	 * buy button is disabled. Alignment buttons are only selectable when a creep is selected.
	 */
	private void updateAllowedButtons() {
		if (creep == null || (creep != null && !gc.playerCanAfford(creep)) || !gc.playerCanAfford(creep)) {
			buyButton.setEnabled(false);
		} else {
			buyButton.setEnabled(true);
		}
		
		if(creep != null){
			neutralButton.setEnabled(true);
			redButton.setEnabled(true);
			greenButton.setEnabled(true);
			blueButton.setEnabled(true);
			yellowButton.setEnabled(true);	
		} else {
			neutralButton.setEnabled(false);
			redButton.setEnabled(false);
			greenButton.setEnabled(false);
			blueButton.setEnabled(false);
			yellowButton.setEnabled(false);			
		}
	}
	
	public void paintComponent(Graphics g) {
		updateAllowedButtons();
	}
	
	/**
	 * Modifies the icon displayed for the potential creep to purchase. Called by the CreepSelectionPanel. Whenever a different creep is selected,
	 * passes in the proper index and sets the creep icon accordingly.
	 * @param index of creep icon in creepTypes array
	 */
	public void setCreepByIndex(int index){
		creepIndex = index;
		if(index == -1){
			this.creep = null;
			String path = FilePaths.imgPath + "blank.png";
			creepIcon = new ImageIcon(path);
			
			Image i = creepIcon.getImage();
			i = i.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
			creepIcon = new ImageIcon(i);  
			iconLabel.setIcon(creepIcon);
			buyButton.setEnabled(false);
		} else {
			this.creep = Creep.createCreep(creepTypes[index], gc.getGame().getWavesSent());
			setIconLabel(creep);
			buyButton.setEnabled(true);
		}
	}
	
	/**
	 * Sets the icon for potential creep to purchase to the appropriate creep based off of type and and alignment
	 * @param creep with proper type and alignment
	 */
	public void setIconLabel(Creep c) {
		creepIcon = new ImageIcon(Creep.getImage(c.getType(), c.getAlignment()));
		
		Image i = creepIcon.getImage();
		i = i.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
		creepIcon = new ImageIcon(i);  
		iconLabel.setIcon(creepIcon);

	}
}
