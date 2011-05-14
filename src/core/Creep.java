package src.core;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import src.FilePaths;
import src.core.xml.CreepXMLReader;
import src.ui.IDrawableCreep;


/**
 * Represents an enemy in the game.
 */
public class Creep implements IDrawableCreep, IAlignment, IPurchasable {
	/**
	 * Modifiers that affect the strength of a creep vs. towers of certain elemental colors.
	 * ex. If this creep is weak to the tower, the damage is multiplied by WEAKNESS_MODIFIER
	 */
	private static double WEAKNESS_MODIFIER = 1.25;
	private static double STRENGTH_MODIFIER = 0.75;
	private static double NEUTRAL_MODIFIER = 0.8;
	
	/**
	 * Templates for creeps, as well as images for creeps of every type and alignment
	 */
	private static HashMap<Creep.Type, Creep> templateCreeps;
	private static HashMap<Type, HashMap<IAlignment.Alignment, Image>> creepImages;
	
	/**
	 * Returns a preloaded image for a creep of this type and alignment
	 */
	public static Image getImage(Type type, IAlignment.Alignment alignment) {
		if (creepImages == null) loadImages();
		return creepImages.get(type).get(alignment);
	}
	
	/**
	 * Loads all of the creep images and stores them in a useful data structure so they can be accessed quickly.
	 * This code is kind of tedious and annoying.
	 */
	private static void loadImages() {
		creepImages = new HashMap<Type, HashMap<IAlignment.Alignment, Image>>();
		creepImages.put(Type.ASSASSIN, new HashMap<IAlignment.Alignment, Image>());
		creepImages.put(Type.BIG_GUY, new HashMap<IAlignment.Alignment, Image>());
		creepImages.put(Type.FAST, new HashMap<IAlignment.Alignment, Image>());
		creepImages.put(Type.FLYING, new HashMap<IAlignment.Alignment, Image>());
		creepImages.put(Type.GENERIC, new HashMap<IAlignment.Alignment, Image>());
		
		creepImages.get(Type.ASSASSIN).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.creepsPath + "AssassinBlue.png").getImage());
		creepImages.get(Type.ASSASSIN).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.creepsPath + "AssassinGreen.png").getImage());
		creepImages.get(Type.ASSASSIN).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.creepsPath + "AssassinNeutral.png").getImage());
		creepImages.get(Type.ASSASSIN).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.creepsPath + "AssassinRed.png").getImage());
		creepImages.get(Type.ASSASSIN).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.creepsPath + "AssassinYellow.png").getImage());
		
		creepImages.get(Type.BIG_GUY).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.creepsPath + "BigGuyBlue.png").getImage());
		creepImages.get(Type.BIG_GUY).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.creepsPath + "BigGuyGreen.png").getImage());
		creepImages.get(Type.BIG_GUY).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.creepsPath + "BigGuyNeutral.png").getImage());
		creepImages.get(Type.BIG_GUY).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.creepsPath + "BigGuyRed.png").getImage());
		creepImages.get(Type.BIG_GUY).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.creepsPath + "BigGuyYellow.png").getImage());

		creepImages.get(Type.FAST).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.creepsPath + "FastBlue.png").getImage());
		creepImages.get(Type.FAST).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.creepsPath + "FastGreen.png").getImage());
		creepImages.get(Type.FAST).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.creepsPath + "FastNeutral.png").getImage());
		creepImages.get(Type.FAST).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.creepsPath + "FastRed.png").getImage());
		creepImages.get(Type.FAST).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.creepsPath + "FastYellow.png").getImage());
		
		creepImages.get(Type.FLYING).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.creepsPath + "FlyingBlue.png").getImage());
		creepImages.get(Type.FLYING).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.creepsPath + "FlyingGreen.png").getImage());
		creepImages.get(Type.FLYING).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.creepsPath + "FlyingNeutral.png").getImage());
		creepImages.get(Type.FLYING).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.creepsPath + "FlyingRed.png").getImage());
		creepImages.get(Type.FLYING).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.creepsPath + "FlyingYellow.png").getImage());
		
		creepImages.get(Type.GENERIC).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.creepsPath + "GenericBlue.png").getImage());
		creepImages.get(Type.GENERIC).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.creepsPath + "GenericGreen.png").getImage());
		creepImages.get(Type.GENERIC).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.creepsPath + "GenericNeutral.png").getImage());
		creepImages.get(Type.GENERIC).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.creepsPath + "GenericRed.png").getImage());
		creepImages.get(Type.GENERIC).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.creepsPath + "GenericYellow.png").getImage());
	}
	
	/**
	 * Helper method to create a creep of a certain type, based on the "template" creep
	 * defined in XML.  If the XML hasn't been read yet, it is read at this point.
	 * @param t The type of Creep to return.
	 * @return A creep of the specified type.
	 */
	private static Creep createCreep(Type t) {
		if (templateCreeps == null) { 
			templateCreeps = CreepXMLReader.readXML(FilePaths.xmlPath + "creeps.xml");
		}
		
		Creep templateCreep = templateCreeps.get(t);
		Creep newCreep = new Creep();
		newCreep.setBaseHealth(templateCreep.getBaseHealth());
		newCreep.setHealth(templateCreep.getBaseHealth());
		newCreep.setFlying(templateCreep.isFlying());
		newCreep.setDamageToBase(templateCreep.getDamageToBase());
		newCreep.setPrice(templateCreep.getPrice());
		newCreep.setReward(templateCreep.getReward());
		newCreep.setSpeed(templateCreep.getSpeed());
		newCreep.setType(t);
		newCreep.setAlignment(Alignment.NEUTRAL);
		newCreep.setAdditionalGoldPerWave(templateCreep.getAdditionalGoldPerWave());
		
		return newCreep;
	}
	
	/**
	 * Creates a creep of the specified type, and scales some of its attributes based
	 * on the current wave number in a game.  This allows creeps to get stronger as time
	 * goes on, for instance.
	 * @param t The type of creep to create
	 * @param waveNumber The current wave number
	 * @return A creep, with scaled properties
	 */
	public static Creep createCreep(Type t, int waveNumber) {
		Creep originalCreep = createCreep(t);
		double scalingFactor = waveNumber * 0.05;
		
		originalCreep.setReward(originalCreep.getReward() + (int)(waveNumber/2));
		originalCreep.setPrice(originalCreep.getPrice() + (int)(waveNumber/5)*10);
		
		if (waveNumber < 5) {
			originalCreep.setBaseHealth(originalCreep.getBaseHealth() + scalingFactor*originalCreep.getBaseHealth());
			originalCreep.setHealth(originalCreep.getBaseHealth());
		} else if (waveNumber < 10){
			originalCreep.setBaseHealth(originalCreep.getBaseHealth() + 2*scalingFactor*originalCreep.getBaseHealth());
			originalCreep.setHealth(originalCreep.getBaseHealth());
		} else if (waveNumber < 20) {
			originalCreep.setBaseHealth(originalCreep.getBaseHealth() + 3*scalingFactor*originalCreep.getBaseHealth());
			originalCreep.setHealth(originalCreep.getBaseHealth());
		} else {
			originalCreep.setBaseHealth(originalCreep.getBaseHealth() + 4*scalingFactor*originalCreep.getBaseHealth());
			originalCreep.setHealth(originalCreep.getBaseHealth());
		}
		
		return originalCreep;
	}
	
	/**
	 * Creates a creep with the same properties as another creep.
	 * @param original The creep to copy
	 * @return A new creep with the same properties as the original
	 */	
	public static Creep copyCreep (Creep original) {
		Creep newCreep = new Creep();
		newCreep.setBaseHealth(original.getBaseHealth());
		newCreep.setHealth(original.getBaseHealth());
		newCreep.setFlying(original.isFlying());
		newCreep.setDamageToBase(original.getDamageToBase());
		newCreep.setPrice(original.getPrice());
		newCreep.setReward(original.getReward());
		newCreep.setSpeed(original.getSpeed());
		newCreep.setType(original.getType());
		newCreep.setAlignment(original.getAlignment());
		newCreep.setAdditionalGoldPerWave(original.getAdditionalGoldPerWave());
		
		return newCreep;
	}
	
	@Element
	private double baseHealth; // the health this creep started with
	
	private double health; // the creep's current health
	
	private boolean targetable; // whether or not towers are allowed to target this creep
	
	// counts how many damage applications have changed the targetable property
	// so that we know when to change the targetable value as timed damage wears off
	// to understand why this is needed, look at the stasis tower
	private int targetableCount;
	
	@Element
	private double reward; // the gold reward for killing this creep
	
	@Element
	private double speed; // the speed of this creep, as a percentage of normal speed
	
	@Attribute
	private Type type;
	
	@Element
	private boolean flying;
	
	@Element
	private double damageToBase;
	
	// holds time-based damage (such as slowing effects) that has been applied to this creep
	private HashMap<Tower, DamageApplication> damages;

	// the creep's path, and its position along the path
	private CreepPath path;
	private int pathIndex;

	private Point2D.Double position;

	@Element
	private double price;
	
	// the additional gold per wave the user receives for purchasing this creep (in multiplayer mode)
	@Element
	private double additionalGoldPerWave;

	private IAlignment.Alignment alignment;
	
	/**
	 * An enum describing the type of a creep.
	 */
	public enum Type {
		GENERIC,
		BIG_GUY,
		ASSASSIN,
		FLYING,
		FAST
	}

	public Creep() {
		damages = new HashMap<Tower, DamageApplication>();
		pathIndex = 0;
		targetable = true;
		targetableCount = 0;
		health = baseHealth;
	}
	
	/**
	 * Determines the direction this creep is moving in, by comparing its current position with
	 * the next point on its creep path.
	 * 
	 * @return A Point2D representing the unit direction vector.
	 */
	public Point2D.Double getNextDirection() {
		Point2D.Double target = path.getPoint(pathIndex);
		double length = position.distance(target);

		if (length < .1) { // have some tolerance for when the creep reaches its target
			setPosition(target);
			incrementPathTarget();
			target = path.getPoint(pathIndex);

			if (target == null) {
				return null;
			}

			length = position.distance(target);
		}

		Point2D.Double direction = new Point2D.Double(target.getX()
				- position.getX(), target.getY() - position.getY());

		return new Point2D.Double(direction.getX() / length, direction.getY()
				/ length);
	}

	/**
	 * Every tower is only allowed to apply time-based damage to each creep once at a time.
	 * 
	 * For example, a tower that slows down a creep by 10 percent for 10 seconds may not attach
	 * this damage modifier to the same creep over and over again.
	 * 
	 * @note This method returning true does not necessarily mean that the tower can attack.
	 * 		 There are other factors, such as whether or not the tower is in range, which are
	 * 		 checked elsewhere.
	 * 
	 * @param t A tower
	 * @return A boolean indicating whether or not the provided tower is allowed to damage to
	 * 		   this creep.
	 */
	public boolean towerCanApplyDamage(Tower t) {
		if (!damages.containsKey(t) && targetable) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Applies a certain a tower's damage to this creep, taking into account the elemental alignment
	 * of both the tower and the creep.
	 * 
	 * @param d The damage to be applied
	 * @param t The tower to apply the damage
	 * @param applicationTime The game time at which this damage is to be applied
	 */
	public void applyDamage(Damage d, Tower t, int applicationTime) {
		// factor in increases or decreases of damage due to alignment
		double damage = d.getInstantDamage();
		
		// adjust effect of damage based on alignment
		if (t.getAlignment() == IAlignment.Alignment.NEUTRAL) {
			damage *= NEUTRAL_MODIFIER;
		} else if (this.alignment != IAlignment.Alignment.NEUTRAL) {
			if (t.getAlignment() == this.alignment.getWeakTo()) //if this creep is weak to tower's element
				damage *= WEAKNESS_MODIFIER;
			else if (t.getAlignment() == this.alignment.getStrength()) // if this creep is strong to tower's element
				damage *= STRENGTH_MODIFIER;
		}
		
		health -= damage;
		
		// set this creep untargetable, and keep track of the fact that we did so
		if (!d.isTargetable()) {
			targetable = false;
			targetableCount++;
		}
		
		// if there will be any timed effects, hold on to them
		if (d.getEffectDuration() > 0 && !damages.containsKey(t)) {
			speed *= 1 + d.getSpeedChange();
			
			DamageApplication da = new DamageApplication(d, applicationTime);
			damages.put(t, da);
		}
	}
	
	/**
	 * Handles any time-based damage that has been applied to this creep.  If the damage has worn off,
	 * remove it from the list, restore the creep's original speed, etc.  Otherwise, apply the appropriate
	 * damages.
	 * 
	 * @param time The current game time.  This will be compared against the game time at which the damage
	 * 				was applied.
	 */
	public void handleTimedDamage(int time) {
		Iterator<Tower> it = damages.keySet().iterator();
		while (it.hasNext()) {
			Tower t = (Tower) it.next();
			
			if (t == null) { // just to be safe
				it.remove();
				continue;
			}
			
			DamageApplication da = damages.get(t);
			
			if (da.shouldUnattach(time)) { // throw away old damge
				it.remove();
				speed /= 1 + da.getDamage().getSpeedChange();
				
				if (!da.getDamage().isTargetable()) { // count how many times targetability has changed
					targetableCount--;
					if (targetableCount == 0) targetable = true;
				}
			} else if (da.shouldDoTimeDamage(time)) { // otherwise do damages
				health -= da.getDamage().getTimeDamage();
			}
		}
	}

	public void setPath(CreepPath path) {
		this.path = path;
	}

	public void incrementPathTarget() {
		pathIndex++;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}

	public Type getType() {
		return type;
	}

	public double getHealthFraction() {
		return health / baseHealth;
	}

	public Point2D.Double getPosition() {
		return position;
	}

	public void setPosition(Point2D.Double p) {
		position = p;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getReward() {
		return reward;
	}

	public double getBaseHealth() {
		return baseHealth;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void setBaseHealth(double baseHealth) {
		this.baseHealth = baseHealth;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public double getDamageToBase() {
		return damageToBase;
	}

	public void setDamageToBase(double damageToBase) {
		this.damageToBase = damageToBase;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public void setAdditionalGoldPerWave(double d) {
		 additionalGoldPerWave = d;
	}
	
	public double getAdditionalGoldPerWave() {
		return additionalGoldPerWave;
	}
}
