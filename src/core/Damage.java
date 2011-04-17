package src.core;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Represents the damage done by a tower
 */
public class Damage {
	@Element
	private double instantDamage; // the instant damage caused by this damage
	
	@Element
	private double timeDamage; // the amount of damage caused over time
	
	@Element
	private double period; // how frequently timeDamage should be applied

	@Element
	private double effectDuration; // how long any time-based effects of this damage should last
	
	@Element
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
