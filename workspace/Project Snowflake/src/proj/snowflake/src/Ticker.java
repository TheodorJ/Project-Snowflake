package proj.snowflake.src;

public class Ticker implements Runnable {
	
	Main main;
	
	public long lastTick;
	
	public Ticker(Main m) {
		main = m;
	}
	
	public void tick() {
		long yolo1 = System.currentTimeMillis() - lastTick;
        lastTick = System.currentTimeMillis();
    }
	
	public void run() {
		while(true) {
			//tick();
		}
	}
}
