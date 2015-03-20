package proj.snowflake.src.buildings;

public class PowerPlant extends Building {
	
	public long lastTake;
	
	public long lastMine;
	
	public int mineIndex = 0;
	
	public PowerPlant() {
		lastTake = System.currentTimeMillis();
		lastMine = System.currentTimeMillis();
	}
}
