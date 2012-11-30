package objects;

import static org.lwjgl.opengl.GL11.glColor3f;
import main.Point;
import glhelper.Outlines;
import glhelper.Prisms;

/**
 * A generic rectangular prism
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Block implements Renderable {
	Point a;
	Point b;
	
	/**
	 * Creates a Block
	 * 
	 * @param position near bottom right corner of the block
	 * @param width width on the x axis
	 * @param height height on the y axis
	 * @param depth depth on the z axis
	 */
	public Block(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void draw() {
		glColor3f(1.0f, 1.0f, 1.0f);
		Prisms.drawRectPrism2f(a, b);
		glColor3f(1.0f, 0.0f, 0.0f);
		Outlines.outlineRectPrism2f(a, b);
	}
}
