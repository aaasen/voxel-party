package glhelper;

/**
 * A collection of static helper methods for drawing 3D shapes
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import main.Point;

public class Prisms {
	
	/**
	 * Draws a rectangular prism given two Points
	 * 
	 * @param a near bottom left corner
	 * @param b far top right corner
	 */
	public static void drawRectPrism2f(Point a, Point b) {
		Planes.drawQuad2f(a.x, a.y, a.z, b.x, b.y, a.z);
		Planes.drawQuad2f(a.x, a.y, b.z, b.x, b.y, b.z);

		Planes.drawQuad2f(a.x, b.y, a.z, b.x, b.y, b.z);
		Planes.drawQuad2f(a.x, a.y, a.z, b.x, a.y, b.z);
		
		Planes.drawQuad2f(a.x, a.y, a.z, a.x, b.y, b.z);
		Planes.drawQuad2f(b.x, a.y, a.z, b.x, b.y, b.z);
	}
}
