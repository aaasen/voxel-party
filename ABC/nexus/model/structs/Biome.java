package nexus.model.structs;

import nexus.view.color.Colorist;

/**
 * Biomes define y dilation and Color for Terrain
 * 
 * @author Lane Aasen <laneaasen@gmail.com>
 *
 */

public class Biome {
	public float yDilation;
	public Colorist colorist;
	
	public Biome(float yDilation, Colorist colorist) {
		this.yDilation = yDilation;
		this.colorist = colorist;
	}
}
