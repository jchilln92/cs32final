package src.core;

import src.ui.IDrawableTower;

/**
 * Represents a tower and all of its attributes, including attack radius, which
 * creeps to attack first, damage done, and how fast to fire.
 */
public class Tower implements IDrawableTower, IPurchasable {
	private Damage damage;
	private double radius;
	private double fireRate;
	private double price;
	private TargetingInfo targeting;
	private int x, y;
	private double investment;

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

	public void setInvestment(double d) {
		investment = d;
	}

	public double getInvestment() {
		return investment;
	}

	// Applies an upgrade u onto this tower, modifying its Damage,
	// TargetingInfo, and self
	public void applyUpgrade(Upgrade u) {

		u.updateDamage(damage); // all damage modifications
		u.updateTargeting(targeting); // canHitFlying
		u.updateTower(this); // radius, rate of fire, investment

	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double p) {
		price = p;
	}
}
