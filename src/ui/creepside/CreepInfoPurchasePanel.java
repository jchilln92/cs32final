package src.ui.creepside;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.FilePaths;
import src.core.Creep;
import src.core.IAlignment;
import src.ui.ColorConstants;
import src.ui.controller.GameController;
import src.ui.side.TowerStatsPanel;

public class CreepInfoPurchasePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController gc;
	
	private CreepQueuePanel creepQueue;
	private Creep cr;
	private ImageIcon creepIcon;
	private JLabel iconLabel;
	
	private JButton neutralButton;
	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;
	private JButton yellowButton;
	private JButton buyButton;
	private JButton cancelButton;
	
	private int creepIndex;
	
	private Creep.Type[] creepTypes = {Creep.Type.GENERIC, Creep.Type.FLYING, Creep.Type.BIG_GUY, Creep.Type.ASSASSIN, Creep.Type.FAST};

	public CreepInfoPurchasePanel(CreepQueuePanel cq, GameController controller) {
	
		super(new GridBagLayout());
		gc = controller;
		
		creepQueue = cq;
		creepIndex = -1;		
		neutralButton = new JButton();
		neutralButton.setBackground(ColorConstants.neutralColor);
		neutralButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cr != null)
					cr.setAlignment(IAlignment.Alignment.NEUTRAL);
			}
		});
		
		redButton = new JButton();
		redButton.setBackground(ColorConstants.redColor);
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cr != null)
					cr.setAlignment(IAlignment.Alignment.RED);
			}
		});		
		greenButton = new JButton();
		greenButton.setBackground(ColorConstants.greenColor);
		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cr != null)
					cr.setAlignment(IAlignment.Alignment.GREEN);
			}
		});			
		blueButton = new JButton();
		blueButton.setBackground(ColorConstants.blueColor);
		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cr != null)
					cr.setAlignment(IAlignment.Alignment.BLUE);
			}
		});			
		yellowButton = new JButton();
		yellowButton.setBackground(ColorConstants.yellowColor);
		yellowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cr != null)
					cr.setAlignment(IAlignment.Alignment.YELLOW);
			}
		});			
		
		buyButton = new JButton("Buy");
		buyButton.setBackground(Color.ORANGE);
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gc.getGame().getPlayer().purchase(cr);
				creepQueue.enqueue(cr, creepIndex);
			}
		});	
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setEnabled(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCreepByIndex(-1);
			}
		});	
		
		GridBagConstraints c = new GridBagConstraints();
		
		String path = FilePaths.imgPath + "blank.png";
		creepIcon = new ImageIcon(path);
		
		Image i = creepIcon.getImage();
		i = i.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);  
		creepIcon = new ImageIcon(i);  
		iconLabel = new JLabel(creepIcon);
		

		c.insets = new Insets(0, 5, 0, 10);
		
		c.gridx = 0;
		c.gridy = 1;

		add(iconLabel, c);
		c.gridwidth = 1;
		c.gridheight = 3;
		c.ipady = 0;
		
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 10;
		c.insets = new Insets(0, 5, 0, 0);
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
		add(yellowButton, c);
		
		updateAllowedButtons();
		c.insets = new Insets(0, 5, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 5;
		c.gridheight = 3;
		add(buyButton, c);
		c.gridy = 5;
		add(cancelButton, c);
	}

	private void updateAllowedButtons() {
		if ( cr == null || (cr != null && !gc.playerCanAfford(cr)) || !gc.playerCanAfford(cr)) {
			buyButton.setEnabled(false);
		} 
		else{
			buyButton.setEnabled(true);
		}
		
		if(cr != null){
			neutralButton.setEnabled(true);
			redButton.setEnabled(true);
			greenButton.setEnabled(true);
			blueButton.setEnabled(true);
			yellowButton.setEnabled(true);	
		}
		else{
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
	
	
	public void setCreepByIndex(int index){
		creepIndex = index;
		if(index == -1){
			this.cr = null;
			String path = FilePaths.imgPath + "blank.png";
			creepIcon = new ImageIcon(path);
			
			Image i = creepIcon.getImage();
			i = i.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);  
			creepIcon = new ImageIcon(i);  
			iconLabel.setIcon(creepIcon);
			buyButton.setEnabled(false);
			cancelButton.setEnabled(false);
			
			

		}
		else{
			this.cr = Creep.createCreep(creepTypes[index], gc.getGame().getWavesSent());
			String path = FilePaths.imgPath + "creep-icon"+(index+1)+".png";
			creepIcon = new ImageIcon(path);
			
			Image i = creepIcon.getImage();
			i = i.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);  
			creepIcon = new ImageIcon(i);  
			iconLabel.setIcon(creepIcon);
			buyButton.setEnabled(true);
			cancelButton.setEnabled(true);
		}
	}
}
