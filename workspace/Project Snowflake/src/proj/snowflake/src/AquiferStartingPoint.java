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
		this.topRad = (int) (Math.random()) + 10;
		this.bottomRad = (int) (Math.random()*5) + 10;
		this.x = (int) (Math.random() * (125 - topRad*2) + topRad);
		this.y = (int) (Math.random() * (100 - topRad*2) + topRad);
		this.x -= this.x%5;
		this.y -= this.y%5;
		this.homeChunk = chunk;
		
		/*for(int i = (x - bottomRad); i <= (x + bottomRad); i++) {
			for(int j = (y - topRad); j <= (y + topRad); j++) {
				try {
					if(homeChunk.blocks[i][j] != null) {
						homeChunk.blocks[i][j] = new AquiferBlock();
					}
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		}*/
		for(int i = (x - 2); i <= (x + 3); i++) {
			for(int j = (y - 2); j <= (y + 3); j++) {
				try {
					if(homeChunk.blocks[i][j] != null) {
						homeChunk.blocks[i][j] = new AquiferBlock();
					}
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
		if(Math.random() >= .5) {
			for(int i = (x - 2); i <= (x + 13); i++) {
				for(int j = (y + 3); j <= (y + 8); j++) {
					try {
						if(homeChunk.blocks[i][j] != null) {
							homeChunk.blocks[i][j] = new AquiferBlock();
						}
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
			}
			for(int i = (x + 3); i <= (x + 13); i++) {
				for(int j = (y - 2); j <= (y + 3); j++) {
					try {
						if(homeChunk.blocks[i][j] != null) {
							homeChunk.blocks[i][j] = new AquiferBlock();
						}
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
			}
		}
	}
}
