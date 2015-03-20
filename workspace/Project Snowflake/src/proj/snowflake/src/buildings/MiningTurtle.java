package proj.snowflake.src.buildings;

public class MiningTurtle extends Building {
	
	public int rightIndex = 1;
	public int leftIndex = 1;
	
	public long lastMine;
	
	public MiningTurtle(int X, int Y) {
		lastMine = System.currentTimeMillis();
		x = X;
		y = Y;
	}
}
