package noise;

/**
 * Utilities for generating Perlin noise
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * @see [http://freespace.virgin.net/hugo.elias/models/m_perlin.htm](Noise Tutorial)
 *
 */

public class Perlin {

	/**
	 * Given two y values a and b which are 1 apart on the x axis, return the y value of the point that is x units from a on the x axis 
	 * 
	 * @param a y value of left most point
	 * @param b y value of right most point
	 * @param x offset of the point from a
	 * @return
	 */
	public static float cosineInterpolate(float a, float b, float x) {
		float ft = x * (float) Math.PI;
		float f = (1.0f - (float) Math.cos(ft)) * 0.5f;

		return a*(1-f) + b*f;
	}
}
