package objects;

import main.Point;
import glhelper.Planes;

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
		Planes.drawQuad2d(a, b);
	}
}
