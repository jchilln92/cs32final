package src;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import src.core.Creep;
import src.core.Game;
import src.core.Tower;

/**
 * This class will be replaced and/or heavily modified at some point. It is
 * currently in place so that other features may be tested.
 */
public class Runner implements Runnable {
	public static long tickDuration = 30; // how long each tick is, in milliseconds

	private GameController gc;

	public void run() {
		while (true) {
			gc.tick();

			try {
				Thread.sleep(tickDuration);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setGameController(GameController gc) {
		this.gc = gc;
	}
}
