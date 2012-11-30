package noise;

/**
 * Utilities for creating deterministic Noise
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * @see [http://freespace.virgin.net/hugo.elias/models/m_perlin.htm](Noise Tutorial)
 * 
 */

public class Noise {


	public static final int[] primes0 = {15731, 789221, 1376312589, 107374182};

	/**
	 * Deteministically hashes one number
	 * 
	 * @param x
	 * @param primes set of 4 primes to use in generating the noise
	 * @return deteministic noise; hash of x
	 */
	public static float noise1f(int x, int[] primes) {
		x = (x<<13) ^ x;
		return (1.0f - ( (x * (x * x * primes[0] + primes[1]) + primes[2]) & 0x7fffffff) / (float) primes[3]);
	}

	/**
	 * Deteministically hashes two numbers
	 * 
	 * @param x
	 * @param y
	 * @param primes set of 4 primes to use in generating the noise
	 * @return deteministic noise; hash of x
	 */
	public static float noise2f(int x, int y, int[] primes) {
		int n = x + y * 57;
		n = (n<<13) ^ n;
		return noise1f(n, primes);   	
	}
}
