package main;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class BasicDrawing {
	public static void drawGrid(float width, float spacing, float y, boolean thick) {
        glLineWidth(thick ? 3.0f : 1.0f);
		glBegin(GL_LINES);
        
		for(float i = -width; i <= width; i += spacing) {
			glVertex3f(-width, y, i);
			glVertex3f(width, y, i);
			glVertex3f(i, y, -width);
			glVertex3f(i, y, width);			
		}

		glEnd();
	}
}
