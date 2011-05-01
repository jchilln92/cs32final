package src.core;

import java.util.Comparator;

public class HealthComparator implements Comparator<Creep> {
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
