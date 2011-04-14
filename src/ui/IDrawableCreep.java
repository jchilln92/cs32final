package src.ui;

import java.awt.geom.Point2D;

import src.core.Creep;

public interface IDrawableCreep {
	public Creep.Type getType();
	public double getHealthFraction();
	public Point2D.Double getPosition();
}
