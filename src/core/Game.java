package src.core;

import java.util.ArrayList;

public class Game {
	private Map map;
	private ArrayList<Creep> creeps;
	private ArrayList<Tower> towers;
	private Player player;
	private int elapsedTime;
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public ArrayList<Creep> getCreeps() {
		return creeps;
	}
	
	public void setCreeps(ArrayList<Creep> creeps) {
		this.creeps = creeps;
	}
	
	public ArrayList<Tower> getTowers() {
		return towers;
	}
	
	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getElapsedTime() {
		return elapsedTime;
	}
	
	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}
