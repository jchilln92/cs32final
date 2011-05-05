package src.ui.creepside;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.FilePaths;
import src.core.Creep;

public class CreepQueuePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Creep> waitingCreeps;
	private ArrayList<JLabel> displayNext;
	
	private JButton dequeueButton;
	
	private Creep.Type[] creepTypes = {Creep.Type.GENERIC, Creep.Type.FLYING, Creep.Type.BIG_GUY, Creep.Type.ASSASSIN, Creep.Type.FAST};
	
	public CreepQueuePanel(){
		super(new GridBagLayout());
		
		waitingCreeps = new ArrayList<Creep>();
		displayNext = new ArrayList<JLabel>();
		
		String path = FilePaths.imgPath + "blank.png";
		ImageIcon blankIcon = new ImageIcon(path);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 40);
		for(int iconIndex = 0; iconIndex < 10; iconIndex++){
			JLabel iconLabel = new JLabel(blankIcon);
			displayNext.add(iconLabel);
			c.gridx = iconIndex;
			add(displayNext.get(iconIndex), c);
		}
		
		dequeueButton = new JButton("Dequeue");
		dequeueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dequeue();
			}
		});	
		c.gridx = 10;
		add(dequeueButton, c);
		


	}
	
	public void enqueue(Creep c, int index){
		if(waitingCreeps.size() <displayNext.size()){
			String path = FilePaths.imgPath + "creep-icon"+(index+1)+".png";
			displayNext.get(waitingCreeps.size()).setIcon(new ImageIcon(path));
		}
		else{
			String path = FilePaths.imgPath + "creep-icon"+(index+1)+".png";
			displayNext.add(new JLabel(new ImageIcon(path)));
		}
		waitingCreeps.add(c);
	}
	public Creep dequeue(){
		if(waitingCreeps.size() > 0){
			int nextIndex = 0;
			if(waitingCreeps.size() > 10){

				String path = FilePaths.imgPath + "blank.png";
				for(nextIndex = 0; nextIndex < displayNext.size()-1; nextIndex++){
					displayNext.get(nextIndex).setIcon(displayNext.get(nextIndex+1).getIcon());
				}
				displayNext.get(nextIndex).setIcon(new ImageIcon(path));
			}
			else if (displayNext.size() > waitingCreeps.size()){

				for(nextIndex = 0; nextIndex < waitingCreeps.size(); nextIndex++){
					displayNext.get(nextIndex).setIcon(displayNext.get(nextIndex+1).getIcon());				
				}
				for(nextIndex = nextIndex; nextIndex < displayNext.size(); nextIndex++){
					String path = FilePaths.imgPath + "blank.png";
					displayNext.get(nextIndex).setIcon(new ImageIcon(path));	
				}
			}
			return waitingCreeps.remove(0);
		}
		else
			return null;
	}
}
