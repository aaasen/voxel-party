package nexus.main;

/**
 * A generic 3D point made of floats for OpenGL compatibility
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Vector3 {
	public float x, y, z;
	
	/**
	 * Constructs a Point
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3(float x, float y, float z) {
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
	public static Vector3 add(Vector3 a, Vector3 b) {
		return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public static Vector3 scale(Vector3 a, float scalar) {
		return new Vector3(a.x * scalar, a.y * scalar, a.z * scalar); 
	}
}
