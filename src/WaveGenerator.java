package src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import src.core.Creep;

/**
 * A small helper class responsible for generating waves of creeps.  Currently, does not account
 * for things such as elementals.  This can be easily changed.  Parameters for this generator might
 * eventually be pulled out to an xml file.
 */
public class WaveGenerator {
	/**
	 * Generates a creep wave, the contents of which vary based on the current wave number.
	 * The higher the number, the more difficult the wave.
	 * @param n The current wave number
	 */
	public static Collection<Creep> generateWave(int n) {
		ArrayList<Creep> creeps = new ArrayList<Creep>();
		Random r = new Random();
		
		// generate 5 times the wave number of generic creeps
		for (int i = 0; i < n * 5; i++) {
			Creep c = Creep.createCreep(Creep.Type.GENERIC, n);
			creeps.add(c);
		}
		
		// make random numbers of other creeps, with limits on what wave they can start at
		// for example, flying, big, and assasin creeps don't start until wave 5.
		int numFast = r.nextInt(n * 2);
		int numBig = r.nextInt(n * 2);
		int numAssassin = r.nextInt(n);
		int numFlying = r.nextInt(n * 2);
		
		if (n > 3) {
			for (int i = 0; i < numFast; i++) {
				Creep c = Creep.createCreep(Creep.Type.FAST, n);
				creeps.add(c);
			}
		}
		
		if (n > 4) {
			for (int i = 0; i < numBig; i++) {
				Creep c = Creep.createCreep(Creep.Type.BIG_GUY, n);
				creeps.add(c);
			}
			
			for (int i = 0; i < numAssassin; i++) {
				Creep c = Creep.createCreep(Creep.Type.ASSASSIN, n);
				creeps.add(c);
			}
			
			
			for (int i = 0; i < numFlying; i++) {
				Creep c = Creep.createCreep(Creep.Type.FLYING, n);
				creeps.add(c);
			}
		}
		
		return creeps;
	}
}
