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
			this.view.camera.pitch -= (Mouse.getEventDY() * 0.003);
			this.view.camera.yaw += (Mouse.getEventDX() * 0.003);
			this.view.camera.update();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.view.camera.eye.z += this.sensitivity;
			this.view.camera.update();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.view.camera.eye.x += this.sensitivity;
			this.view.camera.update();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.view.camera.eye.z -= this.sensitivity;
			this.view.camera.update();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.view.camera.eye.x -= this.sensitivity;	
			this.view.camera.update();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
			this.view.camera.eye.y -= this.sensitivity;
			this.view.camera.update();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
			this.view.camera.eye.y += this.sensitivity;
			this.view.camera.update();
		}
	}

	public void stop() {
		this.stop = true;
		this.view.stop();
	}
}
