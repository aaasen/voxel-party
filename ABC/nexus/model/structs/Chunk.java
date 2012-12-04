package nexus.model.structs;

import nexus.model.renderable.Block;
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
	public static final int HEIGHT = 1;
	
	int x, z;
	
	public Block[][][] blocks;
	
	public Chunk(int x, int z) {
		this.x = x;
		this.z = z;
		this.blocks = new Block[WIDTH][WIDTH][HEIGHT];
	}
	
	public void fillChunk() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < WIDTH; j++) {
				for (int k  = 0; k < HEIGHT; k++) {
					this.blocks[i][j][k] = new Block(new Vector3((float) x * Chunk.WIDTH + i, (float) k, (float) z * Chunk.WIDTH + j), 1.0f, new Greyscale(3.0f, 0.0f));
				}
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
