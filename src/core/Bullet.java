package src.core;

import java.awt.geom.Point2D;

/**
 * Represents a bullet flying around on screen.
 */
public class Bullet {
	private Tower firedBy; // the tower who fired this bullet
	private int fireTime; // the elapsed game time when this bullet was fired
	private Point2D.Double position; // the current position of this bullet in "tiles"
	private Creep target; // the creep at which this bullet is aimed
	
	// needed for kryo serialization - ignore this
	public Bullet() {}
	
	/**
	 * @param firedBy The tower firing the bullet
	 * @param target The bullet's target
	 * @param fireTime The game time at which this bullet is being created
	 */
	public Bullet(Tower firedBy, Creep target, int fireTime) {
		this.firedBy = firedBy;
		this.fireTime = fireTime;
		this.position = new Point2D.Double(firedBy.getX() + .5, firedBy.getY() + .5);
		this.target = target;
	}
	
	public double distanceToTarget() {
		return position.distance(target.getPosition());
	}
	
	/**
	 * @return A unit direction vector pointing from this bullet to its target.
	 */
	public Point2D.Double directionToTarget() {
		double length = position.distance(target.getPosition());
		Point2D.Double vector = new Point2D.Double(target.getPosition().getX() - position.getX(), 
												   target.getPosition().getY() - position.getY());

		return new Point2D.Double(vector.getX() / length, vector.getY() / length);
	}
	
	/**
	 * Attempts to apply this tower's damage to the target.
	 * @note This method does not check if the bullet is actually at the target, this check
	 * 		 must be done elsewhere.
	 */
	public void dealDamage() {
		target.applyDamage(firedBy.getDamage(), firedBy, fireTime);
	}
	
	public void setPosition(Point2D.Double p) {
		position = p;
	}
	
	public Point2D.Double getPosition() {
		return position;
	}

	public boolean targetIsNull() {
		return target == null;
	}
}
