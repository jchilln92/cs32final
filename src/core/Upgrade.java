package src.core;

/**
 * 
 * The upgrade class holds all of the changes a tower upgrade can apply onto a
 * tower.
 */
public class Upgrade implements IPurchasable {

	// All of these fields represent percentage increases that will be added on
	// to a current tower's values
	// ex: an instantDamageChange value of 0.1 means a 10% increase in the
	// towers instantDamage.
	private double instantDamageChange; // modifies instantDamage
	private double timeDamageChange; // modifies time damage
	private double periodChange; // modifies period
	private double effectDurationChange;// modifies effectDuration
	private double speedEffectChange; // modifies speedChange

	private double radiusChange;
	private double fireRateChange;
	private boolean canHitFlying;

	private double priceOfUpgrade;
	
	
	private int level; //level 1, 2, or 3
	private Tower.Type type; // the type of tower this upgrade applies to

	public Upgrade() {
		// Change later to use an XML reader to get these values
		instantDamageChange = 0;
		timeDamageChange = 0;
		periodChange = 0;
		effectDurationChange = 0;
		speedEffectChange = 0;

		radiusChange = 0;
		fireRateChange = 0;
		canHitFlying = false;

		priceOfUpgrade = 0;
		this.setPrice(0);
		
		level = 0;
		type = Tower.Type.GENERIC;
	}

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

	public double getFireRateChange() {
		return fireRateChange;
	}

	public boolean getHitsFlyingChange() {
		return canHitFlying;
	}

	@Override
	public double getPrice() {
		return priceOfUpgrade;
	}

	@Override
	public void setPrice(double p) {
		priceOfUpgrade = p;
	}

	// Modifies Damage d's fields based on this upgrades change fields
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
		t.setHitsFlying(getHitsFlyingChange());
	}

	public void updateTower(Tower t) {
		double curFireRate = t.getFireRate();
		double curRadius = t.getRadius();
		double curInvestment = t.getInvestment();

		t.setFireRate(curFireRate + curFireRate * getFireRateChange());
		t.setRadius(curRadius + curRadius * getRadiusChange());
		t.setInvestment(curInvestment + curInvestment * getRadiusChange());
	}
}
