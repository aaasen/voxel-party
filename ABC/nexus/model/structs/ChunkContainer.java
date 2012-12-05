package nexus.model.structs;

/**
 * A Container for Chunks
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import static nexus.model.structs.Chunk.WIDTH;

import java.util.HashMap;

public class ChunkContainer {
	public HashMap<Long, Chunk> chunks;
	
	/**
	 * Creates an empty ChunkContainer
	 */
	public ChunkContainer() {
		this.chunks = new HashMap<Long, Chunk>();
	}
	
	/**
	 * Returns the Chunk that given point is in
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Chunk getChunk(float x, float y) {
		return getChunk(((int) x) / WIDTH, ((int) y) / WIDTH);
	}
	
	/**
	 * Returns the Chunk at the given Chunk coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Chunk getChunk(int x, int y, boolean mask) {
		Chunk chunk = this.chunks.get(getKey(x, y));
		
		if (chunk == null) {
			chunk = new Chunk(x, y, new Vector3(0.2f, 20.0f, 0.2f), this);
			chunk.generate();
			
			if (mask) {
				chunk.calcVisible();
			}
			
			this.chunks.put(getKey(x, y), chunk);
		} else if (mask) {
			if (!chunk.mask) {
				chunk.calcVisible();
			}
		}
		
		return chunk;
	}
	
	public Chunk getChunk(int x, int y) {
		return getChunk(x, y, true);
	}
	
	/**
	 * Generates a unique key for the given Chunk coordinate
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static long getKey(int x, int y) {
		return x * (long) Math.pow(2, 31) + y;
	}
}
