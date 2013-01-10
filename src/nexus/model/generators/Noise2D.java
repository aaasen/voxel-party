package nexus.model.generators;

/**
 * Utilities for creating deterministic Noise
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * @see [http://freespace.virgin.net/hugo.elias/models/m_perlin.htm](Noise Tutorial)
 * 
 */

public class Noise2D {	

	/**
	 * Deteministically hashes two numbers
	 * 
	 * @param x
	 * @param y
	 * @param primes set of 4 primes to use in generating the noise
	 * @return deteministic noise; hash of x
	 */
	public static float noise(int x, int y, int[] primes) {		
		int n = x + y * 57;
		n = (n<<13) ^ n;
		return Noise1D.noise(n, primes);
	}

	/**
	 * Creates smooth noise from two numbers based on its neighbors
	 * 
	 * @param x
	 * @param primes set of 4 primes to use in generating the noise
	 * @return deteministic noise
	 */
	public static float smooth(int x, int y, int[] primes) {
		float corners = (noise(x - 1, y - 1, primes) + noise(x + 1, y - 1, primes) + noise(x - 1, y + 1, primes) + noise(x + 1, y + 1, primes)) / 16.0f;
		float sides   = (noise(x - 1, y, primes) + noise(x + 1, y, primes) + noise(x, y - 1, primes) + noise(x, y + 1, primes)) / 8.0f;
		float center  =  noise(x, y, primes) / 4.0f;
		return corners + sides + center;
	}
	
	/**
	 * Performs cosine interpolation on smooth noise
	 * 
	 * @param x
	 * @param primes
	 * @return
	 */
	public static float interpolate(float x, float y, int[] primes) {
		int intX = (int) x;
		float fractionalX = x - intX;
		
		int intY = (int) y;
		float fractionalY = y - intY;
		
		float v1 = smooth(intX, intY, primes);
		float v2 = smooth(intX + 1, intY, primes);
		float v3 = smooth(intX, intY + 1, primes);
		float v4 = smooth(intX + 1, intY + 1, primes);
		
		float i1 = Interpolate.cosine(v1, v2, fractionalX);
		float i2 = Interpolate.cosine(v3, v4, fractionalX);
		
		return Interpolate.cosine(i1, i2, fractionalY);
	}
}
