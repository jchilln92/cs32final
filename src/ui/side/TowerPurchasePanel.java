package src.ui.side;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
import src.GameController;
import src.core.Damage;
import src.core.Tower;

/**
 * Panel displaying buttons that allow the user to purchase a tower.
 */
public class TowerPurchasePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameController gc;
	
	private static final String purchaseTowersText = "Purchase Towers:";
	
	private JLabel purchaseTowersLabel;
	private TowerStatsPanel towerStats;
	private JButton[] towerButtons;
	private Tower.Type[] buttonTypes = {Tower.Type.GUN, Tower.Type.ANTIAIR, Tower.Type.SLOWING, Tower.Type.MORTAR,
			  							Tower.Type.FRIEND, Tower.Type.FLAME, Tower.Type.STASIS, Tower.Type.HTA};
	
	public TowerPurchasePanel(GameController controller) {
		super(new GridBagLayout());
		
		gc = controller;
		
		purchaseTowersLabel = new JLabel(purchaseTowersText);
		towerStats = new TowerStatsPanel();
		towerButtons = new JButton[8];
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(purchaseTowersLabel, c);
		
		for (int index = 0; index < 8; index++) {
			String path = FilePaths.imgPath + "tower-icon"+(index+1)+".png";
			
			ImageIcon towerIcon = new ImageIcon(path);
			JButton towerButton = new JButton(towerIcon);
			towerButtons[index] = towerButton;
			final Tower.Type type = buttonTypes[index];
			
			towerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gc.beginPurchasingTower(Tower.createTower(type));
				}
			});
			
			towerButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					towerStats.setTower(Tower.createTower(type));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					towerStats.setTower(null);
				}
			});
			
			if(index == 7)
				c.gridx = 2;
			else
				c.gridx = 0 + index % 3;
			
			c.gridy = 1 + index / 3;
			c.fill = GridBagConstraints.NONE;
			add(towerButton, c);
		}
		
		updateAllowedButtons();
		
		c.gridx = 1;
		c.gridy = 4;
		add(towerStats, c);
	}
	
	private void updateAllowedButtons() {
		for (int i = 0; i < 8; i++) {
			JButton b = towerButtons[i];
			Tower.Type type = buttonTypes[i];
			
			Tower t = Tower.createTower(type);
			
			if (!gc.playerCanAfford(t)) {
				b.setEnabled(false);
			} else {
				b.setEnabled(true);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		updateAllowedButtons();
	}
}
