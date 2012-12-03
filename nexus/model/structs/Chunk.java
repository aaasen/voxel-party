package nexus.model.structs;

import nexus.model.renderable.Block;

/**
 * 16x16 containers for Terrain
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Chunk {
	// this value should not need to be changed
	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;
	
	int x, y;
	
	Block[][][] blocks;
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		this.blocks = new Block[WIDTH][HEIGHT][WIDTH];
	}
}
