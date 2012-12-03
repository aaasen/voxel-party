package nexus.model.renderable;

/**
 * A polygonal Terrain generator
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import java.util.ArrayList;

import nexus.model.generators.Perlin;
import nexus.model.structs.Biome;
import nexus.model.structs.ChunkContainer;
import nexus.model.structs.Vector3;

public class Terrain implements Renderable {
	public static final int BIG_NUMBER = (int) Math.pow(2, 18);
	
	ArrayList<Block> blocks;
	Biome biome;
	float xDilation, zDilation;
	int x, z;
	int width, depth;
	float y;
	
	public Terrain(int x, int z, float y, int width, int depth, float xDilation, float zDilation, Biome biome) {
		this.x = x;
		this.z = z;
		this.y = y;
		this.width = width;
		this.depth = depth;
		this.blocks = new ArrayList<Block>();
		this.xDilation = xDilation;
		this.zDilation = zDilation;
		this.biome = biome;
		
		genTerrain();
	}
	
	/**this.zDilation
	 * Populates the Terrain matrix
	 */
	public void genTerrain() {
		for (int i = 0; i < width + 1; i++) {
			for (int j = 0; j < depth + 1; j++) {
				float x = (this.x * ChunkContainer.CHUNK_DIMENSION + i);
				float z = (this.z * ChunkContainer.CHUNK_DIMENSION + j);
				float y = this.biome.yDilation * ((1.0f + Perlin.perlin2D(x * this.xDilation + BIG_NUMBER, z * this.zDilation + BIG_NUMBER)) / 2.0f);
				
//				System.out.println(y);
				
				for (float k = 0.0f; k < y; k += 1.0f) {
//					System.out.println(k);
					this.blocks.add(new Block(new Vector3(x, k, z), new Vector3(x + 1, k + 1, z + 1), this.biome.colorist));
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
