package nexus.model.objects;

import static org.lwjgl.opengl.GL11.glColor3f;
import nexus.glhelper.Outlines;
import nexus.glhelper.Prisms;
import nexus.main.Vector3;

/**
 * A generic rectangular prism
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Block implements Renderable {
	Vector3 a;
	Vector3 b;
	
	/**
	 * Creates a Block
	 * 
	 * @param position near bottom right corner of the block
	 * @param width width on the x axis
	 * @param height height on the y axis
	 * @param depth depth on the z axis
	 */
	public Block(Vector3 a, Vector3 b) {
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
