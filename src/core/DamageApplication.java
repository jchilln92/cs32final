package src.core;

/**
 * Represents the attachment of a timed-effect damage to a creep.
 */
public class DamageApplication {
	private Damage damage;
	private int timeApplied; // the time this damage was attached to the creep
	private int lastApplication; // the last time timed damage was dealt

	// see CreepPath for an explanation
	@SuppressWarnings("unused")
	private DamageApplication() {};

	public DamageApplication(Damage d, int time) {
		this.damage = d;
		this.timeApplied = time;
		this.lastApplication = time;
	}

	/**
	 * Whether this damage has worn off at this time.
	 */
	public boolean shouldUnattach(int newTime) {
		return newTime >= timeApplied + damage.getEffectDuration();
	}

	/**
	 * Decides whether time based damage should be dealt at a certain game time (in ticks)
	 */
	public boolean shouldDoTimeDamage(int newTime) {
		if (newTime >= lastApplication + damage.getPeriod()) {
			lastApplication = newTime;
			return true;
		}
		
		return false;
	}

	public Damage getDamage() {
		return damage;
	}
}
