package main;

import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Controller implements Runnable {
	Thread inputThread;
	boolean stop = false;
	Model model;
	View view;
	float sensitivity = 0.000001f;

	public Controller(Model model, View view) {
		this.inputThread = new Thread(this, "input_thread");
		this.inputThread.start();
		this.model = model;
		this.view = view;

		try {
			Mouse.create();
			Mouse.setGrabbed(true);
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!stop) {
			processInput();
		}
	}

	public void processInput() {

		while (Mouse.next()) {
			this.view.camera.focal.y -= (Mouse.getEventDY() * 0.003);
			this.view.camera.focal.x += (Mouse.getEventDX() * 0.003);
			
			System.out.println(Mouse.getEventDX() + " : " + Mouse.getEventDY());
		}
		
//		System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);


		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.view.camera.eye.z += this.sensitivity;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.view.camera.eye.x += this.sensitivity;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.view.camera.eye.z -= this.sensitivity;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.view.camera.eye.x -= this.sensitivity;			
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
			this.view.camera.eye.y -= this.sensitivity;			
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
			this.view.camera.eye.y += this.sensitivity;			
		}
	}

	public void stop() {
		this.stop = true;
		this.view.stop();
	}
}