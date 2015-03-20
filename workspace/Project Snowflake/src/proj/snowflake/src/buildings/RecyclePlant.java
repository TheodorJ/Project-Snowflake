package proj.snowflake.src.buildings;

public class RecyclePlant extends Building {
public long lastTake;
	
	public RecyclePlant() {
		lastTake = System.currentTimeMillis();
	}
}
