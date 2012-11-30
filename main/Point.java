package main;

/**
 * A generic 3D point made of floats for OpenGL compatibility
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Point {
	float x, y, z;
	
	/**
	 * Constructs a Point
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
