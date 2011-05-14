package src.ui;

import src.core.Tower;
import src.core.IAlignment.Alignment;
/**
 * Interface that allows for tower drawing on game map
 */
public interface IDrawableTower {
	public int getX();

	public int getY();

	public double getOrientation();

	public Tower.Type getType();
	
	public double getRadius();
	
	public int getUpgradeLevel();
	
	public Alignment getAlignment();
}
