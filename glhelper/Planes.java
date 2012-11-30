package glhelper;

/**
 * A collection of static helper methods for drawing 2D surfaces
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 */

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import main.Point;

public class Planes {
	/**
	 * Draws a quad through Points a, b, c, and d
	 * 
	 * This is preferred over the deprecated GL_QUADS 
	 * 
	 * @param a bottom left corner
	 * @param b top left corner
	 * @param c top right corner
	 * @param d bottom right corner
	 */
	public static void drawQuad4f(Point a, Point b, Point c, Point d) {
		glBegin(GL_TRIANGLES);
		glVertex3f(a.x, a.y, a.z);
		glVertex3f(b.x, b.y, b.z);
		glVertex3f(c.x, c.y, c.z);
		
		glVertex3f(a.x, a.y, a.z);
		glVertex3f(d.x, d.y, d.z);
		glVertex3f(c.x, c.y, c.z);	
		glEnd();		
	}
	
	/**
	 * Draws a rectangle through Points a and b
	 * 
	 * @param a bottom left corner
	 * @param b top right corner
	 */
	public static void drawQuad2d(Point a, Point b) {
		glBegin(GL_TRIANGLES);
		glVertex3f(a.x, a.y, a.z);
		glVertex3f(a.x, b.y, a.z);
		glVertex3f(b.x, b.y, b.z);
		
		glVertex3f(a.x, a.y, a.z);
		glVertex3f(b.x, a.y, b.z);
		glVertex3f(b.x, b.y, b.z);	
		glEnd();		
	}
}
