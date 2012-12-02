package nexus.model.containers;

/**
 * A Container for Chunks
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import java.util.HashMap;

public class ChunkContainer {
	// this value should not need to be changed
	public static final int CHUNK_DIMENSION = 16;
	public static final long BIG_NUMBER = (long) Math.pow(2, 31);
	
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
		return getChunk(((int) x) / CHUNK_DIMENSION, ((int) y) / CHUNK_DIMENSION);
	}
	
	/**
	 * Returns the Chunk at the given Chunk coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Chunk getChunk(int x, int y) {
		Chunk chunk; 

		if((chunk = this.chunks.get(getKey(x, y))) == null) {
			chunk = new Chunk(x, y);
			this.chunks.put(getKey(x, y), chunk);
		}
		
		return chunk;
	}
	
	/**
	 * Generates a unique key for the given Chunk coordinate
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static long getKey(int x, int y) {
		return x * BIG_NUMBER + y;
	}
}
