package main;

import org.lwjgl.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class View implements Runnable {
	Thread viewThread;
	Model model;
	int width, height;
	
	public View(Model model, int width, int height) {
		this.viewThread = new Thread(this, "view_thread");
		this.model = model;
		this.width = width;
		this.height = height;
		this.viewThread.start();
	}
	
	@Override
    public void run() {
        try {
        	
        	Display.setDisplayMode(new DisplayMode(this.width, this.height));
        	Display.create();
	    
        } catch (LWJGLException e) {
        	e.printStackTrace();
        	System.exit(0);
        }

    	// init OpenGLs
    	glMatrixMode(GL_PROJECTION);
    	glLoadIdentity();
    	glOrtho(0, this.width, 0, this.height, 1, -1);
    	glMatrixMode(GL_MODELVIEW);
     
    	while (!Display.isCloseRequested()) {
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
}