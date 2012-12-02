package nexus.model.objects;

/**
 * A horizontal Grid for orientation
 * 
 *  @author Lane Aasen <laneaasen@gmail.com>
 */

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class Grid implements Renderable {
	float width, spacing, height, thickness;
	
	/**
	 * Creates a Grid
	 * 
	 * @param width the Grid will extend outwards from the origin by this much
	 * @param spacing units in between each line of the grid
	 * @param height y coordinate of the Grid
	 * @param thickness thickness (in pixels) for the grid lines
	 */
	public Grid(float width, float spacing, float height, float thickness) {
		this.width = width;
		this.spacing = spacing;
		this.height = height;
		this.thickness = thickness;
	}
	
	@Override
	public void draw() {
		glLineWidth(this.thickness);
		glBegin(GL_LINES);
        
		for(float i = -this.width; i <= this.width; i += this.spacing) {
			glVertex3f(-this.width, this.height, i);
			glVertex3f(this.width, this.height, i);
			glVertex3f(i, this.height, -this.width);
			glVertex3f(i, this.height, this.width);			
		}

		glEnd();
	}

}
