package main;

import static org.lwjgl.opengl.GL11.*;

public class Player {
	float x, y;
	float speed = 0.0001f;
	
	public Player(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw() {
	    glBegin(GL_TRIANGLES);
	    glColor3f(1f, 0f, 0f);
	    glVertex2f(this.x, this.y);
	    glColor3f(0f, 1, 0f);
	    glVertex2f(this.x + 10, this.y - 10);
	    glColor3f(0f, 0f, 1f);
	    glVertex2f(this.x - 10, this.y - 10);
	    glEnd();
	}
	
	public void draw3D() {
		triprism(true);
	}
	
	 public void triprism(boolean solid){
		    // back endcap
		    glBegin(solid ? GL_TRIANGLES : GL_LINES);
		    glVertex3f(1f, 0f, 0f);
		    glVertex3f(0f, 0f, 0f);
		    glVertex3f(0f, 1f, 0f);
		    glEnd();

		    // front endcap
		    glBegin(solid ? GL_TRIANGLES : GL_LINES);
		    glVertex3f(1f, 0f, 1f);
		    glVertex3f(0f, 0f, 1f);
		    glVertex3f(0f, 1f, 1f);
		    glEnd();

		    // bottom
		    glBegin(solid ? GL_QUADS : GL_LINES);
		    glVertex3f(0f, 0f, 0f);
		    glVertex3f(1f, 0f, 0f);
		    glVertex3f(1f, 0f, 1f);
		    glVertex3f(0f, 0f, 1f);
		    glEnd();

		    // back
		    glBegin(solid ? GL_QUADS : GL_LINES);
		    glVertex3f(0f, 0f, 0f);
		    glVertex3f(0f, 1f, 0f);
		    glVertex3f(0f, 1f, 1f);
		    glVertex3f(0f, 0f, 1f);
		    glEnd();

		    // top
		    glBegin(solid ? GL_QUADS : GL_LINES);
		    glVertex3f(0f, 1f, 0f);
		    glVertex3f(1f, 0f, 0f);
		    glVertex3f(1f, 0f, 1f);
		    glVertex3f(0f, 1f, 1f);
		    glEnd();
		}
	
	public void moveForwards() {
		this.y += this.speed;
	}
	
	public void moveBackwards() {
		this.y -= this.speed;
	}
	
	public void moveRight() {
		this.x += this.speed;
	}
	
	public void moveLeft() {
		this.x -= this.speed;
	}
}
