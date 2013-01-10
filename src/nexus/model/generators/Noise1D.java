package nexus.model.generators;

public class Noise1D {
	
	public static final int[][] primes = { {15731, 789221, 1376312589, 107374182} };
	
	/**
	 * Deteministically hashes one number
	 * 
	 * @param x
	 * @param primes set of 4 primes to use in generating the noise
	 * @return deteministic noise; hash of x
	 */
	public static float noise(int x, int[] primes) {
		x = (x<<17) ^ x; 
		return ((((x * (x * x * primes[0] + primes[1]) + primes[2]) & 0x7fffffff) / (float) primes[3]) - 10.0f) / 10.0f;
	}


	/**
	 * Creates smooth noise from one numbers based on its neighbors
	 * 
	 * @param x
	 * @param primes set of 4 primes to use in generating the noise
	 * @return deteministic noise
	 */
	public static float smooth(int x, int[] primes) {
		return noise(x, primes) / 2.0f + noise(x - 1, primes) / 4.0f + noise(x + 1, primes) / 4.0f;
	}
	
	/**
	 * Performs cosine interpolation on smooth noise
	 * 
	 * @param x
	 * @param primes
	 * @return
	 */
	public static float interpolate(float x, int[] primes) {
		int intX = (int) x;
		float fractionalX = x - intX;
		
		float v1 = smooth(intX, primes);
		float v2 = smooth(intX + 1, primes);
		
		return Interpolate.cosine(v1, v2, fractionalX);
	}
}
