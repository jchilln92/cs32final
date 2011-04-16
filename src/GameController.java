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
	
	public void setGame(Game g) {
		game = g;
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
	
	public void towerWasPlaced(Tower t, int x, int y) {
		t.setX(x);
		t.setY(y);
		
		game.getTowers().add(t);
	}
}
