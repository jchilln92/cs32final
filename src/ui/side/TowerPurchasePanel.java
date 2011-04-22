package src.ui.side;

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
	private JLabel towerInfoLabel;
	private JLabel damageLabel;
	private JLabel rangeLabel;
	private JLabel abilitiesLabel;
	private JLabel costLabel;
	
	private ArrayList<JButton> towerButtons;
	
	public TowerPurchasePanel(GameController controller) {
		super(new GridBagLayout());
		
		gc = controller;
		
		purchaseTowersLabel = new JLabel(purchaseTowersText);
		towerInfoLabel = new JLabel(" ");
		damageLabel = new JLabel(" ");
		rangeLabel = new JLabel(" ");
		abilitiesLabel = new JLabel(" ");
		costLabel = new JLabel(" ");
		towerButtons = new ArrayList<JButton>();
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(purchaseTowersLabel, c);
	
		Tower.Type types[] = {Tower.Type.GUN, Tower.Type.ANTIAIR, Tower.Type.SLOWING, Tower.Type.MORTAR,
							  Tower.Type.FRIEND, Tower.Type.FLAME, Tower.Type.STASIS, Tower.Type.HTA};
		
		for (int index = 0; index < 8; index++) {
			String path = FilePaths.imgPath + "tower-icon1.png";
			//String path = "assets/tower-icon"+index+1".png";
			ImageIcon towerIcon = new ImageIcon(path);
			JButton towerButton = new JButton(towerIcon);
			final Tower.Type type = types[index];
			
			towerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gc.beginPurchasingTower(Tower.createTower(type));
				}
			});
			
			towerButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					Tower t = Tower.createTower(type);
					towerInfoLabel.setText("Info: ");
					damageLabel.setText("Damage: ");
					rangeLabel.setText("Range: " + Double.toString(t.getRadius()));
					abilitiesLabel.setText("Abilities: ");
					costLabel.setText("Cost: ");
				}

				@Override
				public void mouseExited(MouseEvent e) {
					towerInfoLabel.setText(" ");
					damageLabel.setText(" ");
					rangeLabel.setText(" ");
					abilitiesLabel.setText(" ");
					costLabel.setText(" ");
				}
			});
			
			towerButtons.add(towerButton);
			if(index == 7)
				c.gridx = 2;
			else
				c.gridx = 0+index%3;
			c.gridy = 1+index/3;
			c.fill = GridBagConstraints.NONE;
			add(towerButton, c);
		}
		c.gridx = 1;
		c.gridy = 4;
		add(towerInfoLabel, c);
		c.gridy = 5;
		add(damageLabel, c);
		c.gridy = 6;
		add(rangeLabel, c);
		c.gridy = 7;
		add(abilitiesLabel, c);
		c.gridy = 8;
		add(costLabel, c);

	}
}
