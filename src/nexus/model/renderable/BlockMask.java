package nexus.model.renderable;

/**
 * BlockMasks define which parts of a Block must be rendered
 */

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glLineWidth;

import java.util.Arrays;

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
	
	public static enum index { TOP, BOTTOM, LEFT, RIGHT, NEAR, FAR }
	
	public Block block;

	// top, bottom, near, far, left, right
	private boolean[] doRender;
	public boolean render = false;
	public boolean outline = false;
	private VertexContainer[] faces;

	public BlockMask(Block block) {
		this.block = block;

		this.doRender = new boolean[6];
		Arrays.fill(doRender, false);
		
		this.faces = new VertexContainer[6];
	}

	/**
	 * Draws the specified sides of the Mask's Block
	 */
	@Override
	public void draw() {
		if (this.render) {
			for (int i = 0; i < faces.length; i++) {
				if (doRender[i]) {
					getFace(i).render();
				}
			}

			if (this.outline); {
				glColor3f(OUTLINE_R, OUTLINE_G, OUTLINE_B);
				glLineWidth(OUTLINE_WIDTH);
				Outlines.rectPrism2f(block.a, block.b);
			}
		}
	}
	
	public VertexContainer getFace(int i) {
		if (this.faces[i] == null) {
			this.faces[i] = makeFace(i);
		}
		
		return this.faces[i];
	}
	
	public VertexContainer makeFace(int i) {
		if (i == index.TOP.ordinal()) {
			return new VertexContainer(Planes.makeQuad2f(new Vector3(block.a.x, block.b.y, block.a.z), block.b));
		} else if (i == index.BOTTOM.ordinal()) {
			return new VertexContainer(Planes.makeQuad2f(block.a, new Vector3(block.b.x, block.a.y, block.b.z)));
		} else if (i == index.RIGHT.ordinal()) {
			return new VertexContainer(Planes.makeQuad2f(new Vector3(block.b.x, block.b.y, block.a.z), block.b));
		} else if (i == index.LEFT.ordinal()) {
			return new VertexContainer(Planes.makeQuad2f(block.a, new Vector3(block.a.x, block.b.y, block.b.z)));
		} else if (i == index.NEAR.ordinal()) {
			return new VertexContainer(Planes.makeQuad2f(block.a, new Vector3(block.b.x, block.b.y, block.a.z)));
		} else if (i == index.FAR.ordinal()) {
			return new VertexContainer(Planes.makeQuad2f(new Vector3(block.a.x, block.a.y, block.b.z), block.b));
		} else {
			throw new IllegalArgumentException(i + " is out of range");
		}
	}
	
	public void setRender(boolean x) {
		this.render = x;
	}
	
	public void setDrawOutline(boolean x) {
		this.outline = x;
	}
	
	public void setDrawTop(boolean x) {
		this.doRender[index.TOP.ordinal()] = x;
	}
	
	public void setDrawBottom(boolean x) {
		this.doRender[index.BOTTOM.ordinal()] = x;
	}
	
	public void setDrawLeft(boolean x) {
		this.doRender[index.LEFT.ordinal()] = x;
	}
	
	public void setDrawRight(boolean x) {
		this.doRender[index.RIGHT.ordinal()] = x;
	}
	
	public void setDrawNear(boolean x) {
		this.doRender[index.NEAR.ordinal()] = x;
	}
	
	public void setDrawFar(boolean x) {
		this.doRender[index.FAR.ordinal()] = x;
	}
}
