package proj.snowflake.src.guimanagers;

import java.awt.Color;
import java.awt.Graphics;

import proj.snowflake.src.IconHolder;

public class BuildingMenu extends GUIComponent {
	//public int y;
	
	public BuildingMenu() {
		y = 100;
	}
	
	@Override
	public void draw(Graphics g, int width, int height) {
		g.setColor(new Color(1f, 1f, 1f, 0.75f));
		g.fillRect(0, height - y, width, y);
		//g.setColor(Color.YELLOW);
		//g.fillRect(10, height - 90, 80, 80);
		g.drawImage(IconHolder.houseIcon, 10, height - 90, null);
		//g.setColor(Color.CYAN);
		//g.fillRect(110, height - 90, 80, 80);
		g.drawImage(IconHolder.barnIcon, 110, height - 90, null);
		//g.setColor(Color.MAGENTA);
		//g.fillRect(210, height - 90, 80, 80);
		g.drawImage(IconHolder.factoryIcon, 210, height - 90, null);
		//g.setColor(Color.BLACK);
		//g.fillRect(310, height - 90, 80, 80);
		g.drawImage(IconHolder.labIcon, 310, height - 90, null);
		//g.setColor(Color.RED);
		//g.fillRect(410, height - 90, 80, 80);
		g.drawImage(IconHolder.solarIcon, 410, height - 90, null);
		
		g.setColor(Color.RED);
		g.fillRect(510, height - 90, 80, 80);
	}
}
