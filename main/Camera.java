package main;

public class Camera {
	Point eye;
	float pitch, yaw;
	Point focal;
	
	public Camera(Point eye, float pitch, float yaw) {
		this.eye = eye;
		this.pitch = pitch;
		this.yaw = yaw;
		this.focal = new Point(0.0f, 0.0f, 0.0f);
	}
	
	public Camera(float ex, float ey, float ez, float pitch, float yaw) {
		this.eye = new Point(ex, ey, ez);
		this.pitch = pitch;
		this.yaw = yaw;
		this.focal = new Point(0.0f, 0.0f, 0.0f);
	}
	
	private Point unitLookAt() {
		return new Point((float) Math.cos(this.yaw), 0.0f, (float) Math.sin(this.yaw));
	}
	
	public Point lookAt() {
		Point unit = unitLookAt();
		return new Point(this.eye.x + unit.x, this.eye.y + unit.y, this.eye.z + unit.z);
	}
	
	public void update() {
		this.focal = lookAt();
	}
}
