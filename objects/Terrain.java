package objects;

/**
 * A polygonal Terrain generator
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */
public class Terrain implements Renderable {
	float[][] terrainMatrix;
	
	public Terrain(int width, int depth) {
		this.terrainMatrix = new float[width][depth];
		this.genTerrain();
	}
	
	/**
	 * Populates the Terrain matrix
	 */
	public void genTerrain() {
		for (int i = 0; i < this.terrainMatrix.length; i++) {
			for (int j = 0; j < this.terrainMatrix[i].length; j++) {
				this.terrainMatrix[i][j] = i * j;
			}
		}
	}
	
	public void draw() {
		for (int i = 0; i < this.terrainMatrix.length; i++) {
			for (int j = 0; j < this.terrainMatrix[i].length; j++) {
				System.out.println(this.terrainMatrix[i][j]);
			}
		}
	}
}
