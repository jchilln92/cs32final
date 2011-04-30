package src;

import java.util.ArrayList;
import java.util.Collection;

import src.core.Creep;

public class WaveGenerator {
	/**
	 * Generates a creep wave, the contents of which vary based on the current wave number.
	 * The higher the number, the more difficult the wave.
	 * @param n The current wave number
	 */
	public static Collection<Creep> generateWave(int n) {
		ArrayList<Creep> creeps = new ArrayList<Creep>();
		
		for (int i = 0; i < n * 5; i++) {
			Creep c = Creep.createCreep(Creep.Type.GENERIC);
			creeps.add(c);
		}
		
		// make some flying creeps too
		for (int i = 0; i < n; i++) {
			Creep c = Creep.createCreep(Creep.Type.FLYING);
			creeps.add(c);
		}
		
		return creeps;
	}
}
