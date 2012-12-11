package nexus.model.structs;

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
	public Vector3 add(Vector3 a) {
		return new Vector3(a.x + x, a.y + y, a.z + z);
	}
	
	public Vector3 subtract(Vector3 a) {
		return new Vector3(x - a.x, y - a.y, z - a.z);
	}
	
	public Vector3 scale(float scalar) {
		return new Vector3(x * scalar, y * scalar, z * scalar); 
	}
	
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
