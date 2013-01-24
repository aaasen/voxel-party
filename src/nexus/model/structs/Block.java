package nexus.model.structs;

import nexus.model.renderable.BlockMask;
import nexus.view.color.Colorist;

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
	private BlockMask mask;
	
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
	
	public void draw() {
		if (this.visible() && this.mask != null) {
			mask.draw();
		}
	}
	
	public boolean visible() {
		return true;
	}
	
	public BlockMask getMask() {
		if (this.mask == null && this.visible()) {
			this.mask = new BlockMask(this);
		}
		
		return this.mask;
	}
	
	public boolean isOnGrid() {
		return (a.length() % 1 == 0);
	}
}
