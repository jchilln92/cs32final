package src.net;

import java.util.Collection;

import src.core.Bullet;
import src.ui.IDrawableCreep;
import src.ui.IDrawableTower;
import src.ui.controller.GameController;

/**
 * Manages the representation of a remote game.  NetworkGameController
 * is used to populate the MapComponent that represents the oppponent's
 * map.
 */
public class NetworkGameController extends GameController {
	private NetworkGame game;
	
	public NetworkGameController(NetworkGame g) {
		game = g;
	}
	
	public Collection<? extends IDrawableCreep> getDrawableCreeps() {
		return game.getOpponentCreeps();
	}
	
	public Collection<? extends IDrawableTower> getDrawableTowers() {
		return game.getOpponentTowers();
	}
	
	public Collection<Bullet> getBullets() {
		return game.getOpponentBullets();
	}
}
