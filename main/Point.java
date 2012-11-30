package main;

/**
 * A generic 3D point made of floats for OpenGL compatibility
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Point {
	public float x, y, z;
	
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
	
	/**
	 * Adds two Points
	 * 
	 * @param a
	 * @param b
	 * @return sum of a and b
	 */
	public static Point add(Point a, Point b) {
		return new Point(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public static Point scale(Point a, float scalar) {
		return new Point(a.x * scalar, a.y * scalar, a.z * scalar); 
	}
}
