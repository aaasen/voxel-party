package main;

import java.util.ArrayList;

public class Model {
	boolean locked;
	ArrayList<Block> blocks;
	
	public Model() {
		this.blocks = new ArrayList<Block>();
	}
	
	public void lock() {
		this.locked = true;
	}
	
	public void unlock() {
		this.locked = false;
	}
}
