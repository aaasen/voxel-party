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
	 * Returns the Chunk that the given Vector is in
	 * 
	 * @param position
	 * @return
	 */
	public Chunk getChunk(Vector3 pos) {
		return getChunk(pos.x, pos.z);
	}
	
	public Chunk getChunk(float x, float z) {
		return getChunk((int) Math.floor(x / WIDTH), (int) Math.floor(z / WIDTH));
	}
	
	public Chunk getChunk(int x, int y) {
		return getChunk(x, y, true);
	}
	
	/**
	 * Returns the Chunk at the given Chunk coordinates
	 * 
	 * This will generate a new Chunk if there is none at the specified position
	 * 
	 * @param x
	 * @param z
	 * @param mask if the Chunk should be visually culled
	 * @return
	 */
	public Chunk getChunk(int x, int z, boolean mask) {
		Chunk chunk = this.chunks.get(getKey(x, z));
		
		if (chunk == null) {
			chunk = new Chunk(x, z, new Vector3(0.2f, 20.0f, 0.2f), this);
			chunk.generate();
			
			if (mask) {
				chunk.calcVisible();
			}
			
			this.chunks.put(getKey(x, z), chunk);
		} else if (mask) {
			if (!chunk.mask) {
				chunk.calcVisible();
			}
		}
		
		return chunk;
	}

	public Block getBlock(Vector3 pos) {
		return this.getChunk(pos).blocks[posMod((int) Math.floor(pos.x), WIDTH)][posMod((int) Math.floor(pos.z), WIDTH)][(int) Math.floor(pos.y)];
	}
	
	private void updateMask(Block block) {
		Block below = this.getBlock(block.a.add(new Vector3(0f, -1f, 0f)));
		Block above = this.getBlock(block.a.add(new Vector3(0f, 1f, 0f)));
		Block left = this.getBlock(block.a.add(new Vector3(-1f, 0f, 0f)));
		Block right = this.getBlock(block.a.add(new Vector3(1f, 0f, 0f)));
		Block near = this.getBlock(block.a.add(new Vector3(0f, 0f, -1f)));
		Block far = this.getBlock(block.a.add(new Vector3(0f, 0f, 1f)));
		
		System.out.println("------");
		System.out.println(block.a.toString());
		System.out.println(below.a.toString());
		System.out.println(above.a.toString());
		System.out.println(left.a.toString());
		System.out.println(right.a.toString());
		System.out.println(near.a.toString());
		System.out.println(far.a.toString());
		
		above.mask.bottom = true;
		above.mask.render = true;
		
		below.mask.top = true;
		below.mask.render = true;

		left.mask.right = true;
		left.mask.render = true;
		
		right.mask.left = true;
		right.mask.render = true;
		
		near.mask.far = true;
		near.mask.render = true;
		
		far.mask.near = true;
		far.mask.render = true;
	}
	
	public void setBlock(Block block) {
		this.getChunk(block.a.x, block.a.z).blocks[posMod((int) block.a.x, WIDTH)][posMod((int) block.a.z, WIDTH)][(int) block.a.y] = block;
	
		if(!block.visible()) {
			updateMask(block);
		}
	}
	
	/**
	 * Generates a unique key for the given Chunk coordinate
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private static long getKey(int x, int y) {
		return x * (long) Math.pow(2, 31) + y;
	}
	
	private static int posMod(int a, int b) {
		int r = a % b;
		return r < 0 ? r + b : r;
	}
}
