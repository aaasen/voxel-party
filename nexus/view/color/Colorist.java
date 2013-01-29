package nexus.view.color;

import static org.lwjgl.opengl.GL11.glColor3f;

/**
 * The parent class for all Colorists, which handle dynamically coloring things
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

public class Colorist {
	float range;
	float offset;
	
	/**
	 * Creates a Colorist
	 * 
	 * @param range
	 * @param offset
	 */
	public Colorist(float range, float offset) {
		this.range = range;
		this.offset = offset;
	}
	
	/**
	 * Adjusts the value to a float from 0.0 to 1.0 based on the Colorist's range
	 * 
	 * @param value
	 * @return value mapped into 0.0 to 1.0
	 */
	public float value(float value) {
		return (value - offset) / this.range;
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
