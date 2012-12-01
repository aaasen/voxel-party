package mvc;

/**
 * The Model which is responsible for holding all of the world's data
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import java.util.HashMap;

import main.Camera;
import main.Point;
import world.Chunk;

public class Model {
	public HashMap<Float, Chunk> chunks;
	Camera camera;
	boolean locked;
	
	/**
	 * Constructs an empty Model
	 */
	public Model() {
		this.chunks = new HashMap<Float, Chunk>();
		this.camera = new Camera(new Point(0.0f, 8.0f, 0.0f), 0.0f, 0.0f);
		this.locked = false;
	}
	
	/**
	 * Disables writing to the model
	 * 
	 * Obedient programs do not write to the model when it is locked (for thread safety)
	 */
	public void lock() {
		this.locked = true;
	}
	
	/**
	 * Enables writing to the model
	 */
	public void unlock() {
		this.locked = false;
	}
}
