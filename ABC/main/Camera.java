package main;

public class Camera {
	Point eye;
	Point focal;
	
	public Camera(Point eye, Point focal) {
		this.eye = eye;
		this.focal = focal;
	}
	
	public Camera(float ex, float ey, float ez, float lx, float ly, float lz) {
		this.eye = new Point(ex, ey, ez);
		this.focal = new Point(lx, ly, lz);
	}
}
