package model.generators;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import nexus.model.generators.Noise1D;
import nexus.model.generators.Noise2D;

import org.junit.Test;

public class Noise2DTest {
	public static final int TEST_ITERATIONS = (int) Math.pow(2, 11);
	public static final int TEST_RUNS = TEST_ITERATIONS * TEST_ITERATIONS;
	public final TreeSet<Float> noise = new TreeSet<Float>(genRandom(TEST_ITERATIONS));
	
	@Test
	public void noiseCollision() {
		assertEquals((float) noise.size() / TEST_RUNS, 0, 0.05f);
	}
	
	private Set<Float> genRandom(int iterations) {
		Set<Float> results = new HashSet<Float>();
		
		for (int i = 0; i < iterations; i++) {
			for (int j = 0; j < iterations; j++) {
				results.add(Noise2D.noise(i, j, Noise1D.primes[0]));	
			}
		}
		
		return results;
	}
}
