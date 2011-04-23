package src.core;

/**
 * An interface representing a purchasable object.  Towers, Upgrades, and Creeps
 * are all purchasable (Creeps are only purchasable in multiplayer mode).
 */
public interface IPurchasable {
	public void setPrice(double p);
	public double getPrice();
}
