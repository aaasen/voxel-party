package nexus.model.structs;

/**
 * A Container for Chunks
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import static nexus.model.structs.Chunk.HEIGHT;
import static nexus.model.structs.Chunk.WIDTH;

import java.util.HashMap;

public class ChunkContainer {
	public HashMap<Long, Chunk> chunks;
	public Vector3 selected;

	/**
	 * Creates an empty ChunkContainer
	 */
	public ChunkContainer() {
		this.chunks = new HashMap<Long, Chunk>();
		this.selected = new Vector3(0f, 0f, 0f);
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

	/**
	 * Returns the Chunk that the given Vector is in
	 * 
	 * @param position
	 * @return
	 */
	public Chunk getChunk(Vector3 pos) {
		return getChunk((int) Math.floor(pos.x / WIDTH), (int) Math.floor(pos.z / WIDTH));
	}

	public Chunk getChunk(int x, int y) {
		return getChunk(x, y, true);
	}

	/**
	 * @return the Block that the given Vector is in
	 */
	public Block getBlock(Vector3 pos) {
		if (inBounds(pos)) {
			return this.getChunk(pos).blocks[posMod((int) Math.floor(pos.x), WIDTH)][posMod((int) Math.floor(pos.z), WIDTH)][(int) Math.floor(pos.y)];
		} else {
			throw new IllegalArgumentException(pos + " is out of bounds");
		}
	}

	/**
	 * places a block into the ChunkContainer and updates nearby masks if necessary
	 * 
	 * @param block
	 */
	public void setBlock(Block block) {
		if (inBounds(block.a) && inBounds(block.b) && block.isOnGrid()) {
			this.getChunk(block.a).blocks[posMod((int) block.a.x, WIDTH)][posMod((int) block.a.z, WIDTH)][(int) block.a.y] = block;

			if (!block.visible()) {
				updateNearbyMask(block);
			} else {
				updateMask(block);
			}
		} else {
			throw new IllegalArgumentException(block.a.toString() + " is an illegal block position");
		}
	}

	/**
	 * Updates the rendering masks of nearby blocks
	 * 
	 * @param block
	 */
	private void updateNearbyMask(Block block) {
		Block below = this.getBlock(block.a.add(new Vector3(0f, -1f, 0f)));
		Block above = this.getBlock(block.a.add(new Vector3(0f, 1f, 0f)));
		Block left = this.getBlock(block.a.add(new Vector3(-1f, 0f, 0f)));
		Block right = this.getBlock(block.a.add(new Vector3(1f, 0f, 0f)));
		Block near = this.getBlock(block.a.add(new Vector3(0f, 0f, -1f)));
		Block far = this.getBlock(block.a.add(new Vector3(0f, 0f, 1f)));

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

	/**
	 * Updates the mask of the given block based on its neighbors
	 * 
	 * @param block
	 */
	private void updateMask(Block block) {
		Block below = this.getBlock(block.a.add(new Vector3(0f, -1f, 0f)));
		Block above = this.getBlock(block.a.add(new Vector3(0f, 1f, 0f)));
		Block left = this.getBlock(block.a.add(new Vector3(-1f, 0f, 0f)));
		Block right = this.getBlock(block.a.add(new Vector3(1f, 0f, 0f)));
		Block near = this.getBlock(block.a.add(new Vector3(0f, 0f, -1f)));
		Block far = this.getBlock(block.a.add(new Vector3(0f, 0f, 1f)));

		if (!below.visible()) {
			block.mask.render = true;
			block.mask.bottom = true;
		}

		if (!above.visible()) {
			block.mask.render = true;
			block.mask.top = true;
		}

		if (!left.visible()) {
			block.mask.render = true;
			block.mask.left = true;
		}

		if (!right.visible()) {
			block.mask.render = true;
			block.mask.right = true;
		}

		if (!near.visible()) {
			block.mask.render = true;
			block.mask.near = true;
		}

		if (!far.visible()) {
			block.mask.render = true;
			block.mask.far = true;
		}
	}

	/**
	 * Generates a unique key for the given Chunk coordinate
	 */
	public static long getKey(int x, int y) {
		return x * (long) Math.pow(2, 31) + y;
	}

	private static int posMod(int a, int b) {
		int r = a % b;
		return r < 0 ? r + b : r;
	}

	public boolean inBounds(Vector3 pos) {
		return (pos.y <= (HEIGHT - 1) && pos.y >= 0f);
	}
}
