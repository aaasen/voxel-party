package nexus.view.color;

/**
 * A Dirt Colorist
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import static org.lwjgl.opengl.GL11.glColor3f;

public class Sand extends Colorist {
	public Sand(float range, float offset) {
		super(range, offset);
	}
	
	/**
	 * Selects a color based on the value
	 * 
	 * @param value
	 */
	public void color(float value) {
		value = this.value(value);
		glColor3f(value, value, value / 2.0f);
	}
}
