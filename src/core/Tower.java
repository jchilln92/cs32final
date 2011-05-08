package src.core;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import src.FilePaths;
import src.core.xml.TowerXMLReader;
import src.ui.IDrawableTower;

import src.core.IAlignment;

/**
 * Represents a tower and all of its attributes, including attack radius, which
 * creeps to attack first, damage done, and how fast to fire.
 */
public class Tower implements IDrawableTower, IPurchasable, IAlignment {
	private static HashMap<Type, Tower> templateTowers = null;
	
	@Attribute
	private Tower.Type type;
	
	@Element
	private Damage damage;
	
	@Element
	private double radius;
	
	@Element
	private double firePeriod;
	private int lastFireTime;
	
	@Element
	private double price;
	
	@Element
	private TargetingInfo targeting;
	
	@ElementList(required=false)
	private ArrayList<Upgrade> upgrades;
	
	@Element
	private String description;
	
	private IAlignment.Alignment alignment;
	private int upgradeLevel;

	private int x, y;
	private double investment;
	
	public static Tower createTower(Type t){
		if (templateTowers == null) {
			templateTowers = TowerXMLReader.readXML(FilePaths.xmlPath + "towers.xml");
		}
		
		Tower template = templateTowers.get(t);
		Tower tower = new Tower();
		
		tower.setDamage(template.getDamage());
		tower.setFirePeriod(template.getFirePeriod());
		tower.setPrice(template.getPrice());
		tower.setRadius(template.getRadius());
		tower.upgrades = template.getUpgrades();
		tower.setInvestment(tower.getPrice());
		tower.setTargetingInfo(template.getTargeting());
		tower.setAlignment(Alignment.NEUTRAL);
		tower.setType(t);
		
		return tower;
	}
	
	public Tower() {
		targeting = new TargetingInfo();
		upgradeLevel = 0;
		lastFireTime = 0;
	}
	
	public enum Type {
		GUN, ANTIAIR, SLOWING, MORTAR, FRIEND, FLAME, STASIS, HTA;
		
		private static Image gunImage;
		private static Image antiairImage;
		private static Image slowingImage;
		private static Image mortarImage;
		private static Image friendImage;
		private static Image flameImage;
		private static Image stasisImage;
		private static Image htaImage;
		
		public static void loadImages() {
			gunImage = (new ImageIcon(FilePaths.towersPath + "tower-icon1.png")).getImage();
			antiairImage = (new ImageIcon(FilePaths.towersPath + "tower-icon2.png")).getImage();
			slowingImage = (new ImageIcon(FilePaths.towersPath + "tower-icon3.png")).getImage();
			mortarImage = (new ImageIcon(FilePaths.towersPath + "tower-icon4.png")).getImage();
			friendImage = (new ImageIcon(FilePaths.towersPath + "tower-icon5.png")).getImage();
			flameImage = (new ImageIcon(FilePaths.towersPath + "tower-icon6.png")).getImage();
			stasisImage = (new ImageIcon(FilePaths.towersPath + "tower-icon7.png")).getImage();
			htaImage = (new ImageIcon(FilePaths.towersPath + "tower-icon8.png")).getImage();
		}
		
		public Image getImage() {
			if (gunImage == null) loadImages();
			
			switch (this) {
				case GUN:
					return gunImage;
				case ANTIAIR:
					return antiairImage;
				case SLOWING:
					return slowingImage;
				case MORTAR:
					return mortarImage;
				case FRIEND:
					return friendImage;
				case FLAME:
					return flameImage;
				case STASIS:
					return stasisImage;
				case HTA:
					return htaImage;
			}
			
			return null;
		}
		
		public Color getColor() {
		
			Color colour = Color.BLACK;
			
			switch(this) {
				case GUN:
					colour = Color.BLUE;
					break;
				case ANTIAIR:
					colour = Color.DARK_GRAY;
					break;
				case SLOWING:
					colour = Color.GREEN;
					break;
				case MORTAR:
					colour = Color.DARK_GRAY;
					break;
				case FRIEND:
					colour = new Color(171, 123, 255);
					break;
				case FLAME:
					colour = Color.RED;
					break;
				case STASIS:
					colour = Color.CYAN;
					break;
				case HTA:
					colour = Color.YELLOW;
					break;
				}
			return colour;
		}
	}

	public boolean canFire(int time) {
		if (time >= lastFireTime + firePeriod) {
			return true;
		}
		
		return false;
	}
	
	public void didFireAtTime(int time) {
		lastFireTime = time;
	}
	
	public double getFirePeriod() {
		return firePeriod;
	}

	public void setFirePeriod(double firePeriod) {
		this.firePeriod = firePeriod;
	}

	public TargetingInfo getTargeting() {
		return targeting;
	}
	
	public void setTargetingInfo(TargetingInfo tInfo) {
		//this.targeting = tInfo;
		this.targeting = TargetingInfo.copyTargetingInfo(tInfo);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Damage getDamage() {
		return damage;
	}

	public void setDamage(Damage damage) {
		this.damage = Damage.copyDamage(damage);
	}

	public double getOrientation() {
		// TODO: stub
		return 0;
	}

	public void setType(Tower.Type t){
		type = t;
	}
	
	public Type getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setInvestment(double d) {
		investment = d;
	}

	public double getInvestment() {
		return investment;
	}
	
	public void addInvestment(IPurchasable item) {
		investment += item.getPrice();
	}

	// Applies an upgrade u onto this tower, modifying its Damage,
	// TargetingInfo, and self
	public void applyUpgrade(Upgrade u) {
		assert u.getLevel() == upgradeLevel + 1 : "Attempted to apply inappropriate update!";
		
		u.updateDamage(damage); // all damage modifications
		u.updateTargeting(targeting); // canHitFlying
		u.updateTower(this); // radius, rate of fire, investment
		upgradeLevel++;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double p) {
		price = p;
	}
	
	public int getUpgradeLevel() {
		return upgradeLevel;
	}
	
	public ArrayList<Upgrade> getUpgrades() {
		return upgrades;
	}

	@Override
	public Alignment getAlignment() {
		return alignment;
	}

	@Override
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
}
