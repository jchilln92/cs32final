package src;

import java.util.Collection;

import src.core.Game;
import src.core.Tower;
import src.ui.IDrawableCreep;
import src.ui.IDrawableTower;

/**
 * Manages the interaction between the GUI and the backend.
 */
public class GameController {
	private Game game;
	private Tower placingTower; // a tower pending purchase
	private boolean isPaused;
	private boolean isDoubleTime;
	
	public GameController() {
		placingTower = null;
		isPaused = false;
		isDoubleTime = false;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game g) {
		game = g;
	}
	
	/*
	 * Time control methods
	 */
	public void tick() {
		if (!isPaused) {
			game.tick();
			
			if (isDoubleTime) {
				game.tick();
			}
		}
	}
	
	public void togglePause(boolean shouldPause) {
		isPaused = shouldPause;
	}
	
	public void toggleDoubleTime(boolean dt) {
		isDoubleTime = dt;
	}
	
	public Collection<? extends IDrawableCreep> getDrawableCreeps() {
		return game.getCreeps();
	}
	
	public Collection<? extends IDrawableTower> getDrawableTowers() {
		return game.getTowers();
	}
	
	public boolean tileIsOccupied(int x, int y) {
		for (Tower t : game.getTowers()) {
			if (t.getX() == x && t.getY() == y) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * Tower purchase handling methods
	 */
	public boolean isPlacingTower() {
		return placingTower != null;
	}

	public void setPlacingTower(Tower t) {
		placingTower = t;
	}
	
	public Tower getPlacingTower() {
		return placingTower;
	}
	
	public void beginPurchasingTower(Tower t) {
		setPlacingTower(t);
	}
	
	public void cancelTowerPurchase() {
		setPlacingTower(null);
	}
	
	public void finalizeTowerPurchase(int x, int y) {
		placingTower.setX(x);
		placingTower.setY(y);
		
		game.getPlayer().purchase(placingTower);
		game.getTowers().add(placingTower);
		placingTower = null;
	}
}
