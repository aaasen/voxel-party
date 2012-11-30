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

public class Terrain implements Renderable {
	Point[][] matrix;
	
	public Terrain(int width, int depth) {
		matrix = new Point[width + 1][depth + 1];
		genTerrain();
	}
	
	public float hashNoise(int x) {
		x = (x<<13) ^ x;
	    return (float) ( 1.0 - ( (x * (x * x * 15731 + 789221) + 1376312589) & 0xfffffff) / 1073741824.0);    	
	}
	
	/**
	 * Populates the Terrain matrix
	 */
	public void genTerrain() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = new Point(i - matrix.length / 2, 0, j - matrix[i].length / 2);
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j].y += (float) hashNoise(512 * i + j) * 16;
			}
		}
		
//		for (int i = 0; i < matrix.length; i++) {
//			for (int j = 0; j < matrix[i].length; j++) {
//				matrix[i][j] = new Point(i - matrix.length / 2, (float) Math.random() * 1.0f, j - matrix[i].length / 2);
//			}
//		}
	}
	
	public void draw() {
		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = 0; j < matrix[i].length - 1; j++) {
				glColor3f(1.0f, 1.0f, 1.0f);
				Planes.drawQuad4f(matrix[i][j], matrix[i + 1][j], matrix[i + 1][j + 1], matrix[i][j + 1]);
			}
		}
	}
}
