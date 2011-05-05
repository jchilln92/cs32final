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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.FilePaths;
import src.core.Creep;
import src.core.IAlignment;
import src.ui.ColorConstants;
import src.ui.side.TowerStatsPanel;

public class CreepInfoPurchasePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
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
	
	private Creep.Type[] creepTypes = {Creep.Type.GENERIC, Creep.Type.FLYING, Creep.Type.BIG_GUY, Creep.Type.ASSASSIN, Creep.Type.FAST};

	
	public CreepInfoPurchasePanel() {
		super(new GridBagLayout());
		
	
		neutralButton = new JButton();
		neutralButton.setBackground(ColorConstants.neutralColor);
		neutralButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(creep != null)
				//	creep.setAlignment(IAlignment.Alignment.NEUTRAL);
			}
		});
		
		redButton = new JButton();
		redButton.setBackground(ColorConstants.redColor);
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});		
		greenButton = new JButton();
		greenButton.setBackground(ColorConstants.greenColor);
		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});			
		blueButton = new JButton();
		blueButton.setBackground(ColorConstants.blueColor);
		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});			
		yellowButton = new JButton();
		yellowButton.setBackground(ColorConstants.yellowColor);
		yellowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});			
		buyButton = new JButton("Buy");
		buyButton.setBackground(Color.ORANGE);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.WHITE);
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
		

		c.insets = new Insets(0, 5, 5, 0);
		c.gridx = 2;
		c.gridy = 0;

		add(iconLabel, c);
		c.gridwidth = 1;
		c.gridheight = 1;
		//c.ipadx = -20;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 1;
		add(neutralButton, c);
		c.gridx = 1;
		add(redButton, c);
		c.gridx = 2;
		add(greenButton, c);
		c.gridx = 3;
		add(blueButton, c);
		c.gridx = 4;
		add(yellowButton, c);
		
		c.insets = new Insets(0, 5, 5, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;
		c.gridheight = 3;
		add(buyButton, c);
		c.gridy = 5;
		add(cancelButton, c);

	}
	
	public void setCreepByIndex(int index){

		if(index == -1){
			this.cr = null;
			String path = FilePaths.imgPath + "blank.png";
			creepIcon = new ImageIcon(path);
			
			Image i = creepIcon.getImage();
			i = i.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);  
			creepIcon = new ImageIcon(i);  
			iconLabel.setIcon(creepIcon);
			
			

		}
		else{
			this.cr = Creep.createCreep(creepTypes[index]);
			String path = FilePaths.imgPath + "creep-icon"+(index+1)+".png";
			creepIcon = new ImageIcon(path);
			
			Image i = creepIcon.getImage();
			i = i.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);  
			creepIcon = new ImageIcon(i);  
			iconLabel.setIcon(creepIcon);
			

		}

	}
	
	
}
