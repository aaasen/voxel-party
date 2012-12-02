package nexus.color;

/**
 * A Greyscale Colorist
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import static org.lwjgl.opengl.GL11.glColor3f;

public class Greyscale extends Colorist {
	public Greyscale(float range, float offset) {
		super(range, offset);
	}
	
	/**
	 * Selects a color based on the value
	 * 
	 * @param value
	 */
	public void color(float value) {
		value = this.value(value);
		glColor3f(value, value, value);
	}
}
