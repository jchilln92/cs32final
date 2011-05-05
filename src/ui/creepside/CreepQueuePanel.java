package src.ui.creepside;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
		iconPanel.setPreferredSize(new Dimension(300, 30));
		JScrollPane scroller = new JScrollPane(iconPanel, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setPreferredSize(new Dimension(400, 40));
		
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
		iconPanel.setPreferredSize(new Dimension(iconPanel.getWidth() + 16, iconPanel.getHeight()));
		iconPanel.add(creepLabel, BorderLayout.LINE_START);
		iconPanel.revalidate();
	}

	public Creep dequeue(int index){
		ArrayList<Creep> waitingCreeps = gc.getGame().getYourCreeps();
		
		if (index < waitingCreeps.size()) {
			int nextIndex = 0;
			for(nextIndex = index; nextIndex < waitingCreeps.size()-1; nextIndex++){
				displayNext.get(nextIndex).setIcon(displayNext.get(nextIndex+1).getIcon());				
			}
			
			String path = FilePaths.imgPath + "blank.png";
			displayNext.get(nextIndex).setIcon(new ImageIcon(path));
			gc.getGame().getPlayer().setGold(gc.getGame().getPlayer().getGold() + waitingCreeps.get(index).getPrice());
			return waitingCreeps.remove(index);
		} else
			return null;
	}

	public int getNumberOfCreeps(){
		return gc.getGame().getYourCreeps().size();
	}
}
