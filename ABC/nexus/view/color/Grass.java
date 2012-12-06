package nexus.view.color;

/**
 * A Dirt Colorist
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import static org.lwjgl.opengl.GL11.glColor3f;

public class Grass extends Colorist {
	public Grass(float range, float offset) {
		super(range, offset);
	}
	
	/**
	 * Selects a color based on the value
	 * 
	 * @param value
	 */
	public void color(float value) {
		value = this.value(value);
		glColor3f(0.0f, value, 0.0f);
	}
}
