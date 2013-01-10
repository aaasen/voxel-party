package model.generators;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import nexus.model.generators.Noise1D;

import org.junit.Test;

public class Noise1DTest {
	public static final float ACCEPTABLE_DISTRIBUTION_PERCENT = 0.01f;
	public static final float ACCEPTABLE_COLLISION_PERCENT = 0.05f;
	public static final int TEST_ITERATIONS = (int) Math.pow(2, 20);
	public final TreeSet<Float> noise = new TreeSet<Float>(genRandom(TEST_ITERATIONS));
	
	@Test
	public void noiseCollision() {
		System.out.println(noise.size());
		System.out.println(TEST_ITERATIONS);
		assertEquals((TEST_ITERATIONS - (float) noise.size()) / TEST_ITERATIONS, 0f, ACCEPTABLE_COLLISION_PERCENT);
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
			results.add(Noise1D.noise(i, Noise1D.primes[0]));
		}
		
		return results;
	}
}
