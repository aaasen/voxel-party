package objects;

/**
 * A polygonal Terrain generator
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import static org.lwjgl.opengl.GL11.glColor3f;
import glhelper.Planes;
import glhelper.Outlines;
import main.Point;
import noise.*;

public class Terrain implements Renderable {
	Point[][] matrix;
	
	public Terrain(int width, int depth) {
		matrix = new Point[width + 1][depth + 1];
		genTerrain();
	}
	
	/**
	 * Populates the Terrain matrix
	 */
	public void genTerrain() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = new Point(i * 0.1f, 0.0f, j * 0.1f);
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j].y = Math.abs(Perlin.perlin2D(matrix[i][j].x, matrix[i][j].z));
//				System.out.println(matrix[i][j].y);
			}
		}
	}
	
	public void draw() {
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = 0; j < matrix[i].length - 1; j++) {
				glColor3f(1.0f, 1.0f, 1.0f);
				Planes.drawQuad4f(matrix[i][j], matrix[i + 1][j], matrix[i + 1][j + 1], matrix[i][j + 1]);
//				glColor3f(0.0f, 0.0f, 0.0f);
//				Outlines.outline(matrix[i][j], matrix[i + 1][j], matrix[i + 1][j + 1], matrix[i][j + 1]);
			}
		}
	}
}
