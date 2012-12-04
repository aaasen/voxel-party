package nexus.model.renderable;

import nexus.model.structs.Camera;
import nexus.model.structs.Vector3;

public class Player {
	public Camera camera;
	public float height = 1.6f;
	
	public Player(Vector3 eye, float pitch, float yaw, float sensitivity, boolean invert) {
		eye.y += height;
		this.camera = new Camera(eye, pitch, yaw, sensitivity, invert);
	}

}
