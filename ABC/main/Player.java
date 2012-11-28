package main;

import static org.lwjgl.opengl.GL11.*;

public class Player {
	float x, y;
	
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
}
