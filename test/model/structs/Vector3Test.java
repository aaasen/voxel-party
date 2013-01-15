package model.structs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import nexus.model.structs.Vector3;

import org.junit.Test;

public class Vector3Test {

	@Test
	public void testEqualsVector3True() {
		Vector3 a = new Vector3(0f, 1.001f, 2f);
		Vector3 b = new Vector3(0f, 1.001f, 2.0f);
		
		assertEquals(true, a.equals(b));
	}
	
	@Test
	public void testEqualsVector3False() {
		Vector3 a = new Vector3(0f, 1f, 0.0f);
		Vector3 b = new Vector3(1f, 1f, 1f);
		
		assertEquals(false, a.equals(b));
	}
	
	@Test
	public void testAdd() {
		Vector3 a = new Vector3(4f, -4f, -4f);
		Vector3 b = new Vector3(1f, 3f, -2f);
		Vector3 expect = new Vector3(5f, -1f, -6f);
		Vector3 result = a.add(b);
		
		assertEquals(result.toString(), true, result.equals(expect));
	}

	@Test
	public void testSubtract() {
		Vector3 a = new Vector3(4f, -4f, -4f);
		Vector3 b = new Vector3(1f, 3f, -2f);
		Vector3 expect = new Vector3(3f, -7f, -2f);
		Vector3 result = a.subtract(b);
		
		assertEquals(result.toString(), true, result.equals(expect));
	}

	@Test
	public void testLength() {
		Vector3 a = new Vector3(1f, 2f, 2f);
		
		assertEquals(3f, a.length(), 0);
	}

	@Test
	public void testLengthSquared() {
		Vector3 a = new Vector3(1f, 2f, 2f);
		
		assertEquals(9f, a.lengthSquared(), 0);
	}

	@Test
	public void testScale() {
		Vector3 a = new Vector3(1f, 2f, -2f);
		float scalar = 4f;
		Vector3 expect = new Vector3(4f, 8f, -8f);
		Vector3 result = a.scale(scalar);
		
		assertEquals(true, expect.equals(result));
	}

	@Test
	public void testNormalize() {
		Vector3 a = new Vector3(1f, 2f, 2f);
		Vector3 expect = new Vector3(1f/3, 2f/3, 2f/3);
		Vector3 result = a.normalize();
		
		assertEquals(true, result.equals(expect));
	}

	@Test
	public void testDotProduct() {
		Vector3 a = new Vector3(1f, 2f, 3f);
		Vector3 b = new Vector3(4f, -5f, 6f);
		float expect = 12f;
		float result = a.dotProduct(b);
		
		assertEquals(result, expect, 0f);
	}

	@Test
	public void testCrossProduct() {
		Vector3 a = new Vector3(3f, -3f, 1f);
		Vector3 b = new Vector3(4f, 9f, 2f);
		Vector3 expect = new Vector3(-15f, -2f, 39f);
		Vector3 result = a.crossProduct(b);
		
		assertEquals(true, result.equals(expect));
	}

	@Test
	public void testProjection() {
		Vector3 a = new Vector3(3f, -3f, 1f);
		Vector3 b = new Vector3(4f, 9f, 2f);
		Vector3 expect = new Vector3(-0.5148515f, -1.1584159f, -0.25742576f);
		Vector3 result = a.projection(b);
		
		assertEquals(result.toString() + " : " + expect.toString(), true, result.equals(expect));		
	}

	@Test
	public void testPerpendicular() {
		fail("not implemented");
	}
	
	@Test
	public void testToArray() {
		Vector3 a = new Vector3(3f, -1f, 0f);
		
		assertEquals(true, Arrays.equals(a.toArray(), new float[] { 3f, -1f, 0f }));
	}
	
	@Test
	public void testCombined() {
		Vector3 a = new Vector3(0f, 1f, 2f);
		Vector3 b = new Vector3(3f, 4f, 5f);
		Vector3 c = new Vector3(6f, 7f, 8f);
		
		assertEquals(true, Arrays.equals(Vector3.combine(new Vector3[] { a, b, c }),
				new float[] { 0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f }));
	}
}
