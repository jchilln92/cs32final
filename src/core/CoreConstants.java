package src.core;

public class CoreConstants {
	
	/**
	 * Used by Creep.java:
	 * Modifiers that affect the strength of a creep vs. towers of certain elemental colors.
	 * ex. If this creep is weak to the tower, the damage is multiplied by WEAKNESS_MODIFIER
	 */
	public static double WEAKNESS_MODIFIER = 1.25;
	public static double STRENGTH_MODIFIER = 0.75;
	public static double NEUTRAL_MODIFIER = 0.8;
	
	/**
	 * Used by Game.java
	 */
	public static final int creepDelay = 25; // delay, in ticks, between creeps
	public static final int waveTime = 790; // number of ticks between each wave (about 25 seconds)
	
	/**
	 * Used by IAlignment.java
	 */
	public static double ALIGNMENT_COST = 5;

	/**
	 * Used by Player.java
	 */
	public static double STARTING_HEALTH = 100;
	public static double STARTING_GOLD = 500;
	public static double STARTING_INCOME = 200;
}
