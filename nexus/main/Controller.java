package nexus.main;

/**
 * The Controller which handles input
 * 
 * Controller runs in a seperate thread from the View to ensure responsiveness
 * 
 * TODO: cap controller tick
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */



import nexus.model.renderable.Air;
import nexus.model.structs.Block;
import nexus.model.structs.Vector3;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Controller implements Runnable {
	Thread inputThread;
	boolean stop = false;
	Model model;
	float mouseSensitivity = 0.003f;

	/**
	 * Creates a Controller
	 * 
	 * @param model a Model for the Controller to manipulate
	 * @param view
	 */
	public Controller(Model model) {
		this.inputThread = new Thread(this, "input_thread");
		this.inputThread.start();
		this.model = model;

		try {
			Mouse.create();
			Mouse.setGrabbed(true);
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts the Controller
	 */
	@Override
	public void run() {
		while (!this.stop) {
			processInput();
			
			try {
				Thread.sleep(15l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.exit(0);
	}

	/**
	 * Processes input once
	 */
	public void processInput() {
		int dy = 0;
		int dx = 0;
		
		while (Mouse.next()) {
			dy += Mouse.getEventDY();
			dx += Mouse.getEventDX();
		}

		this.model.camera.pitch(dy * this.mouseSensitivity);
		this.model.camera.yaw(dx * this.mouseSensitivity);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.model.camera.forwards();
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.model.camera.backwards();
		}
		
		this.model.camera.update();
		
		if (Mouse.isButtonDown(0)) {
			for (float i = 0f; i <= 5f; i += 0.5f) {
				Vector3 b = model.camera.eye.add(model.camera.unitFocal.scale((float) i));
				Block block = model.chunks.getBlock(b);
				if (block.visible()) {
					model.chunks.setBlock(new Air(b, 1.0f));
					
					Block above = model.chunks.getBlock(new Vector3(b.x, b.y + 1, b.z));
					Block below = model.chunks.getBlock(new Vector3(b.x, b.y - 1, b.z));
					Block left = model.chunks.getBlock(new Vector3(b.x - 1, b.y, b.z));
					Block right = model.chunks.getBlock(new Vector3(b.x + 1, b.y, b.z));
					Block near = model.chunks.getBlock(new Vector3(b.x, b.y, b.z - 1));
					Block far = model.chunks.getBlock(new Vector3(b.x, b.y, b.z + 1));
				
					
					above.mask.bottom = true;
					above.mask.render = true;
					
					below.mask.top = true;
					below.mask.render = true;

					left.mask.right = true;
					left.mask.render = true;
					
					right.mask.left = true;
					right.mask.render = true;
					
					near.mask.far = true;
					near.mask.render = true;
					
					far.mask.near = true;
					far.mask.render = true;

					
					
					break;
				}
			}
		}
		
	}

	/**
	 * Stops the Controller
	 */
	public void stop() {
		this.stop = true;
	}
}
