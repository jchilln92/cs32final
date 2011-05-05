package src.ui.creepside;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.FilePaths;
import src.core.Creep;

public class CreepQueuePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Creep> waitingCreeps;
	private ArrayList<JLabel> displayNext;
	
	public CreepQueuePanel(){
		super(new GridBagLayout());
		
		waitingCreeps = new ArrayList<Creep>();
		displayNext = new ArrayList<JLabel>();
		
		String path = FilePaths.imgPath + "blank.png";
		ImageIcon blankIcon = new ImageIcon(path);
		
		Image i = blankIcon.getImage();
		i = i.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);  
		blankIcon = new ImageIcon(i);  
		
		GridBagConstraints c = new GridBagConstraints();
		for(int iconIndex = 0; iconIndex < 10; iconIndex++){
			JLabel iconLabel = new JLabel(blankIcon);
			displayNext.add(iconLabel);
			c.gridx = iconIndex;
			add(displayNext.get(iconIndex), c);
		}
		


	}
	
	public void enqueue(Creep c){
		
	}
	public Creep dequeue(){
		if(waitingCreeps.size() > 0)
			return waitingCreeps.get(0);
		else
			return null;
	}
}
