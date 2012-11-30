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
	 * Creates smooth noise from one numbers based on its neighbors
	 * 
	 * @param x
	 * @param primes set of 4 primes to use in generating the noise
	 * @return deteministic noise
	 */
	public static float smoothNoise1f(int x, int[] primes) {
		return noise1f(x, primes) / 2.0f + noise1f(x - 1, primes) / 4.0f + noise1f(x + 1, primes) / 4.0f;
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

	/**
	 * Creates smooth noise from two numbers based on its neighbors
	 * 
	 * @param x
	 * @param primes set of 4 primes to use in generating the noise
	 * @return deteministic noise
	 */
	public static float smoothNoise2f(int x, int y, int[] primes) {
		float corners = (noise2f(x - 1, y - 1, primes) + noise2f(x + 1, y - 1, primes) + noise2f(x - 1, y + 1, primes) + noise2f(x + 1, y + 1, primes)) / 16.0f;
		float sides   = (noise2f(x - 1, y, primes) + noise2f(x + 1, y, primes) + noise2f(x, y - 1, primes) + noise2f(x, y + 1, primes)) / 8.0f;
		float center  =  noise2f(x, y, primes) / 4.0f;
		return corners + sides + center;
	}
}
