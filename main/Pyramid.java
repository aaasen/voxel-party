package main;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 * A colorful pyramid
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Pyramid implements Renderable {
	Point position;
	
	public Pyramid(float x, float y, float z) {
		this.position = new Point(x, y, z);
	}
	
	public Pyramid(Point position) {
		this.position = position;
	}
	
	@Override
	public void draw() {
		glBegin(GL_TRIANGLES);								
		glColor3f(1.0f,0.0f,0.0f);						
		glVertex3f( 0.0f, 1.0f, 0.0f);					
		glColor3f(0.0f,1.0f,0.0f);						
		glVertex3f(-1.0f,-1.0f, 1.0f);					
		glColor3f(0.0f,0.0f,1.0f);						
		glVertex3f( 1.0f,-1.0f, 1.0f);					
		glColor3f(1.0f,0.0f,0.0f);						
		glVertex3f( 0.0f, 1.0f, 0.0f);					
		glColor3f(0.0f,0.0f,1.0f);						
		glVertex3f( 1.0f,-1.0f, 1.0f);					
		glColor3f(0.0f,1.0f,0.0f);						
		glVertex3f( 1.0f,-1.0f, -1.0f);					
		glColor3f(1.0f,0.0f,0.0f);						
		glVertex3f( 0.0f, 1.0f, 0.0f);					
		glColor3f(0.0f,1.0f,0.0f);						
		glVertex3f( 1.0f,-1.0f, -1.0f);					
		glColor3f(0.0f,0.0f,1.0f);						
		glVertex3f(-1.0f,-1.0f, -1.0f);					
		glColor3f(1.0f,0.0f,0.0f);						
		glVertex3f( 0.0f, 1.0f, 0.0f);					
		glColor3f(0.0f,0.0f,1.0f);						
		glVertex3f(-1.0f,-1.0f,-1.0f);					
		glColor3f(0.0f,1.0f,0.0f);						
		glVertex3f(-1.0f,-1.0f, 1.0f);					
		glEnd();		
	}
}
