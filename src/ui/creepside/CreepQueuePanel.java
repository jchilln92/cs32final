package src.ui.creepside;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import src.FilePaths;
import src.core.Creep;
import src.ui.controller.GameController;

public class CreepQueuePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int initialCreepPanelSize = 400;
	
	private GameController gc;
	private ArrayList<JLabel> displayNext;
	
	private JButton dequeueButton;
	
	private JPanel iconPanel;
	private CreepInfoPurchasePanel infoPurchase;
	private Creep.Type[] creepTypes = {Creep.Type.GENERIC, Creep.Type.FLYING, Creep.Type.BIG_GUY, Creep.Type.ASSASSIN, Creep.Type.FAST};
	
	public CreepQueuePanel(GameController controller){
		super(new GridBagLayout());
		
		gc = controller;
		displayNext = new ArrayList<JLabel>();
		
		String path = FilePaths.imgPath + "blank.png";
		ImageIcon blankIcon = new ImageIcon(path);
		
		// setup a scrolling panel so that we can add as many creeps as needed
		iconPanel = new JPanel();
		iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.LINE_AXIS));
		iconPanel.setPreferredSize(new Dimension(initialCreepPanelSize, 16));
		JScrollPane scroller = new JScrollPane(iconPanel, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setPreferredSize(new Dimension(405, 50));
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(scroller, c);
		
		c.insets.set(0, 0, 0, 0);
		c.ipady = 0;
		dequeueButton = new JButton("Cancel All");
		dequeueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int x = 0; x < displayNext.size(); x++){
					dequeue(0);
				}
			}
		});	
		c.gridx = 1;
		add(dequeueButton, c);
	}
	
	public void setInfoPurchase(CreepInfoPurchasePanel cip){
		infoPurchase = cip;
	}

	public void enqueue(Creep c, int index){
		gc.getGame().getYourCreeps().add(c);
		
		String path = FilePaths.imgPath + "creep-icon" + (index + 1) + ".png";
		ImageIcon creepIcon = new ImageIcon(path);
		JLabel creepLabel = new JLabel();
		creepLabel.setIcon(creepIcon);
		
		// resize the panel that holds the creep icons
		final double creepBoxSize = 26;
		if (getNumberOfCreeps() * creepBoxSize > initialCreepPanelSize)
			iconPanel.setPreferredSize(new Dimension(iconPanel.getWidth() + (int)creepBoxSize, iconPanel.getHeight()));
		
		// create a box to hold this icon and the padding around it
		Box iconBox = Box.createHorizontalBox();
		iconBox.add(Box.createHorizontalStrut(5));
		iconBox.add(creepLabel);
		iconBox.add(Box.createHorizontalStrut(5));
		
		final Box box = iconBox;
		creepLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (getNumberOfCreeps() * creepBoxSize > initialCreepPanelSize) 
					iconPanel.setPreferredSize(new Dimension(iconPanel.getWidth() - (int)creepBoxSize, iconPanel.getHeight()));
					
				dequeue(gc.getGame().getYourCreeps().size()-1);
				iconPanel.remove(box);
				
				iconPanel.revalidate();
			}
		});
		
		iconPanel.add(iconBox);
		iconPanel.revalidate();
	}

	public void dequeue(int index){
		gc.getGame().getYourCreeps().remove(index);
	}

	public int getNumberOfCreeps(){
		return gc.getGame().getYourCreeps().size();
	}
}
