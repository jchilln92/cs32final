package src.ui.creepside;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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

/**
 * Panel used to display the creep queue portion of the creep purchase bottom bar. Visually shows all of the creeps about to be sent to your opponent, and
 * allows a user to cancel any given creep by clicking on it in the queue.
 */
public class CreepQueuePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int initialCreepPanelSize = 450;
	
	private GameController gc;
	private JButton dequeueButton;
	
	private JPanel iconPanel;
	
	private ImageIcon cancelAllIcon;
	private ImageIcon cancelAllHoverIcon;
	private ImageIcon cancelAllPressedIcon;
	private ImageIcon cancelAllDisabledIcon;
	
	public CreepQueuePanel(GameController controller){
		super(new GridBagLayout());
		gc = controller;
		
		//setting up ImageIcons
		cancelAllIcon = new ImageIcon(FilePaths.buttonPath + "CancelAllButton.png");
		cancelAllHoverIcon = new ImageIcon(FilePaths.buttonPath + "CancelAllButtonHover.png");
		cancelAllPressedIcon = new ImageIcon(FilePaths.buttonPath + "CancelAllButtonDown.png");
		cancelAllDisabledIcon = new ImageIcon(FilePaths.buttonPath + "CancelAllButtonDisabled.png");
				
		// setup a scrolling panel so that we can add as many creeps as needed (adds scrollbar when filled)
		iconPanel = new JPanel();
		iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.LINE_AXIS));
		iconPanel.setPreferredSize(new Dimension(initialCreepPanelSize, 16));
		JScrollPane scroller = new JScrollPane(iconPanel, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setPreferredSize(new Dimension(initialCreepPanelSize + 5, 50));
		scroller.setMinimumSize(new Dimension(initialCreepPanelSize + 5, 50));
				
		//set up dequeue button to allow for canceling all purchased creeps
		dequeueButton = new JButton(cancelAllIcon);
		dequeueButton.setBorder(BorderFactory.createEmptyBorder());
		dequeueButton.setFocusPainted(false);
		dequeueButton.setContentAreaFilled(false);	
		dequeueButton.setPressedIcon(cancelAllPressedIcon);
		dequeueButton.setRolloverIcon(cancelAllHoverIcon);
		dequeueButton.setDisabledIcon(cancelAllDisabledIcon);

		dequeueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dequeueAll();
			}
		});	
		
		//laying out with gridbag
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(scroller, c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets.set(0, 30, 0, 0);
		add(dequeueButton, c);
	}

	/**
	 * adds a creep to the list of creeps to be sent to your opponent, and visually adds it to the "creep queue"
	 */
	public void enqueue(Creep c, int index){
		gc.getGame().getYourCreeps().add(c);

		Image i = Creep.getImage(c.getType(), c.getAlignment());
		i = i.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		
		ImageIcon creepIcon = new ImageIcon(i);
		
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
		//Setting it up so when a user clicks on a creep in the queue, removes creep
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
	
	/**
	 * removes a creep to the list of creeps to be sent to your opponent, and visually removes it to the "creep queue"
	 */
	public void dequeue(int index){
		gc.getGame().getPlayer().increaseIncomePerWave(-1 * gc.getGame().getYourCreeps().get(index).getAdditionalGoldPerWave());
		gc.getGame().getPlayer().setGold(gc.getGame().getPlayer().getGold() + gc.getGame().getYourCreeps().get(index).getPrice());
		gc.getGame().getYourCreeps().remove(index);
	}
	
	/**
	 * simply dequeue's every creep in the queue
	 */
	public void dequeueAll() {
		int maxSize = gc.getGame().getYourCreeps().size();
		for (int x = 0; x < maxSize; x++)
			dequeue(0);
		
		iconPanel.removeAll();
		iconPanel.setPreferredSize(new Dimension(initialCreepPanelSize, 16));
		iconPanel.revalidate();
	}
	
	public void paintComponent(Graphics g) {
		//If a wave is sent (hence, number of creeps = 0), set the dequeue button the false, and call dequeueAll to update the iconPanel
		if (getNumberOfCreeps() == 0) {
			dequeueAll();
			dequeueButton.setEnabled(false);
		} else
			dequeueButton.setEnabled(true);
	}

	public int getNumberOfCreeps(){
		return gc.getGame().getYourCreeps().size();
	}
}
