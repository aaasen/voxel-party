package main;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
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
	
    public void run() {
    	// init OpenGLs
    	glMatrixMode(GL_PROJECTION);
    	glLoadIdentity();
    	glOrtho(0, this.width, 0, this.height, 1, -1);
    	glMatrixMode(GL_MODELVIEW);
     
    	while (!Display.isCloseRequested() && !this.stop) {
    		if(!model.locked) {
    			// Clear the screen and depth buffer
    			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	

    			for (Player player : model.players) {
    				player.draw();
    			}

    			Display.update();
    		}
    	}
     
    	Display.destroy();
	}
    
    public void stop() {
    	this.stop = true;
    }
}