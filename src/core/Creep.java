package src.core;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;

import src.ui.IDrawableCreep;

public class Creep implements IDrawableCreep {
	private double baseHealth;
	private double health;
	private double reward;
	private double speed; // the speed of this creep, as a percentage of normal speed
	private Type type;
	private boolean flying;
	private double damageToBase;
	
	private HashMap<Tower, DamageApplication> damages;

	private CreepPath path;
	private int pathIndex;

	private Point2D.Double position;

	private double price;

	public double getPrice() {
		return price;
	}

	public enum Type {
		GENERIC
	}

	public Creep() {
		damages = new HashMap<Tower, DamageApplication>();
		pathIndex = 0;
		baseHealth = 100;
		reward = 100;
		health = 100;
		flying = false;
		speed = 1;
		type = Type.GENERIC;
		damageToBase = 10;
	}

	public double getReward() {
		return reward;
	}

	/**
	 * Determines the direction this creep is moving in.
	 * 
	 * @return A Point2D representing the unit direction vector.
	 */
	public Point2D.Double getNextDirection() {
		Point2D.Double target = path.getPoint(pathIndex);
		double length = position.distance(target);

		if (length < .1) {
			setPosition(target);
			incrementPathTarget();
			target = path.getPoint(pathIndex);

			if (target == null) {
				return null;
			}

			length = position.distance(target);
		}

		Point2D.Double direction = new Point2D.Double(target.getX()
				- position.getX(), target.getY() - position.getY());

		return new Point2D.Double(direction.getX() / length, direction.getY()
				/ length);
	}

	public double getDamageToBase() {
		return damageToBase;
	}

	public void applyDamage(Damage d, Tower t, int applicationTime) {
		health -= d.getInstantDamage();
		
		// if there will be any timed effects, hold on to them
		if (d.getEffectDuration() > 0 && !damages.containsKey(t)) {
			speed *= 1 + d.getSpeedChange();
			
			DamageApplication da = new DamageApplication(d, applicationTime);
			damages.put(t, da);
		}
	}
	
	public void handleTimedDamage(int time) {
		Iterator it = damages.keySet().iterator();
		while (it.hasNext()) {
			Tower t = (Tower) it.next();
			
			if (t == null) { // just to be safe
				it.remove();
				continue;
			}
			
			DamageApplication da = damages.get(t);
			
			if (da.shouldUnattach(time)) {
				it.remove();
				speed /= 1 + da.getDamage().getSpeedChange();
			} else if (da.shouldDoTimeDamage(time)) {
				health -= da.getDamage().getTimeDamage();
			}
		}
	}

	public CreepPath getPath() {
		return path;
	}

	public void setPath(CreepPath path) {
		this.path = path;
	}

	public int getPathTarget() {
		return pathIndex;
	}

	public void incrementPathTarget() {
		pathIndex++;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}

	public Type getType() {
		return type;
	}

	public double getHealthFraction() {
		return health / baseHealth;
	}

	public Point2D.Double getPosition() {
		return position;
	}

	public void setPosition(Point2D.Double p) {
		position = p;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
