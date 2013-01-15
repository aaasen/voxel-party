package nexus.model.structs;

/**
 * A generic 3D point made of floats for OpenGL compatibility
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * @auhtor Dylan Swiggett <dylanswiggett@gmail.com>
 *
 */

public class Vector3 {
	public static final int NUM_ELEMENTS = 3;
	public float x, y, z;
	
	/**
	 * Constructs a Point
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3 add(Vector3 a) {
		return new Vector3(a.x + x, a.y + y, a.z + z);
	}
	
	public Vector3 subtract(Vector3 a) {
		return new Vector3(x - a.x, y - a.y, z - a.z);
	}
	
	public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float lengthSquared() {
        return x * x + y * y + z * z;
    }
	
	public Vector3 scale(float scalar) {
		return new Vector3(x * scalar, y * scalar, z * scalar); 
	}
	
    public Vector3 normalize() {
        return this.scale(1 / length());
    }

    public float dotProduct(Vector3 vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public Vector3 crossProduct(Vector3 vector) {
        return new Vector3(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
    }

    public Vector3 projection(Vector3 vector) {
        float length = vector.length();
        return vector.scale(dotProduct(vector) / (length * length));
    }

    public Vector3 perpendicular(Vector3 vector) {
        return this.subtract(projection(vector));
    }
	
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	public boolean equals(Vector3 a) {
		return this.x == a.x && this.y == a.y && this.z == a.z;
	}
	
	/**
	 * Converts a Vector into an array of floats
	 */
	public float[] toArray() {
		return new float[] { x, y, z };
	}
	
	/**
	 * Combines multiple Vectors into one array of floats
	 * 
	 * @param vectors
	 * @return combined array of floats
	 */
	public static float[] combine(Vector3[] vectors) {
		float[] combined = new float[vectors.length * Vector3.NUM_ELEMENTS];
		
		for (int i = 0; i < vectors.length; i++) {
			combined[Vector3.NUM_ELEMENTS * i] = vectors[i].x;
			combined[Vector3.NUM_ELEMENTS * i + 1] = vectors[i].y;
			combined[Vector3.NUM_ELEMENTS * i + 2] = vectors[i].z;
		}
		
		return combined;
	}
}
