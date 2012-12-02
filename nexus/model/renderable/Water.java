package nexus.model.renderable;

import nexus.model.structs.Color;
import nexus.model.structs.Vector3;
import nexus.view.gl.Planes;

/**
 * Very basic Water; a very large plane
 *
 * TODO: Replace this with something real
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Water implements Renderable {	
	Vector3 position;
	float width, depth;
	Color color;
	
	public Water(Vector3 position, float width, float depth, Color color) {
		this.position = position;
		this.width = width;
		this.depth = depth;
		this.color = color;
	}
	
	public void draw() {
		this.color.color();
		Planes.drawQuad2f(this.position, new Vector3(this.position.x + this.width, this.position.y, this.position.z + this.depth));
	}

}
