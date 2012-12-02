package main;

/** 
 * Launcher for Nexus
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import mvc.Controller;
import mvc.Model;
import mvc.View;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	
    public static void main(String[] argv) {
        try {
        	// initialize lwjgl
        	Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
        	Display.setTitle("Nexus");
        	Display.create();
        	Keyboard.create();
        	Mouse.create();
	    
        	// start the mvc framework
        	Model world = new Model();
            View view = new View(world, 6);
            @SuppressWarnings("unused")
            Controller conroller = new Controller(world);
            
            view.init();
        	
        } catch (LWJGLException e) {
        	e.printStackTrace();
        	System.exit(0);
        }
    }
}
