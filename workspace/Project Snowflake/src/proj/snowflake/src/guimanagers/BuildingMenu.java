package proj.snowflake.src.guimanagers;

import java.awt.Color;
import java.awt.Graphics;

public class BuildingMenu extends GUIComponent {
	//public int y;
	
	public BuildingMenu() {
		y = 100;
	}
	
	@Override
	public void draw(Graphics g, int width, int height) {
		g.setColor(Color.blue);
		g.fillRect(0, height - y, width, y);
		g.setColor(Color.YELLOW);
		g.fillRect(10, height - 90, 80, 80);
		g.setColor(Color.CYAN);
		g.fillRect(110, height - 90, 80, 80);
		g.setColor(Color.MAGENTA);
		g.fillRect(210, height - 90, 80, 80);
	}
}
