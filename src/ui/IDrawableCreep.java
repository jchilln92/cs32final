package src.ui;

import java.awt.geom.Point2D;

import src.core.Creep;
import src.core.IAlignment.Alignment;

/**
 * Interface that allows for creep drawing on game map
 */
public interface IDrawableCreep {
	public Creep.Type getType();

	public double getHealthFraction(); 

	public Point2D.Double getPosition();
	
	public Alignment getAlignment();
}
