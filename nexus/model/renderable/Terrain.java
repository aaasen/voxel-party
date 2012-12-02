package nexus.model.renderable;

/**
 * A polygonal Terrain generator
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import static org.lwjgl.opengl.GL11.glColor3f;
import nexus.model.generators.Perlin;
import nexus.model.structs.Biome;
import nexus.model.structs.ChunkContainer;
import nexus.model.structs.Vector3;
import nexus.view.gl.Planes;

public class Terrain implements Renderable {
	public static final int BIG_NUMBER = (int) Math.pow(2, 18);
	
	Vector3[][] matrix;
	Biome biome;
	float xDilation, zDilation;
	int x, z;
	float y;
	
	public Terrain(int x, int z, float y, int width, int depth, float xDilation, float zDilation, Biome biome) {
		this.x = x;
		this.z = z;
		this.y = y;
		this.matrix = new Vector3[width + 1][depth + 1];
		this.xDilation = xDilation;
		this.zDilation = zDilation;
		this.biome = biome;
		
		genTerrain();
	}
	
	/**this.zDilation
	 * Populates the Terrain matrix
	 */
	public void genTerrain() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = new Vector3((this.x * ChunkContainer.CHUNK_DIMENSION + i), this.y, (this.z * ChunkContainer.CHUNK_DIMENSION + j));
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j].y += this.biome.yDilation * (Perlin.perlin2D(matrix[i][j].x * this.xDilation + BIG_NUMBER, matrix[i][j].z * this.zDilation + BIG_NUMBER));
			}
		}
	}
	
	public void draw() {
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = 0; j < matrix[i].length - 1; j++) {
				glColor3f(1.0f, 1.0f, 1.0f);
				Planes.drawQuad4f(matrix[i][j], matrix[i + 1][j], matrix[i + 1][j + 1], matrix[i][j + 1], this.biome.colorist);
			}
		}
	}
}
