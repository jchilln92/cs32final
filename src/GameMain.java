package src;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import src.core.Map;
import src.ui.MapComponent;

public class GameMain extends JFrame {
	
	public GameMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000); // TODO: Figure out resize and/or move this to another file
		
		getContentPane().setLayout(new FlowLayout());
		MapComponent mc = new MapComponent(Map.demoMap());
		mc.setSize(700, 700);
		mc.setGridOn(false);
		getContentPane().add(mc);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameMain main = new GameMain();
				main.setVisible(true);
			}
		});
	}
}
