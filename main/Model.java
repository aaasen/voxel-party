package main;

import java.util.ArrayList;

public class Model {
	ArrayList<Player> players;
	
	public Model() {
		this.players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
}
