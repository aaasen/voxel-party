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
import nexus.model.renderable.Solid;
import nexus.model.structs.Block;
import nexus.model.structs.Vector3;
import nexus.view.color.Greyscale;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Controller implements Runnable {
	public static final float MAX_SELECT_DISTANCE = 5f;
	
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
		
		for (float i = 0f; i < MAX_SELECT_DISTANCE; i += 0.1f) {
			Vector3 target = model.camera.eye.add(model.camera.unitFocal.scale((float) i));
			target = new Vector3((int) target.x, (int) target.y, (int) target.z);
			
			if (model.chunks.inBounds(target) && model.chunks.getBlock(target).visible()) {
				if (target != model.chunks.selected) {
					model.chunks.getBlockRound(model.chunks.selected).mask.outline = false;
					model.chunks.getBlockRound(target).mask.outline = true;
					model.chunks.selected = target;
				}
				
				if (Mouse.isButtonDown(0)) {
					model.chunks.setBlock(new Air(model.chunks.selected, 1.0f));
				}
				
				if (Mouse.isButtonDown(1)) {
					Block newBlock = new Solid(model.chunks.selected.add(new Vector3(0f, 1f, 0f)), 1.0f, new Greyscale(16.0f, 0.0f));
					model.chunks.setBlock(newBlock);
				}
				
				break;
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
