package src;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import src.core.Creep;
import src.core.Map;
import src.core.Tower;
import src.ui.IDrawableCreep;
import src.ui.MapComponent;
import src.ui.side.Sidebar;

public class GameMain extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600); // TODO: Figure out resize and/or move this to another file
		
		getContentPane().setLayout(new BorderLayout());
		
		Map m = Map.demoMap();
		
		Creep c = new Creep();
		c.setPath(m.getPath());
		c.setPosition(new Point2D.Double(0, 0));
		
		ArrayList<Creep> creeps = new ArrayList<Creep>();
		creeps.add(c);
		ArrayList<IDrawableCreep> creeps2 = new ArrayList<IDrawableCreep>(creeps);
		
		MapComponent mc = new MapComponent(Map.demoMap());
		mc.setSize(600, 600);
		mc.setGridOn(true);
		mc.setPlacingTower(new Tower());
		mc.creeps = creeps2;
		getContentPane().add(mc, BorderLayout.LINE_START);
		
		Sidebar s = new Sidebar();
		getContentPane().add(s, BorderLayout.LINE_END);
		
		Runner r = new Runner();
		r.creeps = creeps;
		Thread thread = new Thread(r);
		thread.start();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final GameMain main = new GameMain();
				main.setVisible(true);
				
				// refresh the window at about 30 fps
				Timer t = new Timer(33, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						main.repaint();
					}
				});
				
				t.start();
			}
		});
	}
}
