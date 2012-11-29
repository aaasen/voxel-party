package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {
    public static void main(String[] argv) {
    	Model world = new Model();
    	Player p1 = new Player(50, 50);
    	Player p2 = new Player(100, 100);
    	world.addPlayer(p1);
    	world.addPlayer(p2);
    	
        try {
        	
        	Display.setDisplayMode(new DisplayMode(800, 600));
        	Display.create();
        	Keyboard.create();
        	Mouse.create();
	    
        } catch (LWJGLException e) {
        	e.printStackTrace();
        	System.exit(0);
        }
    	
        View view = new View(world, 800, 600);
        Controller controller = new Controller(world, view);
        
        view.run();
    }
}
