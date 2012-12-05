package nexus.model.renderable;

/**
 * BlockMasks define which parts of a Block must be rendered
 */

import nexus.model.structs.Block;
import nexus.view.gl.Prisms;

public class BlockMask implements Renderable {

	public Block block;
	public boolean render, top, bottom, near, far, left, right;
	
	public BlockMask(Block block) {
		this.block = block;
		render = false;
		top = false;
		bottom = false;
		near = false;
		far = false;
		left = false;
		right = false;
	}
	
	@Override
	public void draw() {
//		if (render) {
			Prisms.drawRectPrism2f(block.a, block.b, block.colorist);
//		}
	}
}
