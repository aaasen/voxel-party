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
import main.Point;

public class Outlines {
	
	/**
	 * Draws a line through all Points in the array
	 * 
	 * @param points an array of Points
	 */
	public static void outline(Point[] points) {
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
	public static void outline(Point a, Point b, Point c, Point d) {
		Point[] points = {a, b, c, d};
		Outlines.outline(points);	
	}
	
	/**
	 * Outlines a quad
	 * 
	 * @param a
	 * @param b
	 */
	public static void outlineQuad2f(Point a, Point b) {
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
	public static void outlineRectPrism2f(Point a, Point b) {
		outlineQuad2f(a, new Point(b.x, b.y, a.z));
		outlineQuad2f(a, new Point(b.x, a.y, b.z));
		outlineQuad2f(b, new Point(a.x, a.y, b.z));
		outlineQuad2f(b, new Point(a.x, b.y, a.z));
	}
	
	
	/**
	 * Draws a line from Point a to b
	 * 
	 * @param a
	 * @param b
	 */
	public static void drawLine(Point a, Point b) {
		glBegin(GL_LINES);
		
		glVertex3f(a.x, a.y, a.z);
		glVertex3f(b.x, b.y, b.z);
		
		glEnd();
	}
}
