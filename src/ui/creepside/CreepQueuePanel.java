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
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.FilePaths;
import src.core.Creep;
import src.ui.controller.GameController;

public class CreepQueuePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GameController gc;
	
	//private ArrayList<Creep> waitingCreeps;
	private ArrayList<JLabel> displayNext;
	
	private JButton dequeueButton;
	
	private CreepInfoPurchasePanel infoPurchase;
	
	private Creep.Type[] creepTypes = {Creep.Type.GENERIC, Creep.Type.FLYING, Creep.Type.BIG_GUY, Creep.Type.ASSASSIN, Creep.Type.FAST};
	
	public CreepQueuePanel(GameController controller){
		super(new GridBagLayout());
		
		gc = controller;
		
		//waitingCreeps = new ArrayList<Creep>();
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
			iconLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dequeue(index);
				}

			});	
			displayNext.add(iconLabel);
			c.gridx = iconIndex;
			add(displayNext.get(iconIndex), c);
		}
		
		c.insets = new Insets(0, 160, 0, 0);
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

	public void paintComponent(Graphics g) {
		if(getNumberOfCreeps() == 0){
			for(int iconIndex = 0; iconIndex < 30; iconIndex++){
				String path = FilePaths.imgPath + "blank.png";
				ImageIcon blankIcon = new ImageIcon(path);
				displayNext.get(iconIndex).setIcon(blankIcon);
			}
		}
	}
	
	public void setInfoPurchase(CreepInfoPurchasePanel cip){
		infoPurchase = cip;
	}
	public void enqueue(Creep c, int index){
		ArrayList<Creep> waitingCreeps = gc.getGame().getYourCreeps();
		if(waitingCreeps.size() <displayNext.size()){			
			String path = FilePaths.imgPath + "creep-icon"+(index+1)+".png";
			displayNext.get(waitingCreeps.size()).setIcon(new ImageIcon(path));
			gc.getGame().getYourCreeps().add(c);
			
		
		}

	}
	public Creep dequeue(int index){
		ArrayList<Creep> waitingCreeps = gc.getGame().getYourCreeps();
		if(index < waitingCreeps.size()){
			int nextIndex = 0;
			for(nextIndex = index; nextIndex < waitingCreeps.size()-1; nextIndex++){
				displayNext.get(nextIndex).setIcon(displayNext.get(nextIndex+1).getIcon());				
			}
			String path = FilePaths.imgPath + "blank.png";
			displayNext.get(nextIndex).setIcon(new ImageIcon(path));
			gc.getGame().getPlayer().setGold(gc.getGame().getPlayer().getGold() + waitingCreeps.get(index).getPrice());
			return waitingCreeps.remove(index);
		}
		else
			return null;
	}
	
	public int getNumberOfCreeps(){
		return gc.getGame().getYourCreeps().size();
	}
}
