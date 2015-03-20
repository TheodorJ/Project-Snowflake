package proj.snowflake.src.guimanagers;

import java.awt.Color;
import java.awt.Graphics;

import proj.snowflake.src.buildings.Building;

public class BuildingUpgradeMenu extends GUIComponent {
	
	public int reference;
	
	public BuildingUpgradeMenu(int ref) {
		y = 300;
		reference = ref;
	}
	
	@Override
	public void draw(Graphics g, int width, int height) {
		g.setColor(new Color(1f, 1f, 1f, 0.75f));
		g.fillRect(width - y, 0, y, height);
		g.setColor(Color.ORANGE);
		g.fillRect(width - y, 0, y, 100);
		g.setColor(Color.BLACK);
		g.drawString("Remove", width - y, 10);
	}
}
