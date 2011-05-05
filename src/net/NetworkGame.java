package src.net;

import java.util.ArrayList;
import java.util.Collection;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import src.WaveGenerator;
import src.core.Creep;
import src.core.Game;
import src.core.Tower;

/**
 * Acts just like a normal game, but adds network functionality
 * This is a pretty experimental class structure
 */
public class NetworkGame extends Game {
	private Connection remoteConnection; // the connection with our opponent
	private ArrayList<Creep> opponentCreeps; // the creeps on the opponent's map
	private ArrayList<Tower> opponentTowers; // the towers on the opponent's map
	private NetworkPlayer opponent; // the opponent
	
	
	
	private ArrayList<Creep> yourCreeps; //the creeps that you will be storing

	public NetworkGame(Connection opponentConnection) {
		opponent = new NetworkPlayer();
		opponentCreeps = new ArrayList<Creep>();
		opponentTowers = new ArrayList<Tower>();
		remoteConnection = opponentConnection;
		initializeGameListeners();
		yourCreeps = new ArrayList<Creep>();
		int random = (int)(Math.random()*10);
		for(int x = 0; x < random; x++){
			yourCreeps.add(Creep.createCreep(Creep.Type.GENERIC));
		
		}
	}

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

	@Override
	public void tick() {
		super.tick();
		provideInformation();
	}
	
	@Override
	public void sendNextWave(){
		setLastWaveTime(getElapsedTime());
		super.applyPlayerIncomePerWave();
		sendWaveToOpponent(yourCreeps);
		//sendWave(WaveGenerator.generateWave(wavesSent + 1)); // wavesSent + 1 == wave number		
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
	 * including the creeps and the towers.
	 */
	private void provideInformation() {
		GameNegotiationMessage creepMessage = new GameNegotiationMessage();
		GameNegotiationMessage towerMessage = new GameNegotiationMessage();
		GameNegotiationMessage healthMessage = new GameNegotiationMessage();
		
		creepMessage.type = GameNegotiationMessage.Type.CREEPS_UPDATE;
		creepMessage.data = getCreeps();
		
		towerMessage.type = GameNegotiationMessage.Type.TOWERS_UPDATE;
		towerMessage.data = getTowers();
		
		healthMessage.type = GameNegotiationMessage.Type.HEALTH_UPDATE;
		healthMessage.data = (Double) getPlayer().getHealth();

		synchronized (getCreeps()) {
			remoteConnection.sendTCP(creepMessage);
		}

		synchronized (getTowers()) {
			remoteConnection.sendTCP(towerMessage);
		}
		
		remoteConnection.sendTCP(healthMessage);
	}

	public ArrayList<Creep> getOpponentCreeps() {
		return opponentCreeps;
	}

	public ArrayList<Tower> getOpponentTowers() {
		return opponentTowers;
	}
	
	public NetworkPlayer getOpponent() {
		return opponent;
	}
}
