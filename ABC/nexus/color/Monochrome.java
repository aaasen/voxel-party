package nexus.color;

import static org.lwjgl.opengl.GL11.glColor3f;

/**
 * A Colorist that uses only on given color
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

public class Monochrome extends Colorist {
	float red, green, blue;
	
	public Monochrome(float range, float offset, float red, float green, float blue) {
		super(range, offset);
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public void color(float value) {
		glColor3f(this.red, this.green, this.blue);
	}
}
