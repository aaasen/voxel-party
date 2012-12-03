package nexus.model.structs;

/**
 * 16x16 containers for Terrain
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import nexus.model.renderable.Terrain;

public class Chunk {
	// this value should not need to be changed
	public static final int CHUNK_DIMENSION = 16;
	
	public Terrain terrain;
	
	int x, y;
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		this.terrain = new Terrain(x, y, 0.0f, CHUNK_DIMENSION, CHUNK_DIMENSION, 0.1f, 0.1f);
	}
}
