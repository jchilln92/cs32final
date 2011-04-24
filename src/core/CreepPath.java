package src.core;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.simpleframework.xml.ElementList;

public class CreepPath {
	@ElementList(inline=true, entry="point", name="points")
	private ArrayList<Point2D.Double> points;

	// this no-arg constructor needs to be around so that xml
	// serialization will work.  note that it is private, and
	// not meant to be used other than by the xml framework,
	// which can override the private-ness using reflection
	@SuppressWarnings("unused")
	private CreepPath() {}
	
	public CreepPath(ArrayList<Point2D.Double> points) {
		this.points = points;
	}
	
	public Point2D.Double getPoint(int idx) {
		if (idx < points.size()) {
			return points.get(idx);
		}

		return null;
	}
}
