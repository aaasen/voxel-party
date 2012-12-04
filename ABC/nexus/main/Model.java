package nexus.main;

/**
 * The Model which is responsible for holding all of the world's data
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import nexus.model.renderable.Block;
import nexus.model.structs.Camera;
import nexus.model.structs.Chunk;
import nexus.model.structs.ChunkContainer;

public class Model implements Runnable {
	Thread modelThread;
	boolean stop = false;
	public ChunkContainer chunks;
	Camera camera;
	boolean locked;
	
	/**
	 * Constructs an empty Model
	 */
	public Model(Camera camera) {
		this.modelThread = new Thread(this, "model_thread");
		this.modelThread.start();
		
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
	
	/**
	 * Starts the Model and ticks about 60 times a second
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(1l);
		
			
			while (!this.stop) {
				tick();
				
				try {
					Thread.sleep(15l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		Chunk curChunk = chunks.getChunk(camera.eye.x, camera.eye.z);
		Block[] yBlocks = curChunk.blocks[(int) camera.eye.x % Chunk.WIDTH][(int) camera.eye.z % Chunk.WIDTH];
		Block curBlock = yBlocks[0];
		
		if (camera.eye.y - 1f < curBlock.a.y) {
			camera.eye.y = 2f;
			camera.update();
		}
		
		
	}
	
	public void stop() {
		this.stop = true;
	}
}
