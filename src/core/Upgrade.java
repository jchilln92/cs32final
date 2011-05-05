package src.core;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * The upgrade class holds all of the changes a tower upgrade can apply onto a
 * tower.
 */
public class Upgrade implements IPurchasable {
	/* All of these fields represent percentage increases that will be added on 
	 * to a current tower's values ex: an instantDamageChange value of 0.1 means 
	 * a 10% increase in the tower's instantDamage
	 */
	@Element(name="dInstantDamage")
	private double instantDamageChange; // modifies instantDamage
	
	@Element(name="dTimeDamage")
	private double timeDamageChange; // modifies time damage
	
	@Element(name="dPeriod")
	private double periodChange; // modifies period
	
	@Element(name="dDuration")
	private double effectDurationChange;// modifies effectDuration
	
	@Element(name="dSpeedEffect")
	private double speedEffectChange; // modifies speedChange

	@Element(name="dRadius")
	private double radiusChange;
	
	@Element(name="dFirePeriod")
	private double firePeriodChange;
	
	@Element
	private boolean canHitFlying;

	@Attribute(name="price")
	private double priceOfUpgrade;
	
	@Attribute
	private int level; //level 1, 2, or 3
	
	@Attribute
	private Tower.Type type; // the type of tower this upgrade applies to

	public double getInstantDamageChange() {
		return instantDamageChange;
	}

	public double getTimeDamageChange() {
		return timeDamageChange;
	}

	public double periodChange() {
		return periodChange;
	}

	public double effectDurationChange() {
		return effectDurationChange;
	}

	public double speedEffectChange() {
		return speedEffectChange;
	}

	public double getRadiusChange() {
		return radiusChange;
	}

	public double getFirePeriodChange() {
		return firePeriodChange;
	}

	public boolean getHitsFlyingChange() {
		return canHitFlying;
	}
	
	public int getLevel() { return level; }

	@Override
	public double getPrice() {
		return priceOfUpgrade;
	}

	@Override
	public void setPrice(double p) {
		priceOfUpgrade = p;
	}

	// Modifies Damage d's fields based on this upgrade's change fields
	public void updateDamage(Damage d) {
		double curInstantDamage = d.getInstantDamage();
		double curTimeDamage = d.getTimeDamage();
		double curPeriod = d.getPeriod();
		double curSpeedChange = d.getSpeedChange();
		double curEffectDuration = d.getEffectDuration();

		d.setInstantDamage(curInstantDamage
				+ (curInstantDamage * instantDamageChange));
		d.setTimeDamage(curTimeDamage + (curTimeDamage * timeDamageChange));
		d.setPeriod(curPeriod + (curPeriod * periodChange));
		d.setSpeedChange(curSpeedChange + (curSpeedChange * speedEffectChange));
		d.setEffectDuration(curEffectDuration
				+ (curEffectDuration * effectDurationChange));
	}

	public void updateTargeting(TargetingInfo t) {
		if (!t.isHitsFlying())	//a tower that hits flying should not ever lose this ability
			t.setHitsFlying(getHitsFlyingChange());
	}

	public void updateTower(Tower t) {
		double curFirePeriod = t.getFirePeriod();
		double curRadius = t.getRadius();
		double curInvestment = t.getInvestment();

		t.setFirePeriod(curFirePeriod + curFirePeriod * getFirePeriodChange());
		t.setRadius(curRadius + curRadius * getRadiusChange());
		t.setInvestment(curInvestment + getPrice());
	}
}
