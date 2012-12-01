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
import objects.Terrain;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import color.Greyscale;

public class Main {
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	
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
            View view = new View(world);
            @SuppressWarnings("unused")
            Controller conroller = new Controller(world);
            
            // populate the world
            world.terrains.add(new Terrain(256, 256, new Greyscale(12.0f, 0.0f)));
            world.terrains.get(0).draw();
            
            view.init();
        	
        } catch (LWJGLException e) {
        	e.printStackTrace();
        	System.exit(0);
        }
    }
}
