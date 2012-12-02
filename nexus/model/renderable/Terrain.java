package nexus.model.renderable;

/**
 * A polygonal Terrain generator
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import static org.lwjgl.opengl.GL11.glColor3f;
import nexus.model.generators.*;
import nexus.model.structs.ChunkContainer;
import nexus.model.structs.Vector3;
import nexus.view.color.*;
import nexus.view.gl.Planes;

public class Terrain implements Renderable {
	public static final int BIG_NUMBER = (int) Math.pow(2, 22);
	
	Vector3[][] matrix;
	Colorist colorist;
	float xDilation, yDilation;
	int x, y;
	
	public Terrain(int x, int y, int width, int depth, Colorist colorist, float xDilation, float yDilation) {
		this.x = x;
		this.y = y;
		this.matrix = new Vector3[width + 1][depth + 1];
		this.colorist = colorist;
		this.xDilation = xDilation;
		this.yDilation = yDilation;
		genTerrain();
	}
	
	/**
	 * Populates the Terrain matrix
	 */
	public void genTerrain() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = new Vector3((this.x * ChunkContainer.CHUNK_DIMENSION + i) * this.xDilation, 0.0f, (this.y * ChunkContainer.CHUNK_DIMENSION + j) * this.yDilation);
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j].y = Math.abs(Perlin.perlin2D((this.x * ChunkContainer.CHUNK_DIMENSION + i + BIG_NUMBER) * 0.1f, (this.y * ChunkContainer.CHUNK_DIMENSION + j + BIG_NUMBER)  * 0.1f));
			}
		}
	}
	
	public void draw() {
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = 0; j < matrix[i].length - 1; j++) {
				glColor3f(1.0f, 1.0f, 1.0f);
				Planes.drawQuad4f(matrix[i][j], matrix[i + 1][j], matrix[i + 1][j + 1], matrix[i][j + 1], this.colorist);
			}
		}
	}
}
