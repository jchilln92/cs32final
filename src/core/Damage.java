package src.core;

import org.simpleframework.xml.Element;

/**
 * Represents the damage done by a tower.  Various damage attributes, such as duration 
 * and period for time-based damage, can be stored by this class, in addition to plain damage.
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
	
	@Element
	private boolean targetable;

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
	
	/**
	 * Creates a copy of a given damage.
	 * @param d The damage to copy
	 * @return The new damage
	 */
	public static Damage copyDamage(Damage d){
		Damage damage = new Damage();
		damage.setTargetable(d.isTargetable());
		damage.setInstantDamage(d.getInstantDamage());
		damage.setEffectDuration(d.getEffectDuration());
		damage.setPeriod(d.getPeriod());
		damage.setSpeedChange(d.getSpeedChange());
		damage.setTimeDamage(d.getTimeDamage());
		
		return damage;
	}

	public boolean isTargetable() {
		return targetable;
	}

	public void setTargetable(boolean targetable) {
		this.targetable = targetable;
	}
}
