package src.net;

import java.util.ArrayList;
import java.util.Collection;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

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
	//private NetworkPlayer opponent;

	public NetworkGame(Connection opponent) {
		opponentCreeps = new ArrayList<Creep>();
		opponentTowers = new ArrayList<Tower>();
		remoteConnection = opponent;
		initializeGameListeners();
	}
	
	private void initializeGameListeners() {
		Log.set(Log.LEVEL_TRACE);
		
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
		
		// TODO: determine whether these need to be synchronized
		System.out.println("good 1");
		creepMessage.type = GameNegotiationMessage.Type.CREEPS_UPDATE;
		creepMessage.data = getCreeps();
		
		System.out.println("good 2");
		towerMessage.type = GameNegotiationMessage.Type.TOWERS_UPDATE;
		towerMessage.data = getTowers();

		System.out.println("good 3");
		remoteConnection.sendTCP(creepMessage);
		
		System.out.println("good 4");
		remoteConnection.sendTCP(towerMessage);
		System.out.println("good 5");
	}
	
	public ArrayList<Creep> getOpponentCreeps() {
		return opponentCreeps;
	}
	
	public ArrayList<Tower> getOpponentTowers() {
		return opponentTowers;
	}
}
