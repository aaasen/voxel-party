package nexus.model.structs;

/**
 * 16x16 containers for Terrain
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import nexus.model.renderable.Terrain;

public class Chunk {
	public Terrain terrain;
	int x, y;
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		this.terrain = new Terrain(x, y, 16.0f, ChunkContainer.CHUNK_DIMENSION, ChunkContainer.CHUNK_DIMENSION, new nexus.view.color.Greyscale(30.0f, -18.0f), 0.1f, 1.5f, 0.1f);
	}
}
