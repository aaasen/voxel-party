package view.gl;

import static org.junit.Assert.*;

import java.util.Arrays;

import nexus.model.structs.Vector3;
import nexus.view.gl.Planes;

import org.junit.Test;

public class PlanesTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void makeQuad2fSwappedArguments() {
		Vector3 a = new Vector3(0, 0, 0);
		Vector3 b = new Vector3(1, 1, 1);
		
		Planes.makeQuad2f(b, a);
	}
	
	@Test
	public void makeQuad2fXAxis() {
		Vector3 a = new Vector3(0, 0, 0);
		Vector3 b = new Vector3(0, 1, 1);
		float[] result = Planes.makeQuad2f(a, b);
		float[] expected = new float[] { 0, 0, 0, 0, 1, 1, 0, 1, 0,
										 0, 1, 1, 0, 0, 0, 0, 0, 1 };
		
		System.out.println("x axis");
		System.out.println(Arrays.toString(expected));
		System.out.println(Arrays.toString(result));
		
		assertEquals("got " + Arrays.toString(expected) + "\nexpected " + Arrays.toString(result), true, Arrays.equals(expected, result));
	}
	
	@Test
	public void makeQuad2fZAxis() {
		Vector3 a = new Vector3(0, 0, 0);
		Vector3 b = new Vector3(1, 1, 0);
		float[] result = Planes.makeQuad2f(a, b);
		float[] expected = new float[] { 0, 0, 0, 1, 1, 0, 0, 1, 0,
										 1, 1, 0, 0, 0, 0, 1, 0, 0 };
		
		System.out.println("z axis");
		System.out.println(Arrays.toString(expected));
		System.out.println(Arrays.toString(result));
		
		assertEquals("got " + Arrays.toString(expected) + "\nexpected " + Arrays.toString(result), true, Arrays.equals(expected, result));
	}
	
	@Test
	public void makeQuad2fYAxis() {
		Vector3 a = new Vector3(0, 0, 0);
		Vector3 b = new Vector3(1, 0, 1);
		float[] result = Planes.makeQuad2f(a, b);
		float[] expected = new float[] { 0, 0, 0, 1, 0, 1, 0, 0, 1,
										 1, 0, 1, 0, 0, 0, 1, 0, 0 };
		
		System.out.println("y axis");
		System.out.println(Arrays.toString(expected));
		System.out.println(Arrays.toString(result));
		
		assertEquals("got " + Arrays.toString(expected) + "\nexpected " + Arrays.toString(result), true, Arrays.equals(expected, result));
	}
	
	@Test
	public void makeQuad2fNonOrtho() {
		Vector3 a = new Vector3(0, 0, 0);
		Vector3 b = new Vector3(1, 1, 1);
		float[] result = Planes.makeQuad2f(a, b);
		float[] expected = new float[] { 0, 0, 0, 1, 1, 1, 0, 1, 0,
										 1, 1, 1, 0, 0, 0, 1, 0, 0 };
		
		System.out.println("non ortho");
		System.out.println(Arrays.toString(expected));
		System.out.println(Arrays.toString(result));
		
		assertEquals("got " + Arrays.toString(expected) + "\nexpected " + Arrays.toString(result), true, Arrays.equals(expected, result));
	}
}
