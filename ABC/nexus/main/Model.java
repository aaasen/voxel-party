package nexus.main;

/**
 * The Model which is responsible for holding all of the world's data
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import nexus.model.renderable.Water;
import nexus.model.structs.Camera;
import nexus.model.structs.ChunkContainer;

public class Model {
	public ChunkContainer chunks;
	Camera camera;
	boolean locked;
	
	/**
	 * Constructs an empty Model
	 */
	public Model(Camera camera) {
		this.chunks = new ChunkContainer();
		this.camera = camera;
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
