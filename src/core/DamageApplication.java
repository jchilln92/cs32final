package src.core;

/**
 * Represents the attachment of a timed-effect damage to a creep.
 */
public class DamageApplication {
	private Damage damage;
	private int timeApplied;
	private int lastApplication;

	// needed for kryo
	private DamageApplication() {};

	public DamageApplication(Damage d, int time) {
		this.damage = d;
		this.timeApplied = time;
		this.lastApplication = time;
	}

	public boolean shouldUnattach(int newTime) {
		return newTime >= timeApplied + damage.getEffectDuration();
	}

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
