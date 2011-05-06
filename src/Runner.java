package src;

import src.ui.controller.GameController;

/**
 * A class that runs on a separate thread, and whose only duty is to send a "tick"
 * message at some predefined time interval.  This is used to run games.
 */
public class Runner implements Runnable {
	public static long tickDuration = 33; // how long each tick is, in milliseconds

	private GameController gc;

	public void run() {
		while (true) {
			gc.tick();

			try {
				Thread.sleep(tickDuration);
			} catch (InterruptedException e) {
				// ignore interruption, we will only ever do it on purpose
			}
		}
	}

	public void setGameController(GameController gc) {
		this.gc = gc;
	}
}
