package proj.snowflake.src;

public class AquiferStartingPoint {
	public int topRad;
	public int bottomRad;
	
	public int x;
	public int y;
	
	public Chunk homeChunk;
	
	public int getDistance(int x1, int y1, int x2, int y2) {
		return (int) (Math.sqrt(((x2 - x1)*(x2 - x1))+((y2 - y1)*(y2 - y1))));
	}
	
	public AquiferStartingPoint(Chunk chunk, int x, int y, int tr, int br) {
		this.topRad = tr;
		this.bottomRad = br;
		this.x = x;
		this.y = y;
		this.homeChunk = chunk;
	}
	
	public AquiferStartingPoint(Chunk chunk) {
		this.topRad = (int) (Math.random() * 20);
		this.bottomRad = (int) (Math.random() * 15);
		this.x = (int) (Math.random() * (300 - topRad*2) + topRad);
		this.y = (int) (Math.random() * (100 - topRad*2) + topRad);
		this.homeChunk = chunk;
		
		for(int i = (x - topRad); i <= (x + topRad); i++) {
			for(int j = (y - topRad); j <= (y + topRad); j++) {
				try {
					if(homeChunk.blocks[i][j] != null && getDistance(x, y, i, j) <= (topRad + .5)) {
						homeChunk.blocks[i][j] = new AquiferBlock();
					}
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
	}
}