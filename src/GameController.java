package src;

import java.util.Collection;

import src.core.Game;
import src.core.Tower;
import src.core.Upgrade;
import src.ui.IDrawableCreep;
import src.ui.IDrawableTower;
import src.ui.side.Sidebar;

/**
 * Manages the interaction between the GUI and the backend.
 */
public class GameController {
	private Game game;
	private Tower placingTower; // a tower pending purchase
	private Tower selectedTower; // a tower that is selected
	private Sidebar side;
	private boolean isPaused;
	private boolean isDoubleTime;
	
	public GameController() {
		placingTower = null;
		isPaused = false;
		isDoubleTime = false;
	}
	
	public Sidebar getSide() {
		return side;
	}

	public void setSide(Sidebar side) {
		this.side = side;
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
		Tower t = getTowerAtTile(x, y);
		return t == null ? false : true;
	}
	
	private Tower getTowerAtTile(int x, int y) {
		for (Tower t : game.getTowers()) {
			if (t.getX() == x && t.getY() == y) {
				return t;
			}
		}
		
		return null;
	}
	
	/*
	 * Tower upgrade handling methods
	 */
	public boolean isTowerSelected() {
		return selectedTower != null;
	}
	
	public Tower getSelectedTower() {
		return selectedTower;
	}

	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}
	
	/**
	 * Applies the appropriate upgrade to the currently selected tower.
	 * @param level The upgrade level of the upgrade to be applied
	 * @param idx Which upgrade in the given level to apply
	 */
	public void applyTowerUpgrade(int level, int idx) {
		int seenAtLevel = 0;
		
		for (Upgrade u : selectedTower.getUpgrades()) {
			if (u.getLevel() == level) seenAtLevel++;
			
			if (seenAtLevel - 1 == idx) {
				selectedTower.applyUpgrade(u);
				game.getPlayer().purchase(u);
				return;
			}
		}
	}
	
	public void toggleTowerSelection(int x, int y) {
		Tower t = getTowerAtTile(x, y);
		
		if (t == null || selectedTower == getTowerAtTile(x, y)) {
			unselectTower();
		} else {
			selectedTower = t;
			side.showTowerUpgrade();
		}
	}
	
	/**
	 * Unselects the current tower, if any.
	 */
	public void unselectTower() {
		selectedTower = null;
		side.showTowerPurchase();
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
		side.showTowerPurchaseCancel();
	}
	
	public void cancelTowerPurchase() {
		setPlacingTower(null);
		side.showTowerPurchase();
	}
	
	public void finalizeTowerPurchase(int x, int y) {
		placingTower.setX(x);
		placingTower.setY(y);
		
		game.getPlayer().purchase(placingTower);
		game.getTowers().add(placingTower);
		placingTower = null;
		
		side.showTowerPurchase();
	}
}
