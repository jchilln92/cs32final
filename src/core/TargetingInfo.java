package src.core;

import org.simpleframework.xml.Element;

/**
 * Encapsulates information about how a tower will target creeps.
 */
public class TargetingInfo {
	@Element
	private Strategy strategy;
	
	@Element
	private boolean hitsFlying;


	/**
	 * Enum to represent tower's attack strategy. Closest attacks nearest enemy to tower and strongest attacks creep with
	 * the most current health. Furthest and weakest are just the opposite of closest and strongest respectively.
	 */
	public enum Strategy {
		CLOSEST, FURTHEST, STRONGEST, WEAKEST
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public boolean isHitsFlying() {
		return hitsFlying;
	}

	public void setHitsFlying(boolean hitsFlying) {
		this.hitsFlying = hitsFlying;
	}
	
	/**
	 * Creates a copy of a given TargetingInfo.
	 * @param oldInfo The TargetingInfo to copy
	 * @return The new TargetingInfo
	 */
	public static TargetingInfo copyTargetingInfo(TargetingInfo oldInfo) {
		TargetingInfo targeting = new TargetingInfo();
		targeting.setHitsFlying(oldInfo.isHitsFlying());
		targeting.setStrategy(oldInfo.getStrategy());
		
		return targeting;
	}
}
