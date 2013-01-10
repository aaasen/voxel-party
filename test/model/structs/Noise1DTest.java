package model.structs;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import nexus.model.generators.Noise1D;

import org.junit.Test;

public class Noise1DTest {
	public static final int TEST_ITERATIONS = (int) Math.pow(2, 20);
	public final TreeSet<Float> noise = new TreeSet<Float>(genRandom(TEST_ITERATIONS));
	
	@Test
	public void noiseCollision() {
		assertEquals(TEST_ITERATIONS - noise.size() + " collisions (" + (float) noise.size() / TEST_ITERATIONS + "%)", TEST_ITERATIONS, noise.size());
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
		
		assertEquals(expectedAverage, realAverage, 0.1f);
	}
	
	@Test
	public void noiseLow() {
		assertEquals(true, noise.first() >= 0);
	}
	
	@Test
	public void noiseHigh() {
		assertEquals(true, noise.last() <= 0);
	}
	
	private Set<Float> genRandom(int iterations) {
		Set<Float> results = new HashSet<Float>();
		
		for (int i = 0; i < iterations; i++) {
			results.add(Noise1D.noise(i, Noise1D.primes[i % 3]));
		}
		
		return results;
	}
}
