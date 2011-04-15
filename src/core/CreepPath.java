package src.core;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CreepPath {
	private ArrayList<Point2D.Double> points;

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
