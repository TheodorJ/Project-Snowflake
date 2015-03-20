package proj.snowflake.src.buildings;

public class WaterTurtle extends MiningTurtle {
	
	public int rightIndex = 1;
	public int leftIndex = 1;
	
	public long lastMine;
	
	public WaterTurtle(int X, int Y) {
		super(X, Y);
		lastMine = System.currentTimeMillis();
	}
}
