package src.core;

import java.util.Comparator;

/**
 * Allows for comparing two creeps based on their health.
 */
public class HealthComparator implements Comparator<Creep> {
	
	/**
	 * Compares which creep has more health
	 * @param c1 The first creep to compare
	 * @param c2 The second creep to compare
	 * @return A negative integer, zero, or a positive integer if Creep c1's health is less than, equal, or greater than Creep c2's health
	 */	
	public int compare(Creep c1, Creep c2) {
		if (c1.getHealth() - c2.getHealth() < 0) {
			return -1;
		} else if (c1.getHealth() - c2.getHealth() > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
