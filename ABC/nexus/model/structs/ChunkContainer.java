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
	public Chunk getChunk(float x, float z) {
		return getChunk((int) Math.floor(x / WIDTH), (int) Math.floor(z / WIDTH));
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
	 * Java's % returns a negative number for negative input. This returns a positivie number no matter what
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int posMod(int a, int b) {
		int r = a % b;
		return r < 0 ? r + b : r;
	}
	
	public Block getBlock(Vector3 a) {
		return this.getChunk(a.x, a.z).blocks[posMod(Math.round(a.x), WIDTH)][posMod(Math.round(a.z), WIDTH)][(int) a.y];
	}
	
	public void setBlock(Block block) {
		this.getChunk(block.a.x, block.a.z).blocks[posMod((int) block.a.x, WIDTH)][posMod((int) block.a.z, WIDTH)][(int) block.a.y] = block;
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
