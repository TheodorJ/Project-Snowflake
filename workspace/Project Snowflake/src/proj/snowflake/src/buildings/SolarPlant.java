package proj.snowflake.src.buildings;

public class SolarPlant extends Building {
	public long lastTake;
	
	public SolarPlant() {
		lastTake = System.currentTimeMillis();
	}
}
