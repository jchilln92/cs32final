package src.ui.creepside;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	
	private CreepInfoPurchasePanel infoPurchase;
	
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
		c.insets = new Insets(0, 0, 0, 1);
		for(int iconIndex = 0; iconIndex < 30; iconIndex++){
			JLabel iconLabel = new JLabel(blankIcon);

			final int index = iconIndex;
			iconLabel.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dequeue(index);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				}
			});	
			displayNext.add(iconLabel);
			c.gridx = iconIndex;
			add(displayNext.get(iconIndex), c);
		}
		
		c.insets = new Insets(0, 150, 0, 0);
		c.ipady = 0;
		dequeueButton = new JButton("Cancel All");
		dequeueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int x = 0; x < displayNext.size(); x++){
					dequeue(0);
				}
			}
		});	
		c.gridx = 30;
		add(dequeueButton, c);
	}
	
	public void setInfoPurchase(CreepInfoPurchasePanel cip){
		infoPurchase = cip;
	}
	public void enqueue(Creep c, int index){
		if(waitingCreeps.size() <displayNext.size()){
			String path = FilePaths.imgPath + "creep-icon"+(index+1)+".png";
			displayNext.get(waitingCreeps.size()).setIcon(new ImageIcon(path));
			waitingCreeps.add(c);
			if(waitingCreeps.size() == displayNext.size()){
				
			}
		
		}

	}
	public Creep dequeue(int index){
		if(index < waitingCreeps.size()){
			int nextIndex = 0;
			for(nextIndex = index; nextIndex < waitingCreeps.size()-1; nextIndex++){
				displayNext.get(nextIndex).setIcon(displayNext.get(nextIndex+1).getIcon());				
			}
			String path = FilePaths.imgPath + "blank.png";
			displayNext.get(nextIndex).setIcon(new ImageIcon(path));
			return waitingCreeps.remove(index);
		}
		else
			return null;
		
		/*if(waitingCreeps.size() > 0){
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
			return null;*/
	}
}
