package nexus.model.structs;

import nexus.model.generators.Perlin;
import nexus.model.renderable.Air;
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
	public static final int HEIGHT = 5;
	public static final int BIG_NUMBER = (int) Math.pow(2, 18);
	
	int x, z;
	Vector3 dilation;
	Block[][][] blocks;
	Colorist colorist;
	
	public Chunk(int x, int z, Vector3 dilation) {
		this.x = x;
		this.z = z;
		this.dilation = dilation;
		this.blocks = new Block[WIDTH][WIDTH][HEIGHT];
		this.colorist = new Greyscale(10.0f, 0.0f);
	}
	
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
		
		this.calcVisible(0, HEIGHT - 1, 0);
	}
	
	/**
	 * Performs a recursive flood fill on the chunk to determine visible polygons
	 * 
	 * Fills from the near top left corner
	 * 
	 * @param a
	 */
	public void calcVisible(int x, int y, int z) {
		if (x < WIDTH - 1&& x >= 0 && z < WIDTH - 1 && z >= 0 && y < HEIGHT && y >= 1) {
			if (blocks[x][z][y] instanceof Air) {

//				System.out.println("(" + x + ", " + y + ", " + z + ")");
				
				
				if (blocks[x][z][y - 1] instanceof Air) {
					calcVisible(x, y - 1, z);
				} else {
					blocks[x][z][y - 1].mask.render = true;
					blocks[x][z][y - 1].mask.top = true;
				}

				if (blocks[x + 1][z][y] instanceof Air) {
					calcVisible(x + 1, y, z);
				} else {
					blocks[x + 1][z][y].mask.render = true;
					blocks[x + 1][z][y].mask.left = true;
				}

				if (blocks[x][z + 1][y] instanceof Air) {
					calcVisible(x, y, z + 1);
				} else {
					blocks[x][z + 1][y].mask.render = true;
					blocks[x][z + 1][y].mask.near = true;
				}
			} else {
				throw new IllegalArgumentException("calcVisible() can only be called on Air blocks");
			}
		}
	}
	
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
