package main;

import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;

public class View {
	Model model;
	int width, height;
	boolean stop = false;

	public View(Model model, int width, int height) {
		this.model = model;
		this.width = width;
		this.height = height;
	}

	public void init() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, this.width, 0, this.height, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	
		this.run();
	}

	public void run() {
		
		while (!Display.isCloseRequested() && !this.stop) {
			Display.sync(60);
			this.render();
		}

		Display.destroy();
	}

	public void render() {
		if(!model.locked) {
			// Clear the screen and depth buffer
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	

			model.player.draw();

			Display.update();
		}
	}

	public void stop() {
		this.stop = true;
	}
}