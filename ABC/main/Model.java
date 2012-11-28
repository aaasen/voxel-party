package main;

import java.util.ArrayList;

public class Model {
	boolean locked;
	ArrayList<Player> players;
	
	public Model() {
		this.players = new ArrayList<Player>();
	}
	
	public void lock() {
		this.locked = true;
	}
	
	public void unlock() {
		this.locked = false;
	}
	
	public void addPlayer(Player player) {
		this.lock();
		
		this.players.add(player);
		
		this.unlock();
	}
}
