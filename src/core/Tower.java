package src.core;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import src.FilePaths;
import src.core.Creep.Type;
import src.core.xml.TowerXMLReader;
import src.ui.IDrawableTower;

import src.core.IAlignment;

/**
 * Represents a tower and all of its attributes, including attack radius, which
 * creeps to attack first, damage done, and how fast to fire.
 */
public class Tower implements IDrawableTower, IPurchasable, IAlignment {
	private static HashMap<Type, Tower> templateTowers = null;
	private static HashMap<Type, HashMap<IAlignment.Alignment, Image>> towerImages;
	
	/**
	 * Returns a preloaded image for a creep of this type and alignment
	 */
	public static Image getImage(Type type, IAlignment.Alignment alignment) {
		if (towerImages == null) loadImages();
		
		return towerImages.get(type).get(alignment);
	}
	
	/**
	 * Loads all of the creep images and stores them in a useful data structure.  This code is kind of tedious and
	 * annoying.
	 */
	private static void loadImages() {
		towerImages = new HashMap<Type, HashMap<IAlignment.Alignment, Image>>();
		towerImages.put(Type.GUN, new HashMap<IAlignment.Alignment, Image>());
		towerImages.put(Type.ANTIAIR, new HashMap<IAlignment.Alignment, Image>());
		towerImages.put(Type.SLOWING, new HashMap<IAlignment.Alignment, Image>());
		towerImages.put(Type.MORTAR, new HashMap<IAlignment.Alignment, Image>());
		towerImages.put(Type.FRIEND, new HashMap<IAlignment.Alignment, Image>());
		towerImages.put(Type.FLAME, new HashMap<IAlignment.Alignment, Image>());
		towerImages.put(Type.HTA, new HashMap<IAlignment.Alignment, Image>());
		towerImages.put(Type.STASIS, new HashMap<IAlignment.Alignment, Image>());
		
		towerImages.get(Type.GUN).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.towersPath + "gunblue.png").getImage());
		towerImages.get(Type.GUN).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.towersPath + "gungreen.png").getImage());
		towerImages.get(Type.GUN).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.towersPath + "gunneutral.png").getImage());
		towerImages.get(Type.GUN).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.towersPath + "gunred.png").getImage());
		towerImages.get(Type.GUN).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.towersPath + "gunyellow.png").getImage());
		
		towerImages.get(Type.ANTIAIR).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.towersPath + "antiairblue.png").getImage());
		towerImages.get(Type.ANTIAIR).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.towersPath + "antiairgreen.png").getImage());
		towerImages.get(Type.ANTIAIR).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.towersPath + "antiairneutral.png").getImage());
		towerImages.get(Type.ANTIAIR).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.towersPath + "antiairred.png").getImage());
		towerImages.get(Type.ANTIAIR).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.towersPath + "antiairyellow.png").getImage());
		
		towerImages.get(Type.SLOWING).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.towersPath + "slowingblue.png").getImage());
		towerImages.get(Type.SLOWING).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.towersPath + "slowinggreen.png").getImage());
		towerImages.get(Type.SLOWING).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.towersPath + "slowingneutral.png").getImage());
		towerImages.get(Type.SLOWING).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.towersPath + "slowingred.png").getImage());
		towerImages.get(Type.SLOWING).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.towersPath + "slowingyellow.png").getImage());
		
		towerImages.get(Type.MORTAR).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.towersPath + "mortarblue.png").getImage());
		towerImages.get(Type.MORTAR).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.towersPath + "mortargreen.png").getImage());
		towerImages.get(Type.MORTAR).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.towersPath + "mortarneutral.png").getImage());
		towerImages.get(Type.MORTAR).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.towersPath + "mortarred.png").getImage());
		towerImages.get(Type.MORTAR).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.towersPath + "mortaryellow.png").getImage());
		
		towerImages.get(Type.FRIEND).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.towersPath + "friendblue.png").getImage());
		towerImages.get(Type.FRIEND).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.towersPath + "friendgreen.png").getImage());
		towerImages.get(Type.FRIEND).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.towersPath + "friendneutral.png").getImage());
		towerImages.get(Type.FRIEND).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.towersPath + "friendred.png").getImage());
		towerImages.get(Type.FRIEND).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.towersPath + "friendyellow.png").getImage());
		
		towerImages.get(Type.FLAME).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.towersPath + "flameblue.png").getImage());
		towerImages.get(Type.FLAME).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.towersPath + "flamegreen.png").getImage());
		towerImages.get(Type.FLAME).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.towersPath + "flameneutral.png").getImage());
		towerImages.get(Type.FLAME).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.towersPath + "flamered.png").getImage());
		towerImages.get(Type.FLAME).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.towersPath + "flameyellow.png").getImage());
		
		towerImages.get(Type.HTA).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.towersPath + "htablue.png").getImage());
		towerImages.get(Type.HTA).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.towersPath + "htagreen.png").getImage());
		towerImages.get(Type.HTA).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.towersPath + "htaneutral.png").getImage());
		towerImages.get(Type.HTA).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.towersPath + "htared.png").getImage());
		towerImages.get(Type.HTA).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.towersPath + "htayellow.png").getImage());
		
		towerImages.get(Type.STASIS).put(IAlignment.Alignment.BLUE, new ImageIcon(FilePaths.towersPath + "stasisblue.png").getImage());
		towerImages.get(Type.STASIS).put(IAlignment.Alignment.GREEN, new ImageIcon(FilePaths.towersPath + "stasisgreen.png").getImage());
		towerImages.get(Type.STASIS).put(IAlignment.Alignment.NEUTRAL, new ImageIcon(FilePaths.towersPath + "stasisneutral.png").getImage());
		towerImages.get(Type.STASIS).put(IAlignment.Alignment.RED, new ImageIcon(FilePaths.towersPath + "stasisred.png").getImage());
		towerImages.get(Type.STASIS).put(IAlignment.Alignment.YELLOW, new ImageIcon(FilePaths.towersPath + "stasisyellow.png").getImage());
	}
	
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
	private double orientation;
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
		return orientation;
	}
	
	public void orientTowards(Creep c) {
		double length = c.getPosition().distance(new Point2D.Double(x + .5, y + .5));
		Point2D.Double direction = new Point2D.Double((c.getPosition().getX() - (x + .5)) / length, 
													  (c.getPosition().getY() - (y + .5)) / length);

		orientation = Math.PI - Math.atan2(direction.getX(), direction.getY());
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
