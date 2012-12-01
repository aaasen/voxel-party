package world;

/**
 * 16x16 containers for Terrain
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import objects.Terrain;

public class Chunk {
	public Terrain terrain;
	int x, y;
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		this.terrain = new Terrain(x, y, ChunkContainer.CHUNK_DIMENSION, ChunkContainer.CHUNK_DIMENSION, new color.Greyscale(20.0f, 0.0f), 1.0f, 1.0f);
	}
}
