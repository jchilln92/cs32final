package src.core;

import java.util.Comparator;

public class DistanceFromTowerComparator implements Comparator<Creep> {
	private Tower t;
	
	public void setTower(Tower t) {
		this.t = t;
	}
	
	public int compare(Creep c1, Creep c2) {
		double c1Dist = c1.getPosition().distance(t.getX(), t.getY());
		double c2Dist = c2.getPosition().distance(t.getX(), t.getY());
		
		if (c1Dist - c2Dist < 0) {
			return -1;
		} else if (c1Dist - c2Dist > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
