package model.structs;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import nexus.main.Model;
import nexus.model.structs.Block;
import nexus.model.structs.Camera;
import nexus.model.structs.Chunk;
import nexus.model.structs.ChunkContainer;
import nexus.model.structs.Vector3;

import org.junit.BeforeClass;
import org.junit.Test;

public class ChunkContainerTest {
	public static final double EXPECTED_GENERATION_TIME = 30;
	public static final double EXPECTED_CULLING_TIME = 20;
	public static final int TEST_ITERATIONS = 2; // default is 5, but this can be annoying for dev
	public static Model world;
	
	@BeforeClass
	public static void testSetup() {
		world = new Model(new Camera(new Vector3(0f, 0f, 0f), 0f, 0f, 0f, false));
	}

	@Test
	public void getBlock() {
		Block block = world.chunks.getBlock(new Vector3(0.5f, 0.5f, 0.5f));
		
		assertEquals(true, new Vector3(0f, 0f, 0f).equals(block.a));
	}
	
	@Test
	public void getBlockNegative() {
		Block block = world.chunks.getBlock(new Vector3(-0.5f, 0.5f, -0.5f));
		
		assertEquals(true, new Vector3(-1f, 0f, -1f).equals(block.a));
	}
	
	@Test
	public void getBlockOrigin() {
		Block block = world.chunks.getBlock(new Vector3(0f, 0f, 0f));
		
		assertEquals(true, new Vector3(0f, 0f, 0f).equals(block.a));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getBlockHigh() {
		world.chunks.getBlock(new Vector3(0f, 100000f, 0f));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getBlockLow() {
		world.chunks.getBlock(new Vector3(0f, -1000000f, 0f));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setBlockHigh() {
		world.chunks.setBlock(new Block(new Vector3(0f, 100000f, 0f), 1f, null));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setBlockLow() {
		world.chunks.setBlock(new Block(new Vector3(0f, -1000000f, 0f), 1f, null));
	}	
	
	@Test
	public void chunkGenerationSpeed() {
		long begin = System.currentTimeMillis();
		
		int numChunks = generateChunks(TEST_ITERATIONS, 0, false);
		
		long end = System.currentTimeMillis();
		long elapsed = end - begin;
		
		double timePerChunk = (double) elapsed / numChunks;
		
		assertEquals("took " + timePerChunk + " ms/chunk, expected " + EXPECTED_GENERATION_TIME, true, timePerChunk < EXPECTED_GENERATION_TIME);
	}
	
	@Test
	public void chunkCullingSpeed() {

		generateChunks(TEST_ITERATIONS, 100, false);
		
		long begin = System.currentTimeMillis();
		
		int numChunks = generateChunks(TEST_ITERATIONS - 1, 100, true);

		long end = System.currentTimeMillis();
		long elapsed = end - begin;
		double timePerChunk = (double) elapsed / numChunks;
	
		assertEquals("took " + timePerChunk + " ms/chunk, expected " + EXPECTED_CULLING_TIME, true, timePerChunk < EXPECTED_CULLING_TIME);
	}
	
	public int generateChunks(int iterations, int offset, boolean mask) {
		for (int i = -iterations; i <= iterations; i++) {
			for (int j = -iterations; j <= iterations; j++) {
				world.chunks.getChunk(i + offset, j + offset, mask);
			}
		}
		
		return (int) Math.pow(iterations * 2 + 1, 2);
	}
	
	@Test
	public void getKey() {
		int high = 100;
		int low = -high;
		
		Set<Long> results = new HashSet<Long>();
		int expected = (int) Math.pow(high + Math.abs(low) + 1, 2);
		
		for (int i = low; i <= high; i++) {
			for (int j = low; j <= high; j++) {
				results.add(ChunkContainer.getKey(i, j));
			}
		}
		
		assertEquals(expected, results.size());
	}
	
	@Test
	public void inBoundsBelow() {
		assertEquals(false, world.chunks.inBounds(new Vector3(0f, -1f, 0f)));
	}

	@Test
	public void inBoundsLowEdge() {
		assertEquals(true, world.chunks.inBounds(new Vector3(0f, 0f, 0f)));
	}
	
	@Test
	public void inBoundsInside() {
		assertEquals(true, world.chunks.inBounds(new Vector3(0f, 1f, 0f)));
	}
	
	@Test
	public void inBoundsAbove() {
		assertEquals(false, world.chunks.inBounds(new Vector3(0f, Chunk.HEIGHT, 0f)));
	}

	@Test
	public void inBoundsHighEdge() {
		assertEquals(true, world.chunks.inBounds(new Vector3(0f, Chunk.HEIGHT - 1, 0f)));
	}
}