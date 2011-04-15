package src;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import src.core.Creep;
import src.core.Tower;

/**
 * This class will be replaced and/or heavily modified at some point.  It is currently in place so that
 * other features may be tested.
 */
public class Runner implements Runnable {
	public ArrayList<Creep> creeps;
	public ArrayList<Tower> towers;
	
	public void run() {
		while (true) {
			stepCreeps();
			doTowers();
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stepCreeps() {
		double speed = .05; // tiles per frame
		
		for (Creep c : creeps) {
			Point2D.Double direction = c.getNextDirection();
			c.setPosition(new Point2D.Double(direction.getX() * speed + c.getPosition().getX(), 
											 direction.getY() * speed + c.getPosition().getY()));
		}
	}
	
	public void doTowers() {
		for (Tower t : towers) {
			for (Creep c : creeps) {
				if (c.getPosition().distance(t.getX(), t.getY()) < t.getRadius()) {
					c.applyDamage(t.getDamage());
					break;
				}
			}
		}
	}
}
