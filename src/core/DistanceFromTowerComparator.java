package src.core;

import java.util.Comparator;

/**
 * Allows for comparing two creeps based on distance from a given tower.
 */
public class DistanceFromTowerComparator implements Comparator<Creep> {
	private Tower t;
	
	public void setTower(Tower t) {
		this.t = t;
	}

	/**
	 * Compares which creep is closer to the given tower.
	 * @param c1 The first creep to compare
	 * @param c2 The second creep to compare
	 * @return A negative integer, zero, or a positive integer if Creep c1 is closer, equal, or father away from the tower than Creep c2
	 */
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
