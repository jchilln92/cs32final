package src.ui.side;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel displaying buttons that allow the user to purchase a tower.
 */
public class TowerPurchasePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton towerButton;
	
	public TowerPurchasePanel() {
		towerButton = new JButton("purchase");
		add(towerButton);
	}
}
