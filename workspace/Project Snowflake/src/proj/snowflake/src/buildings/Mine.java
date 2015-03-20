package proj.snowflake.src.buildings;

public class Mine extends Building {
	public long lastMine;
	
	public long lastCrop;
	
	public int mineIndex = 0;
	
	public boolean wat = false;
	
	public Mine() {
		lastMine = System.currentTimeMillis();
		lastCrop = System.currentTimeMillis();
	}
}
