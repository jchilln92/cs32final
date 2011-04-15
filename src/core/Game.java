package src.core;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Game {
	private Map map;
	private ArrayList<Creep> creeps;
	private ArrayList<Tower> towers;
	private Player player;
	private int elapsedTime; // the total game time, in "ticks"

	public void tick() {
		elapsedTime++;

		// do a bunch of stuff to update the state of the game
		stepCreeps();
		doTowers();
	}

	private void stepCreeps() {
		double speed = .05; // tiles per tick

		Iterator<Creep> it = creeps.iterator();

		while (it.hasNext()) {
			Creep c = it.next();
			
			if (c.getHealthFraction() <= 0) {
				it.remove();
				player.reapReward(c.getReward());
				continue;
			}
			
			Point2D.Double direction = c.getNextDirection();

			if (direction == null) { // the creep reached the end of the path
				player.decreaseHealth(c.getDamageToBase());
				it.remove();
				continue;
			}

			c.setPosition(new Point2D.Double(direction.getX() * speed
					+ c.getPosition().getX(), direction.getY() * speed
					+ c.getPosition().getY()));
		}
	}

	private void doTowers() {
		for (Tower t : towers) {
			for (Creep c : creeps) {
				if (c.getPosition().distance(t.getX(), t.getY()) < t.getRadius()) {
					c.applyDamage(t.getDamage());
					break;
				}
			}
		}
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Collection<Creep> getCreeps() {
		return creeps;
	}

	public void setCreeps(ArrayList<Creep> creeps) {
		this.creeps = creeps;
	}

	public Collection<Tower> getTowers() {
		return towers;
	}

	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}
