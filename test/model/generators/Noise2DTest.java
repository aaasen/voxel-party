package model.generators;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import nexus.model.generators.Noise1D;
import nexus.model.generators.Noise2D;

import org.junit.Test;

public class Noise2DTest {
	public static final float ACCEPTABLE_DISTRIBUTION_PERCENT = 0.01f;
	public static final float ACCEPTABLE_COLLISION_PERCENT = 0.05f;
	public static final int TEST_ITERATIONS = (int) Math.pow(2, 11);
	public static final int TEST_RUNS = TEST_ITERATIONS * TEST_ITERATIONS;
	public final TreeSet<Float> noise = new TreeSet<Float>(genRandom(TEST_ITERATIONS));
	
	@Test
	public void noiseCollision() {
		assertEquals((float) noise.size() / TEST_RUNS, 0, ACCEPTABLE_COLLISION_PERCENT);
	}

	@Test
	public void noiseDistribution() {
		float expectedAverage = (noise.first() + noise.last()) / 2;
		double sum = 0;
		Iterator<Float> iter = noise.iterator();
		
		while (iter.hasNext()) {
			sum += iter.next();
		}
		
		float realAverage = (float) (sum / noise.size());
		
		assertEquals(expectedAverage, realAverage, ACCEPTABLE_DISTRIBUTION_PERCENT);
	}
	
	@Test
	public void noiseLow() {
		assertEquals(noise.first() + "", true, noise.first() >= -1);
	}
	
	@Test
	public void noiseHigh() {
		assertEquals(noise.last() + "", true, noise.last() <= 1);
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
