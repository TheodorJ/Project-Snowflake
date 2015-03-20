package proj.snowflake.src.buildings;

public class House extends Building {
	
	public long lastTick;
	
	public long lastMine;
	
	public int mineIndex = 0;
	public boolean watFound = false;
	
	public House() {
		lastTick = System.currentTimeMillis();
		lastMine = System.currentTimeMillis();
	}
}
