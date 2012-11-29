package main;

import java.util.ArrayList;

public class Model {
	boolean locked;
	Player player;
	
	public Model() {
		this.lock();
		
		this.player = new Player(50, 50);
	
		this.unlock();
	}
	
	public void lock() {
		this.locked = true;
	}
	
	public void unlock() {
		this.locked = false;
	}
}
