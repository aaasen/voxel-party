package model.structs;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import nexus.model.generators.Noise1D;

import org.junit.Test;

public class Noise1DTest {
	public static final int TEST_ITERATIONS = (int) Math.pow(2, 20);
	
	@Test
	public void testNoise() {
		
		Set<Float> results = new HashSet<Float>();
		
		for (int i = 0; i < TEST_ITERATIONS; i++) {
			results.add(Noise1D.noise(i, Noise1D.primes[i % 3]));
		}
		
		System.out.println(TEST_ITERATIONS - results.size());
		assertEquals(TEST_ITERATIONS, results.size());
	}
}
