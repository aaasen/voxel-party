package nexus.view.gl;

/**
 * A collection of static helper methods for drawing 2D shapes
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import nexus.model.structs.Vector3;
import nexus.view.color.Colorist;

public class Planes {
	/**
	 * Draws a quad through Points a, b, c, and d and colors each vertex based on height
	 * 
	 * This is preferred over the deprecated GL_QUADS 
	 * 
	 * @param a bottom left corner
	 * @param b top left corner
	 * @param c top right corner
	 * @param d bottom right corner
	 */
	public static void drawQuad4f(Vector3 a, Vector3 b, Vector3 c, Vector3 d) {
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
	 * Draws a quad through Points a, b, c, and d and colors each vertex based on height
	 * 
	 * This is preferred over the deprecated GL_QUADS 
	 * 
	 * @param a bottom left corner
	 * @param b top left corner
	 * @param c top right corner
	 * @param d bottom right corner
	 */
	public static void drawQuad4f(Vector3 a, Vector3 b, Vector3 c, Vector3 d, Colorist colorist) {
		glBegin(GL_TRIANGLES);
		
		colorist.color(a.y);
		glVertex3f(a.x, a.y, a.z);
		colorist.color(b.y);
		glVertex3f(b.x, b.y, b.z);
		colorist.color(c.y);
		glVertex3f(c.x, c.y, c.z);
		
		colorist.color(a.y);
		glVertex3f(a.x, a.y, a.z);
		colorist.color(d.y);
		glVertex3f(d.x, d.y, d.z);
		colorist.color(c.y);
		glVertex3f(c.x, c.y, c.z);
		
		glEnd();		
	}
	
	/**
	 * Draws a rectangle through Points a and b
	 * 
	 * @param a bottom left corner
	 * @param b top right corner
	 * 
	 * TODO: research
	 */
	public static void drawQuad2f(Vector3 a, Vector3 b, Colorist colorist) {

		
		glBegin(GL_TRIANGLES);
		
		if (a.z == b.z) {
			colorist.color(a.y);
			glVertex3f(a.x, a.y, a.z);
			colorist.color(b.y);
			glVertex3f(a.x, b.y, a.z);
			glVertex3f(b.x, b.y, a.z);
			
			glVertex3f(b.x, b.y, a.z);
			colorist.color(a.y);
			glVertex3f(a.x, a.y, a.z);
			glVertex3f(b.x, a.y, a.z);
		} else if (a.y == b.y) {
			colorist.color(a.y);
			glVertex3f(a.x, a.y, a.z);
			glVertex3f(a.x, a.y, b.z);
			glVertex3f(b.x, a.y, b.z);
			
			glVertex3f(a.x, a.y, a.z);
			glVertex3f(b.x, a.y, a.z);
			glVertex3f(b.x, a.y, b.z);	
		} else {
			colorist.color(a.y);
			glVertex3f(a.x, a.y, a.z);
			colorist.color(b.y);
			glVertex3f(a.x, b.y, a.z);
			glVertex3f(a.x, b.y, b.z);
			
			glVertex3f(a.x, b.y, b.z);	
			colorist.color(a.y);
			glVertex3f(a.x, a.y, a.z);
			glVertex3f(a.x, a.y, b.z);
		}
		
		glEnd();
	}
	
	/**
	 * Draws a rectangle through a, b, c and x, y z
	 * 
	 * @param a bottom left x
	 * @param b bottom left y
	 * @param c bottom left z
	 * @param x top right x
	 * @param y top right y
	 * @param z top right z
	 */
	public static void drawQuad2f(float a, float b, float c, float x, float y, float z, Colorist colorist) {
		drawQuad2f(new Vector3(a, b, c), new Vector3(x, y, z), colorist);
	}
}
