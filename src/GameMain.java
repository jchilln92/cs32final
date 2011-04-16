package src;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import src.core.Creep;
import src.core.Damage;
import src.core.Game;
import src.core.Map;
import src.core.Player;
import src.core.Tower;
import src.ui.MapComponent;
import src.ui.side.Sidebar;

public class GameMain extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameMain() {
		// set up window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600); // TODO: Figure out resize and/or move this to
							// another file

		getContentPane().setLayout(new BorderLayout());

		// set up some test data that will be run
		Map m = Map.demoMap();

		// initialize a game
		Game g = new Game();

		// single creep
		Creep c = new Creep();
		c.setPath(m.getPath());
		c.setPosition(m.getPath().getPoint(0));

		ArrayList<Creep> creeps = new ArrayList<Creep>();
		creeps.add(c);
		
		// make a giant wave and send it, just as a test
		ArrayList<Creep> wave = new ArrayList<Creep>();
		for (int i = 0; i < 1000; i++) {
			Creep waveCreep = new Creep();
			wave.add(waveCreep);
		}

		// single tower
		Tower t = new Tower();
		Damage d = new Damage();
		d.setInstantDamage(.1);
		t.setDamage(d);
		t.setRadius(4);
		t.setX(7);
		t.setY(8);
		
		ArrayList<Tower> towers = new ArrayList<Tower>();
		towers.add(t);

		// a player
		Player p = new Player();

		g.setMap(m);
		g.setCreeps(creeps);
		g.setTowers(towers);
		g.setPlayer(p);
		g.sendWave(wave);
		
		// set up a gamecontroller to mediate interaction between backend and frontend
		GameController gc = new GameController();
		gc.setGame(g);

		// map component to draw everything
		MapComponent mc = new MapComponent(Map.demoMap());
		mc.setSize(600, 600);
		mc.setGridOn(true);
		mc.setGameController(gc);
		getContentPane().add(mc, BorderLayout.LINE_START);

		// setup sidebar
		Sidebar s = new Sidebar(gc);
		getContentPane().add(s, BorderLayout.LINE_END);

		Runner r = new Runner();
		r.setGameController(gc);
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
