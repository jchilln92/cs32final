package src.ui.side;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Sidebar extends JPanel {
	private static final long serialVersionUID = 1L;

	public Sidebar() {
		BoxLayout layoutManager = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layoutManager);

		add(new PlayerStatsPanel(), BorderLayout.PAGE_START);
	}
}
