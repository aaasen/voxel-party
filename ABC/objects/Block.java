package objects;

import main.Point;

/**
 * A generic cube
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Block implements Renderable {
	Point position;
	
	public Block(Point position) {
		this.position = position;
	}
	
	@Override
	public void draw() {
		
	}
}
