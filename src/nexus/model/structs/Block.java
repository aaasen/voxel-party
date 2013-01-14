package nexus.model.structs;

import nexus.model.renderable.BlockMask;
import nexus.view.color.Colorist;
import nexus.view.gl.VertexContainer;

/**
 * A generic rectangular prism
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Block {
	public Vector3 a;
	public Vector3 b;
	public float dimension;
	public Colorist colorist;
	public BlockMask mask;
	public VertexContainer vertices;
	
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
		this.mask = new BlockMask(this);
	}
	
	public void draw() {
		if (visible()) {
			mask.draw();	
		}
	}
	
	public boolean visible() {
		return true;
	}
}
