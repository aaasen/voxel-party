package nexus.model.renderable;

/**
 * BlockMasks define which parts of a Block must be rendered
 */

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glLineWidth;
import nexus.model.structs.Block;
import nexus.model.structs.Vector3;
import nexus.view.gl.Outlines;
import nexus.view.gl.Planes;
import nexus.view.structs.VertexContainer;

public class BlockMask implements Renderable {
	public static final float OUTLINE_R = 0.5f;
	public static final float OUTLINE_G = 0.5f;
	public static final float OUTLINE_B = 0.5f;
	public static final float OUTLINE_WIDTH = 4f;
	
	public Block block;
	public boolean render, drawTop, drawBottom, drawNear, drawFar, drawLeft, drawRight, drawOutline;
	public VertexContainer top, bottom, near, far, left, right;
	
	public BlockMask(Block block) {
		this.block = block;
		render = false;
		drawTop = false;
		drawBottom = false;
		drawNear = false;
		drawFar = false;
		drawLeft = false;
		drawRight = false;
		drawOutline = false;
		
		top = new VertexContainer(Planes.makeQuad2f(new Vector3(block.a.x, block.b.y, block.a.z), block.b));
		bottom = new VertexContainer(Planes.makeQuad2f(block.a, new Vector3(block.b.x, block.a.y, block.b.z)));
		near = new VertexContainer(Planes.makeQuad2f(block.a, new Vector3(block.b.x, block.b.y, block.a.z)));
		far = new VertexContainer(Planes.makeQuad2f(new Vector3(block.a.x, block.a.y, block.b.z), block.b));
		left = new VertexContainer(Planes.makeQuad2f(block.a, new Vector3(block.a.x, block.b.y, block.b.z)));
		right = new VertexContainer(Planes.makeQuad2f(new Vector3(block.b.x, block.b.y, block.a.z), block.b));
	}
	
	/**
	 * Draws the specified sides of the Mask's Block
	 */
	@Override
	public void draw() {
		if (render) {
			if (drawTop) {
				top.render();
			} if (drawBottom) {
				bottom.render();
			} if (drawNear) {
				near.render();
			} if (drawFar) {
				far.render();	
			} if (drawLeft) {
				left.render();
			} if (drawRight) {
				right.render();
			} if (drawOutline) {
				glColor3f(OUTLINE_R, OUTLINE_G, OUTLINE_B);
				glLineWidth(OUTLINE_WIDTH);
				Outlines.rectPrism2f(block.a, block.b);
			}
		}
	}
}
