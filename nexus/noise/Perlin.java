package nexus.noise;

/**
 * Utilities for generating Perlin noise
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * @see [http://freespace.virgin.net/hugo.elias/models/m_perlin.htm](Noise Tutorial)
 *
 */

public class Perlin {
	public static final float P = 0.5f;
	public static final int OCTAVES = 1;
	
	public static float perlin2D(float x, float y) {
		float total = 0.0f;
		
		for (int i = 0; i < OCTAVES; i++) {
			int frequency = (int) Math.pow(2, i);
			float amplitude = (float) Math.pow(P, i);
			
			total += Noise.interpolateNoise2f(x * frequency, y * frequency, Noise.primes[0]) * amplitude;
		}
		
		return total;
	}
}
