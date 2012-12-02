package main;

/**
 * A Camera that defines a viewpoint
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */
public class Camera {
	public Point eye;
	public float pitch, yaw;
	public Point focal;
	public boolean invert;
	public float sensitivity;
	
	/**
	 * Creates a Camera
	 * 
	 * @param eye position of the viewer
	 * @param pitch rotation around the x axis
	 * @param yaw rotation around the y axis
	 */
	public Camera(Point eye, float pitch, float yaw, float sensitivity, boolean invert) {
		this.eye = eye;
		this.pitch = pitch;
		this.yaw = yaw;
		this.sensitivity = sensitivity;
		this.invert = invert;
		this.focal = new Point(0.0f, 0.0f, 0.0f);
	}
	
	/**
	 * Calculates the point on the unit sphere that the Camera is pointing at
	 * 
	 * @return a point on the unit sphere which the Camera is pointing at
	 */
	private Point unitLookAt() {
		return new Point((float) Math.cos(this.yaw), (float) Math.tan((invert ? 1.0f : -1.0f) * this.pitch), (float) Math.sin(this.yaw));
	}
	
	/**
	 * Calculates an absolute point that the camera should point at
	 * 
	 * @see unitLookAt()
	 * @return an absolute point that the camera should look at
	 */
	public Point lookAt() {
		Point unit = unitLookAt();
		return new Point(this.eye.x + unit.x, this.eye.y + unit.y, this.eye.z + unit.z);
	}
	
	/**
	 * Updates the Camera's focal point
	 */
	public void update() {
		this.focal = lookAt();
	}
	
	/**
	 * Sets for Camera's pitch and limits it to a reasonable range
	 * 
	 * @param a
	 */
	public void pitch(float a) {
		this.pitch += a;
		
		if (this.pitch > 1.0f) {
			this.pitch = 1.0f;
		} else if (this.pitch < -1.0f) {
			this.pitch = -1.0f;
		}
	}
	
	/**
	 * Sets the Camera's yaw
	 * 
	 * Does nothing special; it's only here for consistency with pitch()
	 * 
	 * @param a
	 */
	public void yaw(float a) {
		this.yaw += a;
	}
	
	/**
	 * Moves the camera towards where it is pointing
	 */
	public void forwards() {
		this.eye = Point.add(Point.scale(this.unitLookAt(), this.sensitivity), this.eye);
	}
	
	/**
	 * Moves the camera away from where it is pointing
	 */
	public void backwards() {
		this.eye = Point.add(Point.scale(this.unitLookAt(), -this.sensitivity), this.eye);
	}
}
