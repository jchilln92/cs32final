package src.core;

/**
 * Represents a player within the game.
 */
public class Player {
	private double gold;
	private double health;
	private double incomePerWave;

	public Player() {
		// TODO: stub
		health = 100;
		gold = 5000;
		incomePerWave = 100;
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
	
	public void purchase(IPurchasable item) {
		gold -= item.getPrice();
	}
	
	public void reapReward(double reward) {
		gold += reward;
	}
	
	public void increaseIncomePerWave(double inc) {
		incomePerWave += inc;
	}
	
	public double getIncomePerWave() {
		return incomePerWave;
	}
}
