package nexus.model.renderable;

/**
 * BlockMasks define which parts of a Block must be rendered
 */

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glLineWidth;
import nexus.model.structs.Block;
import nexus.view.gl.Outlines;
import nexus.view.gl.Planes;

public class BlockMask implements Renderable {
	public static final float OUTLINE_R = 0.5f;
	public static final float OUTLINE_G = 0.5f;
	public static final float OUTLINE_B = 0.5f;
	public static final float OUTLINE_WIDTH = 4f;
	
	public Block block;
	public boolean render, top, bottom, near, far, left, right, outline;
	
	public BlockMask(Block block) {
		this.block = block;
		render = false;
		top = false;
		bottom = false;
		near = false;
		far = false;
		left = false;
		right = false;
		outline = false;
	}
	
	/**
	 * Draws the specified sides of the Mask's Block
	 */
	@Override
	public void draw() {
		if (render) {
			if (top) {
				Planes.drawQuad2f(block.a.x, block.b.y, block.a.z, block.b.x, block.b.y, block.b.z, block.colorist);
			} if (bottom) {
				Planes.drawQuad2f(block.a.x, block.a.y, block.a.z, block.b.x, block.a.y, block.b.z, block.colorist);
			} if (near) {
				Planes.drawQuad2f(block.a.x, block.a.y, block.a.z, block.b.x, block.b.y, block.a.z, block.colorist);
			} if (far) {
				Planes.drawQuad2f(block.a.x, block.a.y, block.b.z, block.b.x, block.b.y, block.b.z, block.colorist);	
			} if (left) {
				Planes.drawQuad2f(block.a.x, block.a.y, block.a.z, block.a.x, block.b.y, block.b.z, block.colorist);	
			} if (right) {
				Planes.drawQuad2f(block.b.x, block.a.y, block.a.z, block.b.x, block.b.y, block.b.z, block.colorist);	
			} if (outline) {
				glColor3f(OUTLINE_R, OUTLINE_G, OUTLINE_B);
				glLineWidth(OUTLINE_WIDTH);
				Outlines.rectPrism2f(block.a, block.b);
			}
		}
	}
}
