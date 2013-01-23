package nexus.model.structs;

import nexus.model.generators.Perlin;
import nexus.model.renderable.Air;
import nexus.model.renderable.BlockMask;
import nexus.model.renderable.Solid;
import nexus.view.color.Colorist;
import nexus.view.color.Greyscale;

/**
 * 16x16 containers for Terrain
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Chunk {
	// this value should not need to be changed
	public static final int WIDTH = 16;
	public static final int HEIGHT = 64;
	public static final int BIG_NUMBER = (int) Math.pow(2, 18);

	int x, z;
	Vector3 dilation;
	public Block[][][] blocks;
	boolean mask = false;
	Colorist colorist;
	ChunkContainer parent;

	/**
	 * Creates a new Chunk
	 * 
	 * @param x x position of the Chunk in its container
	 * @param z z position of the Chunk in its container
	 * @param dilation (x, y, z) dilation for terrain generatrion
	 * @param parent the Chunk's parent ChunkContainer
	 */
	public Chunk(int x, int z, Vector3 dilation, ChunkContainer parent) {
		this.x = x;
		this.z = z;
		this.dilation = dilation;
		this.blocks = new Block[WIDTH][WIDTH][HEIGHT];
		this.colorist = new Greyscale(16.0f, 0.0f);
		this.parent = parent;
	}

	/**
	 * Fills the Chunk with a heightmap of 2D Perlin noise
	 */
	public void generate() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < WIDTH; j++) {
				float x = (float) this.x * Chunk.WIDTH + i;
				float z = (float) this.z * Chunk.WIDTH + j;
				float y = (int) dilation.y * ((Perlin.perlin2D(x * dilation.x + BIG_NUMBER, z * dilation.z + BIG_NUMBER) + 1) / 2) + 1;

				for (int k = 0; k < HEIGHT; k++) {
					if (k <= y) {
						this.blocks[i][j][k] = new Solid(new Vector3(x, (float) k, z), 1.0f, this.colorist);
					} else {
						this.blocks[i][j][k] = new Air(new Vector3(x, (float) k, z), 1.0f);	
					}
				}
			}
		}
	}

	/**
	 * Calculates visible sides of blocks and updates each block's mask accordingly
	 */
	public void calcVisible() {
		this.mask = true;

		for (int x = 0; x < WIDTH; x++) {
			for (int z = 0; z < WIDTH; z++) {
				for (int y = 0; y < HEIGHT; y++) {
					if (blocks[x][z][y].visible()) {
						if (x == 0) {
							if (!parent.getChunk(this.x - 1, this.z, false).blocks[WIDTH - 1][z][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawLeft(true);
							}
							if (!blocks[x + 1][z][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawRight(true);
							}
						} else if (x == WIDTH - 1) {
							if (!parent.getChunk(this.x + 1, this.z, false).blocks[0][z][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawRight(true);	
							}
							if (!blocks[x - 1][z][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawLeft(true);
							}
						} else {
							if (!blocks[x + 1][z][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawRight(true);
							}

							if (!blocks[x - 1][z][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawLeft(true);
							}
						}


						if (z == 0) {
							if (!parent.getChunk(this.x, this.z - 1, false).blocks[x][WIDTH - 1][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawNear(true);
							}
							if (!blocks[x][z + 1][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawFar(true);
							}
						} else if (z == WIDTH - 1) {
							if (!parent.getChunk(this.x, this.z + 1, false).blocks[x][0][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawFar(true);	
							}
							if (!blocks[x][z - 1][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawNear(true);
							}
						} else {
							if (!blocks[x][z + 1][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawFar(true);
							}

							if (!blocks[x][z - 1][y].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawNear(true);
							}
						}

						if (y == HEIGHT - 1) {
							blocks[x][z][y].getMask().setRender(true);
							blocks[x][z][y].getMask().setDrawTop(true);
						} else if (y != 0) {
							if (!blocks[x][z][y + 1].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawTop(true);
							}

							if (!blocks[x][z][y - 1].visible()) {
								blocks[x][z][y].getMask().setRender(true);
								blocks[x][z][y].getMask().setDrawBottom(true);
							}	
						}
					}
				}
			}
		}
	}

	/**
	 * Draws each block in the Chunk
	 */
	public void drawBlocks() {
		for (Block[][] a : blocks) {
			for (Block[] b : a) {
				for (Block block : b) {
					block.draw();
				}
			}
		}
	}
}
