package mvc;

/**
 * The Model which is responsible for holding all of the world's data
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 * 
 */

import java.util.ArrayList;
import main.Camera;
import main.Point;
import objects.Block;
import objects.Grid;
import objects.Terrain;

public class Model {
	public ArrayList<Block> blocks;
	public ArrayList<Grid> grids;
	public ArrayList<Terrain> terrains;
	Camera camera;
	boolean locked;
	
	/**
	 * Constructs an empty Model
	 */
	public Model() {
		this.blocks = new ArrayList<Block>();
		this.grids = new ArrayList<Grid>();
		this.terrains = new ArrayList<Terrain>();
		this.camera = new Camera(new Point(0.0f, 0.0f, -10.0f), 0.0f, 0.0f);
		this.locked = false;
	}
	
	/**
	 * Disables writing to the model
	 * 
	 * Obedient programs do not write to the model when it is locked (for thread safety)
	 */
	public void lock() {
		this.locked = true;
	}
	
	/**
	 * Enables writing to the model
	 */
	public void unlock() {
		this.locked = false;
	}
}
