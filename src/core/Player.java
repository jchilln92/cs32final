package src.core;

/**
 * Represents a player within the game. This class is used to keep track of a player's health, gold, and income gained per wave.
 * Contains methods for updating health/gold/income as necessary.
 */
public class Player {
	private double gold;
	private double health;
	private double incomePerWave;

	public Player() {
		health = CoreConstants.STARTING_HEALTH;
		gold = CoreConstants.STARTING_GOLD;
		incomePerWave = CoreConstants.STARTING_INCOME;
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
