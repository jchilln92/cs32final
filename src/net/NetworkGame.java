package src.net;

import java.util.ArrayList;
import java.util.Collection;

import src.core.Bullet;
import src.core.Creep;
import src.core.Game;
import src.core.Tower;
import src.ui.controller.GameController;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Acts just like a normal game, but adds network functionality. Allows a player to be able to keep track of an opponents information, as well as
 * send information to an opponent.
 */
public class NetworkGame extends Game {
	private Connection remoteConnection; // the connection with our opponent
	private ArrayList<Bullet> opponentBullets; // the bullets flying around on the other side
	private ArrayList<Creep> opponentCreeps; // the creeps on the opponent's map
	private ArrayList<Tower> opponentTowers; // the towers on the opponent's map
	private NetworkPlayer opponent; // the opponent
	
	public NetworkGame(Connection opponentConnection) {
		opponent = new NetworkPlayer();
		opponentBullets = new ArrayList<Bullet>();
		opponentCreeps = new ArrayList<Creep>();
		opponentTowers = new ArrayList<Tower>();
		remoteConnection = opponentConnection;
		initializeGameListeners();
	}

	/**
	 *  Sets up listener's to handle receiving information from our opponent.
	 *  Basically just sets up remoteConnection so it can handle all of the possible GameNegotiationMessage's it will be sent 
	 *  throughout the course of a network game. More tangibly, sets up so we will be able to see what is happening on our opponenent's screen
	 *  as well as be able to see our opponents health.
	 */
	private void initializeGameListeners() {
		remoteConnection.addListener(new Listener() {
			public void received(Connection c, Object object) {
				if (object instanceof GameNegotiationMessage) {
					GameNegotiationMessage m = (GameNegotiationMessage)object;
					
					switch (m.type) {
						case CREEPS_UPDATE:
							synchronized (opponentCreeps) {
								opponentCreeps = (ArrayList<Creep>) m.data;
							}
							
							break;
						case TOWERS_UPDATE:
							synchronized (opponentTowers) {
								opponentTowers = (ArrayList<Tower>) m.data;
							}
							
							break;
						case BULLETS_UPDATE:
							synchronized (opponentBullets) {
								opponentBullets = (ArrayList<Bullet>) m.data;
							}
							
							break;
						case HEALTH_UPDATE:
							opponent.setHealth((Double) m.data);
							
							break;
						case WAVE:
							ArrayList<Creep> wave = (ArrayList<Creep>) m.data;
							sendWave(wave);
							break;
					}
				}
			}
		});
	}

	
	/**
	 * Tick's the game just how Game's tick works, but also sends over information to our opponent to be displayed in his/her screen
	 */
	@Override
	public void tick() {
		super.tick();
		provideInformation();
	}
	
	/**
	 * Checks if the game is over by looking at both our health and our opponents health
	 */
	@Override
	public boolean isOver() { 
		return getPlayer().getHealth() <= 0 || opponent.getHealth() <= 0;
	}
	
	/**
	 * Overrides Game's sendNextWave. Instead of using the waveGenerator to send waves, this method sends a wave to the opponent via sendWaveToOpponent.
	 */
	@Override
	public void sendNextWave(){
		setLastWaveTime(getElapsedTime());
		applyPlayerIncomePerWave();
		sendWaveToOpponent(getYourCreeps());
		getYourCreeps().clear();	
	}
	
	public void applyPlayerIncomePerWave() {
		if (wavesSent != 0){
			player.reapReward(player.getIncomePerWave());		
		}
	}

	/**
	 * Sends a given wave to an opponent to be added to that opponent's map
	 */
	public void sendWaveToOpponent(Collection<Creep> wave) {
		ArrayList<Creep> waveList = new ArrayList<Creep>(wave);
		GameNegotiationMessage message = new GameNegotiationMessage();
		message.type = GameNegotiationMessage.Type.WAVE;
		message.data = waveList;
		
		remoteConnection.sendTCP(message);
	}

	/**
	 * Sends the opponent data about the current state of our map,
	 * including creeps, towers, bullets, and our health.
	 */
	private void provideInformation() {
		GameNegotiationMessage creepMessage = new GameNegotiationMessage();
		GameNegotiationMessage towerMessage = new GameNegotiationMessage();
		GameNegotiationMessage bulletMessage = new GameNegotiationMessage();
		GameNegotiationMessage healthMessage = new GameNegotiationMessage();
		
		creepMessage.type = GameNegotiationMessage.Type.CREEPS_UPDATE;
		creepMessage.data = getCreeps();
		
		towerMessage.type = GameNegotiationMessage.Type.TOWERS_UPDATE;
		towerMessage.data = getTowers();
		
		bulletMessage.type = GameNegotiationMessage.Type.BULLETS_UPDATE;
		bulletMessage.data = getBullets();
		
		healthMessage.type = GameNegotiationMessage.Type.HEALTH_UPDATE;
		healthMessage.data = (Double) getPlayer().getHealth();

		synchronized (getCreeps()) {
			remoteConnection.sendTCP(creepMessage);
		}

		synchronized (getTowers()) {
			remoteConnection.sendTCP(towerMessage);
		}
		
		synchronized (getBullets()) {
			remoteConnection.sendTCP(bulletMessage);
		}
		
		remoteConnection.sendTCP(healthMessage);
	}

	public ArrayList<Creep> getOpponentCreeps() {
		return opponentCreeps;
	}

	public ArrayList<Tower> getOpponentTowers() {
		return opponentTowers;
	}
	
	public ArrayList<Bullet> getOpponentBullets() {
		return opponentBullets;
	}
	
	public NetworkPlayer getOpponent() {
		return opponent;
	}
}
