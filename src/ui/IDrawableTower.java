package src.ui;

import src.core.Tower;

public interface IDrawableTower {
	public int getX();

	public int getY();

	public double getOrientation();

	public Tower.Type getType();
}
