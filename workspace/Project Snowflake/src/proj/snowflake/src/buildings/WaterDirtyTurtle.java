package proj.snowflake.src.buildings;

public class WaterDirtyTurtle extends Building {
	
	public int rightIndex = 1;
	public int leftIndex = 1;
	
	public long lastMine;
	
	public WaterDirtyTurtle(int X, int Y) {
		lastMine = System.currentTimeMillis();
		x = X;
		y = Y;
	}
}
