package nexus.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nexus.main.Model;
import nexus.model.structs.Camera;
import nexus.model.structs.Chunk;
import nexus.model.structs.ChunkContainer;
import nexus.model.structs.Vector3;

import org.junit.BeforeClass;
import org.junit.Test;

public class ChunkContainerTest {
	public static Model world;
	
	@BeforeClass
	public static void testSetup() {
		world = new Model(new Camera(new Vector3(0f, 0f, 0f), 0f, 0f, 0f, false));
	}
	
	@Test
	public void testChunkContainer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChunkVector3() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChunkFloatFloat() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChunkIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChunkIntIntBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBlock() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBlockRound() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBlock() {
		fail("Not yet implemented");
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