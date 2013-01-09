package nexus.model.generators;

/**
 * A library of static functions for Interpolation
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Interpolate {
	
	/**
	 * Given two y values a and b which are 1 apart on the x axis, return the y value of the point that is x units from a on the x axis 
	 * 
	 * @param a y value of left most point
	 * @param b y value of right most point
	 * @param x offset of the point from a
	 * @return
	 */
	public static float cosine(float a, float b, float x) {
		float ft = x * (float) Math.PI;
		float f = (1.0f - (float) Math.cos(ft)) * 0.5f;

		return a*(1-f) + b*f;
	}
}
