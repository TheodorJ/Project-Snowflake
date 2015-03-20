package proj.snowflake.src.buildings;

import proj.snowflake.src.Main;

public class ScienceFacility extends Building {
	
	public long lastScienceUpgrade;
	
	public ScienceFacility() {
		lastScienceUpgrade = System.currentTimeMillis();
	}
}
