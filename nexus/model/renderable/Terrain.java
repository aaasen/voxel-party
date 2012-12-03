package nexus.model.renderable;

/**
 * A polygonal Terrain generator
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import static nexus.model.structs.Chunk.CHUNK_DIMENSION;

import java.util.ArrayList;

import nexus.model.generators.Perlin;
import nexus.model.structs.Vector3;
import nexus.view.color.Greyscale;

public class Terrain implements Renderable {
	public static final int BIG_NUMBER = (int) Math.pow(2, 18);
	
	ArrayList<Block> blocks;
	float xDilation, zDilation;
	int x, z;
	int width, depth;
	float y;
	
	public Terrain(int x, int z, float y, int width, int depth, float xDilation, float zDilation) {
		this.x = x;
		this.z = z;
		this.y = y;
		this.width = width;
		this.depth = depth;
		this.blocks = new ArrayList<Block>();
		this.xDilation = xDilation;
		this.zDilation = zDilation;
		
		genTerrain();
	}
	
	/**this.zDilation
	 * Populates the Terrain matrix
	 */
	public void genTerrain() {
		for (int i = 0; i < width + 1; i++) {
			for (int j = 0; j < depth + 1; j++) {
				float x = (this.x * CHUNK_DIMENSION + i);
				float z = (this.z * CHUNK_DIMENSION + j);
				float y = ((1.0f + Perlin.perlin2D(x * this.xDilation + BIG_NUMBER, z * this.zDilation + BIG_NUMBER)) / 2.0f);
				
//				System.out.println(y);
				
				for (float k = 0.0f; k < y; k += 1.0f) {
//					System.out.println(k);
					this.blocks.add(new Block(new Vector3(x, k, z), new Vector3(x + 1, k + 1, z + 1), new Greyscale(20.0f, 0.0f)));
				}
			}
		}
	}
	
	public void draw() {
		for (Block block : this.blocks) {
			block.draw();
		}
	}
}
