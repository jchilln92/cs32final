package src.core;

public class Player {
	private double gold;
	private double health;

	public Player() {
		// TODO: stub
		health = 100;
		gold = 1000;
	}

	public double getGold() {
		return gold;
	}

	public void setGold(double gold) {
		this.gold = gold;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void decreaseHealth(double amount) {
		this.health -= amount;
	}
}
