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
		this.terrain = new Terrain(x, y, ChunkContainer.CHUNK_DIMENSION, ChunkContainer.CHUNK_DIMENSION, new nexus.view.color.Greyscale(30.0f, -18.0f), 1.0f, 1.0f);
	}
}
