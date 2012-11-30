package glhelper;

import static org.lwjgl.opengl.GL11.glColor3f;

/**
 * A collection of static helper methods for Color Ranges
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

public class ColorRange {
	public static void bw(float range, float x) {
		float value = x / range;
		glColor3f(value, value, value);
	}
}
