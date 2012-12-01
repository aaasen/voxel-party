package objects;

/**
 * A polygonal Terrain generator
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import static org.lwjgl.opengl.GL11.glColor3f;
import glhelper.Planes;
import main.Point;
import noise.*;
import color.*;

public class Terrain implements Renderable {
	Point[][] matrix;
	Colorist colorist;
	float xDilation, yDilation;
	
	public Terrain(int width, int depth, Colorist colorist, float xDilation, float yDilation) {
		this.matrix = new Point[width + 1][depth + 1];
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
				matrix[i][j] = new Point(i * this.xDilation, 0.0f, j * this.yDilation);
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
//				matrix[i][j].y = Math.abs(Perlin.perlin2D(matrix[i][j].x, matrix[i][j].z));
				matrix[i][j].y = Math.abs(Perlin.perlin2D(i * 0.1f, j * 0.1f));
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
