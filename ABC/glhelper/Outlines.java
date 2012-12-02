package glhelper;

/**
 * A collection of static helper methods for drawing Outlines
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import main.Vector3;

public class Outlines {
	
	/**
	 * Draws a line through all Points in the array
	 * 
	 * @param points an array of Points
	 */
	public static void outline(Vector3[] points) {
		for (int i = 0; i < points.length; i++) {
			drawLine(points[i], points[(i + 1) % points.length]);
		}
	}
	
	/**
	 * Draws a line through 4 points
	 * 
	 * TODO: find a better way to do this
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public static void outline(Vector3 a, Vector3 b, Vector3 c, Vector3 d) {
		Vector3[] points = {a, b, c, d};
		Outlines.outline(points);	
	}
	
	/**
	 * Outlines a quad
	 * 
	 * @param a
	 * @param b
	 */
	public static void outlineQuad2f(Vector3 a, Vector3 b) {
		glBegin(GL_LINES);
		
		glVertex3f(a.x, a.y, a.z);
		glVertex3f(a.x, b.y, b.z);
		
		glVertex3f(b.x, b.y, b.z);
		glVertex3f(a.x, b.y, b.z);
		
		glVertex3f(a.x, a.y, a.z);
		glVertex3f(b.x, a.y, a.z);
		
		glVertex3f(b.x, b.y, b.z);
		glVertex3f(b.x, a.y, a.z);
		
		glEnd();
	}
	
	/**
	 * Outlines a (mostly oblique) rectangular prism
	 * 
	 * @param a
	 * @param b
	 */
	public static void outlineRectPrism2f(Vector3 a, Vector3 b) {
		outlineQuad2f(a, new Vector3(b.x, b.y, a.z));
		outlineQuad2f(a, new Vector3(b.x, a.y, b.z));
		outlineQuad2f(b, new Vector3(a.x, a.y, b.z));
		outlineQuad2f(b, new Vector3(a.x, b.y, a.z));
	}
	
	
	/**
	 * Draws a line from Point a to b
	 * 
	 * @param a
	 * @param b
	 */
	public static void drawLine(Vector3 a, Vector3 b) {
		glBegin(GL_LINES);
		
		glVertex3f(a.x, a.y, a.z);
		glVertex3f(b.x, b.y, b.z);
		
		glEnd();
	}
}
