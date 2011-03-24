package src;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameMain extends JFrame {
	
	public GameMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500); // TODO: Figure out resize and/or move this to another file
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GameMain main = new GameMain();
				main.setVisible(true);
			}
		});
	}
}
