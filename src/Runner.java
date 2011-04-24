package src;

/**
 * A class that runs on a separate thread, and whose only duty is to send a "tick"
 * message at some predefined time interval.
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
				// TODO Auto-generated catch block
				//should we remove this? we probably should, but i want to double check.
				e.printStackTrace();
			}
		}
	}

	public void setGameController(GameController gc) {
		this.gc = gc;
	}
}
