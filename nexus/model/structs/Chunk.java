package nexus.model.structs;

/**
 * 16x16 containers for Terrain
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import nexus.model.renderable.Terrain;
import nexus.model.renderable.Water;
import nexus.view.color.Greyscale;
import static nexus.model.structs.ChunkContainer.CHUNK_DIMENSION;

public class Chunk {
	public Terrain terrain;
	public Water water;
	int x, y;
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		this.terrain = new Terrain(x, y, 0.0f, CHUNK_DIMENSION, CHUNK_DIMENSION,
				0.1f, 0.1f,
				new Biome(40.0f,
					new Greyscale(40.0f, -20.0f)));
		this.water = new Water(new Vector3(x * CHUNK_DIMENSION, 0.0f, y * CHUNK_DIMENSION),
				CHUNK_DIMENSION, CHUNK_DIMENSION, 
				new Color(0.55f, 0.804f, 0.97f));
	}
}