package src.core;

import src.ui.IDrawableTower;

/**
 * Represents a tower and all of its attributes, including attack radius, which creeps to
 * attack first, damage done, and how fast to fire.
 */
public class Tower implements IDrawableTower {
	private Damage damage;
	private double radius;
	private double fireRate;
	private TargetingInfo targeting;
	private int x, y;
	private double price;
	
	public Tower() {
		targeting = new TargetingInfo();
	}

	public double getFireRate() {
		return fireRate;
	}

	public void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}

	public TargetingInfo getTargeting() {
		return targeting;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public enum Type {
		GENERIC
	}
	
	public Damage getDamage() {
		return damage;
	}

	public void setDamage(Damage damage) {
		this.damage = damage;
	}
	
	public double getOrientation() {
		// TODO: stub
		return 0;
	}
	
	public Type getType() {
		// TODO: stub
		return Type.GENERIC;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
