package nexus.main;

/** 
 * Launcher for Nexus
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import nexus.model.structs.Camera;
import nexus.model.structs.Vector3;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Main {
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	public static final String WINDOW_TITLE = "Voxel Party";
	public static final int INIT_RENDER_DISTANCE = 3;
	public static final Vector3 INIT_CAMERA_POSITION = new Vector3(0.0f, 16.0f, 0.0f);
	public static final float INIT_SENSITIVITY = 0.3f;
	public static final boolean CAMERA_INVERT = true;

	public static void main(String[] argv) {
		
		// set opengl version
		PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAttributes = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(false);

		try {
			// initialize lwjgl
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(WINDOW_TITLE);
			Display.create(pixelFormat, contextAttributes);
			Keyboard.create();
			Mouse.create();

		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// start the nexus.mvc framework
		Model world = new Model(new Camera(INIT_CAMERA_POSITION, 0.0f, 0.0f, INIT_SENSITIVITY, CAMERA_INVERT));
		View view = new View(world, INIT_RENDER_DISTANCE);
		new Controller(world);
		
		view.init();
	}
}
