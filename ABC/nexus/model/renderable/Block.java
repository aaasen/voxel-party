package nexus.model.renderable;

import static org.lwjgl.opengl.GL11.glColor3f;
import nexus.model.structs.Vector3;
import nexus.view.color.Colorist;
import nexus.view.gl.Prisms;

/**
 * A generic rectangular prism
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Block implements Renderable {
	public Vector3 a;
	public Vector3 b;
	float dimension;
	Colorist colorist;
	
	/**
	 * Creates a Block
	 * 
	 * @param position near bottom right corner of the block
	 * @param width width on the x axis
	 * @param height height on the y axis
	 * @param depth depth on the z axis
	 */
	public Block(Vector3 a, float dimension, Colorist colorist) {
		this.a = a;
		this.b = new Vector3(a.x + dimension, a.y + dimension, a.z + dimension);
		this.dimension = dimension;
		this.colorist = colorist;
	}
	
	@Override
	public void draw() {
		glColor3f(1.0f, 1.0f, 1.0f);
		Prisms.drawRectPrism2f(a, b, colorist);
//		glColor3f(1.0f, 0.0f, 0.0f);
//		Outlines.outlineRectPrism2f(a, b);
	}
}
