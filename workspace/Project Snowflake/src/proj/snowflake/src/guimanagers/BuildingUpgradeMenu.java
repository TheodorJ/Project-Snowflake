package proj.snowflake.src.guimanagers;

import java.awt.Color;
import java.awt.Graphics;

import proj.snowflake.src.buildings.Building;

public class BuildingUpgradeMenu extends GUIComponent {
	
	public Building reference;
	
	public BuildingUpgradeMenu(Building ref) {
		y = 300;
		reference = ref;
	}
	
	@Override
	public void draw(Graphics g, int width, int height) {
		g.setColor(Color.blue);
		g.fillRect(width - y, 0, y, height);
	}
}
