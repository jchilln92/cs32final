package src.core;

/**
 * Represents the damage done by a tower
 */
public class Damage {
	private double instantDamage; // the instant damage caused by this damage
	private double timeDamage; // the amount of damage caused over time
	private double period; // how frequently timeDamage should be applied
	private double effectDuration; // how long any time-based effects of this
	// damage should last
	private double speedChange; // the change in movement speed caused by this damage

	public double getInstantDamage() {
		return instantDamage;
	}

	public double getTimeDamage() {
		return timeDamage;
	}

	public void setTimeDamage(double timeDamage) {
		this.timeDamage = timeDamage;
	}

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

	public double getEffectDuration() {
		return effectDuration;
	}

	public void setEffectDuration(double effectDuration) {
		this.effectDuration = effectDuration;
	}

	public double getSpeedChange() {
		return speedChange;
	}

	public void setSpeedChange(double speedChange) {
		this.speedChange = speedChange;
	}

	public void setInstantDamage(double instantDamage) {
		this.instantDamage = instantDamage;
	}
}
