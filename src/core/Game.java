package src.core;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import src.WaveGenerator;
/**
 * Represents a game and handles logic of creep movement and tower attacks.
 */
public class Game {
	// the map on which this game takes place
	private Map map;
	
	// the bullets, creeps, and towers currently in play
	private ArrayList<Bullet> bullets;
	private ArrayList<Creep> creeps;
	private ArrayList<Tower> towers;
	
	protected Player player;
	private int elapsedTime; // the total game time, in "ticks"
	protected int wavesSent; // the total number of waves sent
	private int lastWaveTime; // the elapsedTime when the last wave was sent
	
	private ArrayList<Creep> yourCreeps; // in a multiplayer game, the creeps you will send to the opponent
	
	private DistanceFromTowerComparator tdComparator;
	private HealthComparator hComparator;
	
	private LinkedList<Creep> creepQueue; // the creeps that are waiting to be sent out onto the map
	private int lastCreepTime; // the time the last creep was sent

	public Game() {
		player = new Player();
		bullets = new ArrayList<Bullet>();
		towers = new ArrayList<Tower>();
		creeps = new ArrayList<Creep>();
		creepQueue = new LinkedList<Creep>();
		tdComparator = new DistanceFromTowerComparator();
		hComparator = new HealthComparator();
		yourCreeps = new ArrayList<Creep>();
	}
	
	/**
	 * Performs all of the actions associated with one "tick" during the game.
	 * This includes updating creep positions and applying damage to creeps,
	 * among other things.
	 */
	public void tick() {
		if (isOver()) return; // do nothing if the game is over
		
		elapsedTime++;
		
		// time to send another wave
		if (elapsedTime - lastWaveTime >= CoreConstants.waveTime) {
			sendNextWave();
		}
		
		// time to send another creep, if there is one...
		if (creepQueue.size() != 0 && elapsedTime - lastCreepTime > CoreConstants.creepDelay) {
			lastCreepTime = elapsedTime;
			
			Creep toSend = creepQueue.poll();
			CreepPath path = toSend.isFlying() ? map.getFlyingPath() : map.getPath();
			
			toSend.setPath(path);
			toSend.setPosition(path.getPoint(0));
			
			synchronized (creeps) {
				creeps.add(toSend);
			}
		}

		// do a bunch of stuff to update the state of the game
		stepCreeps();
		stepBullets();
		doTowerAttacks();
	}
	
	public boolean isOver() {
		return player.getHealth() <= 0;
	}

	/**
	 * Sends the next wave of creeps
	 */
	public void sendNextWave(){
		lastWaveTime = elapsedTime;
		applyPlayerIncomePerWave();
		sendWave(WaveGenerator.generateWave(wavesSent + 1)); // wavesSent + 1 == wave number	
	}
	
	/**
	 * At the beginning of a new wave, increases the player's income per wave and gives the player gold equal to this income per wave
	 */
	protected void applyPlayerIncomePerWave() {
		if (wavesSent != 0){
			player.reapReward(player.getIncomePerWave());		
			player.increaseIncomePerWave(10 * wavesSent);
		}
	}

	
	/**
	 * Moves each of the creeps on the board along their specified CreepPath.  When creeps
	 * are destroyed, a reward is issued to the player.  When creeps reach the end of the
	 * path unchecked, the player's health is reduced.
	 */
	private void stepCreeps() {
		double speed = .05; // tiles per tick

		Iterator<Creep> it = creeps.iterator();

		while (it.hasNext()) {
			Creep c = it.next();
			
			// remove dead creeps
			if (c.getHealthFraction() <= 0) {
				synchronized (creeps) {
					it.remove();
				}
				
				player.reapReward(c.getReward());
				continue;
			}
			
			// remove creeps that reached the end of the path (applying damage)
			Point2D.Double direction = c.getNextDirection();
			if (direction == null) {
				player.decreaseHealth(c.getDamageToBase());
				
				synchronized (creeps) {
					it.remove();
				}
				
				continue;
			}

			double realSpeed = speed * c.getSpeed(); // modify the creep's speed by a percentage of the default speed
			c.setPosition(new Point2D.Double(
					direction.getX() * realSpeed + c.getPosition().getX(), 
					direction.getY() * realSpeed + c.getPosition().getY()));
			
			c.handleTimedDamage(elapsedTime);
		}
	}

	/**
	 * Moves each of the bullets toward their specified Creep. When the bullets get close enough
	 * to their targets (within a tolerance), they deal damage and are removed from the game.
	 */
	private void stepBullets() {
		double speed = .3; // tiles per tick
		
		Iterator<Bullet> it = bullets.iterator();
		while (it.hasNext()) {
			Bullet b = it.next();
			
			if (b.distanceToTarget() < 1 || b.targetIsNull()) {
				synchronized (bullets) {
					if (!b.targetIsNull())
						b.dealDamage();
					
					it.remove();
				}
				
				continue;
			}
			
			Point2D.Double dir = b.directionToTarget();
			b.setPosition(new Point2D.Double(b.getPosition().getX() + dir.getX() * speed,
						  					 b.getPosition().getY() + dir.getY() * speed));
		}
	}

	/**
	 * Sets the creeps targeted by each tower, and have each tower target the creep based on each tower's targeting strategy
	 */
	private void doTowerAttacks() {
		// find towers eligible to attack
		ArrayList<Tower> eligibleTowers = new ArrayList<Tower>();
		for (Tower t : towers) {
			if (t.canFire(elapsedTime)) {
				eligibleTowers.add(t);
			}
		}
		
		// find and attack the first creep the tower can attack
		for (Tower t : eligibleTowers) {
			tdComparator.setTower(t);
			
			// order the creeps correctly for the tower's targeting strategy
			switch (t.getTargeting().getStrategy()) {
				case CLOSEST:
					Collections.sort(creeps, tdComparator);
					break;
				case FURTHEST:
					Collections.sort(creeps, Collections.reverseOrder(tdComparator));
					break;
				case STRONGEST:
					Collections.sort(creeps, Collections.reverseOrder(hComparator));
					break;
				case WEAKEST:
					Collections.sort(creeps, hComparator);
					break;
			}
			
			for (Creep c : creeps) {
				if (c.getPosition().distance(t.getX(), t.getY()) < t.getRadius() && // creep in range
					(!c.isFlying() || t.getTargeting().isHitsFlying()) && // this tower must be flying if creep is
					c.towerCanApplyDamage(t)) {
					t.orientTowards(c);
					
					synchronized (bullets) {
						bullets.add(new Bullet(t, c, elapsedTime));
					}

					t.didFireAtTime(elapsedTime);
					break;
				}
			}
		}
	}
	
	/**
	 * Adds creeps to the creep queue.  As time goes on, creeps are released
	 * from the map queue one by one onto the map, at some fixed spacing interval.
	 * @param wave A collection of creeps to add to the creep queue.
	 */
	public void sendWave(Collection<Creep> wave) {
		wavesSent++;
		
		for (Creep c : wave) {
			creepQueue.add(c);
		}
	}
	
	/**
	 * @return The number of "ticks" until the next wave will be released.
	 */
	public int getTicksUntilNextWave() {
		return CoreConstants.waveTime - (elapsedTime - lastWaveTime);
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
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getLastWaveTime(){
		return lastWaveTime;
	}
	
	public void setLastWaveTime(int lastWaveTime) {
		this.lastWaveTime = lastWaveTime;
	}
	
	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	public int getWavesSent() {
		return wavesSent;
	}
	
	public LinkedList<Creep> getCreepQueue(){
		return creepQueue;
	}

	public ArrayList<Creep> getYourCreeps(){
		return yourCreeps;
	}
}
