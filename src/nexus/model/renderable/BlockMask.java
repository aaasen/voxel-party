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
	private boolean render, drawTop, drawBottom, drawNear, drawFar, drawLeft, drawRight, drawOutline;
	private VertexContainer top, bottom, near, far, left, right;
	
	public BlockMask(Block block) {
		this.block = block;
		setRender(false);
		setDrawTop(false);
		setDrawBottom(false);
		setDrawNear(false);
		setDrawFar(false);
		setDrawLeft(false);
		setDrawRight(false);
		setDrawOutline(false);	
	}
	
	/**
	 * Draws the specified sides of the Mask's Block
	 */
	@Override
	public void draw() {
		if (isRender()) {
			if (isDrawTop()) {
				top.render();
			} if (isDrawBottom()) {
				bottom.render();
			} if (isDrawNear()) {
				near.render();
			} if (isDrawFar()) {
				far.render();	
			} if (isDrawLeft()) {
				left.render();
			} if (isDrawRight()) {
				right.render();
			} if (isDrawOutline()) {
				glColor3f(OUTLINE_R, OUTLINE_G, OUTLINE_B);
				glLineWidth(OUTLINE_WIDTH);
				Outlines.rectPrism2f(block.a, block.b);
			}
		}
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public boolean isDrawRight() {
		return drawRight;
	}

	public void setDrawRight(boolean drawRight) {
		if (drawRight && this.right == null) {
			this.right = new VertexContainer(Planes.makeQuad2f(new Vector3(block.b.x, block.b.y, block.a.z), block.b));	
		}
		
		this.drawRight = drawRight;
	}

	public boolean isDrawLeft() {
		return drawLeft;
	}

	public void setDrawLeft(boolean drawLeft) {
		if (drawLeft && this.left == null) {
			this.left = new VertexContainer(Planes.makeQuad2f(block.a, new Vector3(block.a.x, block.b.y, block.b.z)));
		}
		
		this.drawLeft = drawLeft;
	}

	public boolean isDrawNear() {
		return drawNear;
	}

	public void setDrawNear(boolean drawNear) {
		if (drawNear && this.near == null) {
			this.near = new VertexContainer(Planes.makeQuad2f(block.a, new Vector3(block.b.x, block.b.y, block.a.z)));
		}
		
		this.drawNear = drawNear;
	}

	public boolean isDrawFar() {
		return drawFar;
	}

	public void setDrawFar(boolean drawFar) {
		if (drawFar && this.far == null) {
			this.far = new VertexContainer(Planes.makeQuad2f(new Vector3(block.a.x, block.a.y, block.b.z), block.b));
		}		
		
		this.drawFar = drawFar;
	}

	public boolean isDrawBottom() {
		return drawBottom;
	}

	public void setDrawBottom(boolean drawBottom) {
		if (drawBottom && this.bottom == null) {
			this.bottom = new VertexContainer(Planes.makeQuad2f(block.a, new Vector3(block.b.x, block.a.y, block.b.z)));
		}
		
		this.drawBottom = drawBottom;
	}

	public boolean isDrawTop() {
		return drawTop;
	}

	public void setDrawTop(boolean drawTop) {
		if (drawTop && this.top == null) {
			this.top = new VertexContainer(Planes.makeQuad2f(new Vector3(block.a.x, block.b.y, block.a.z), block.b));
		}	
		
		this.drawTop = drawTop;
	}

	public boolean isDrawOutline() {
		return drawOutline;
	}

	public void setDrawOutline(boolean drawOutline) {
		this.drawOutline = drawOutline;
	}
}
