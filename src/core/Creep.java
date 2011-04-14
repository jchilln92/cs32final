package src.core;

import java.awt.geom.Point2D;
import src.ui.IDrawableCreep;

public class Creep implements IDrawableCreep {
	private int baseHealth;
	private int health;
	private Type type;
	private boolean flying;
	
	private CreepPath path;
	private int pathIndex;

	private Point2D.Double position;
	
	public enum Type {
		GENERIC
	}
	
	public Creep() {
		pathIndex = 0;
		baseHealth = 100;
		health = 100;
		flying = false;
		type = Type.GENERIC;
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
			length = position.distance(target);
		}
		
		Point2D.Double direction = new Point2D.Double(target.getX() - position.getX(),
				  									  target.getY() - position.getY());
		
		return new Point2D.Double(direction.getX() / length, direction.getY() / length);
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
		return ((double) health) / baseHealth;
	}
	
	public Point2D.Double getPosition() {
		return position;
	}
	
	public void setPosition(Point2D.Double p) {
		position = p;
	}
}
