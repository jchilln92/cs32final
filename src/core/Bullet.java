package src.core;

import java.awt.geom.Point2D;

/**
 * Represents a bullet flying around on screen
 */
public class Bullet {
	private Tower firedBy;
	private int fireTime; // the elapsed game time when this bullet was fired
	private Point2D.Double position;
	private Creep target;
	
	public Bullet() {}
	
	public Bullet(Tower firedBy, Creep target, int fireTime) {
		this.firedBy = firedBy;
		this.fireTime = fireTime;
		this.position = new Point2D.Double(firedBy.getX() + .5, firedBy.getY() + .5);
		this.target = target;
	}
	
	public void setPosition(Point2D.Double p) {
		position = p;
	}
	
	public Point2D.Double getPosition() {
		return position;
	}
	
	public void setTarget(Creep t) {
		target = t;
	}
	
	public double distanceToTarget() {
		return position.distance(target.getPosition());
	}
	
	public Point2D.Double directionToTarget() {
		double length = position.distance(target.getPosition());
		Point2D.Double vector = new Point2D.Double(target.getPosition().getX() - position.getX(), 
												   target.getPosition().getY() - position.getY());

		return new Point2D.Double(vector.getX() / length, vector.getY() / length);
	}
	
	public boolean targetIsNull() {
		return target == null;
	}
	
	public void dealDamage() {
		target.applyDamage(firedBy.getDamage(), firedBy, fireTime);
	}
}
