package nexus.view.gl;

/**
 * A collection of static helper methods for drawing 2D shapes
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import nexus.model.structs.Vector3;

public class Planes {
	/**
	 * Form a quad between two vectors. Only tested for orthagonal.
	 * 
	 * @param a lower left corner
	 * @param b upper right corner
	 * @return float[] that, when parsed as triangles, will for a quad
	 */
	public static float[] makeQuad2f(Vector3 a, Vector3 b) {
		if (a.x <= b.x && a.y <= b.y && a.z <= b.z) {
			if (a.x == b.x) {
				
			} else if (a.y == b.y) {
				
			} else if (a.z == b.z) {
				
			} else {
				throw new IllegalArgumentException("no quad can be constructed between these vertices: " + a.toString() + ", " + b.toString());
			}
			
			return Vector3.combine(new Vector3[] { a, b, new Vector3(a.x, b.y, a.z), b, a, new Vector3(b.x, a.y, a.z)});
		} else {
			throw new IllegalArgumentException("all values of a must be smaller than those of b");
		}
	}
}
