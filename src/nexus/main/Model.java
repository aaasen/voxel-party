package nexus.main;

/**
 * The Model which is responsible for holding all of the world's data
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import nexus.model.structs.Camera;
import nexus.model.structs.ChunkContainer;

public class Model implements Runnable {
	public static final long SLEEPTIME = 15l;
	
	Thread modelThread;
	boolean stop = false;
	boolean locked;
	
	public ChunkContainer chunks;
	public Camera camera;
	
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
		while (!this.stop) {
			tick();

			try {
				Thread.sleep(SLEEPTIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.exit(0);
			
	}
	
	/**
	 * Updates the Model each frame
	 */
	public void tick() {

	}
	
	/**
	 * Tells the Model to exit
	 */
	public void stop() {
		this.stop = true;
	}
}
