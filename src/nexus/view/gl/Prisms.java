package nexus.view.gl;

/**
 * A collection of static helper methods for drawing 3D shapes
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

import nexus.model.structs.Vector3;
import nexus.view.color.Colorist;

public class Prisms {
	
	/**
	 * Draws a rectangular prism given two Points
	 * 
	 * @param a near bottom left corner
	 * @param b far top right corner
	 */
	@Deprecated
	public static void drawRectPrism2f(Vector3 a, Vector3 b, Colorist colorist) {
		Planes.drawQuad2f(a.x, a.y, a.z, b.x, b.y, a.z, colorist);
		Planes.drawQuad2f(a.x, a.y, b.z, b.x, b.y, b.z, colorist);

		Planes.drawQuad2f(a.x, b.y, a.z, b.x, b.y, b.z, colorist);
		Planes.drawQuad2f(a.x, a.y, a.z, b.x, a.y, b.z, colorist);
		
		Planes.drawQuad2f(a.x, a.y, a.z, a.x, b.y, b.z, colorist);
		Planes.drawQuad2f(b.x, a.y, a.z, b.x, b.y, b.z, colorist);
	}
}
