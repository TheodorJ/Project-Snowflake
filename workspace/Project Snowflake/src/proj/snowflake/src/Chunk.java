package proj.snowflake.src;

public class Chunk {
	
	Block[][] blocks = new Block[125][100];
	
	public int dirtLevel = 30;
	public int stoneLevel = dirtLevel + (int) (Math.random() * 10 + 3);
	public int mountainLoc = (int) (Math.random() * 30) + 20;
	public int maxMountainHeight = 25;
	public int aquifersPerChunk = 10;
	public int coalVeinsPerChunk = 13;
	
	public AquiferStartingPoint[] aquifers = new AquiferStartingPoint[aquifersPerChunk];
	public CoalStartingPoint[] coalVeins = new CoalStartingPoint[coalVeinsPerChunk];
	
	public int x = 0;
	public int y = 0;
	
	public Chunk() {
		
	}
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks[0].length; j++) {
				if(j > dirtLevel && j <= stoneLevel) {
					/*if(Math.random() >= 0.5) {
						blocks[i][j] = new DirtBlock();
					}
					else {
						blocks[i][j] = new LimestoneBlock();
					}*/
					if(j == dirtLevel + 1) {
						blocks[i][j] = new LimestoneBlock();
					}
					else if(j == dirtLevel + 2) {
						if(Math.random() >= .5) {
							blocks[i][j] = new LimestoneBlock();
						}
						else {
							blocks[i][j] = new DirtBlock();
						}
					}
					else {
						blocks[i][j] = new DirtBlock();
					}
				}
				else if(j > stoneLevel) {
					blocks[i][j] = new StoneBlock();
				}
				else {
					blocks[i][j] = null;
				}
			}
		}
		
		for(int i = 0; i < aquifersPerChunk; i++) {
			aquifers[i] = new AquiferStartingPoint(this);
		}
		
		for(int i = 0; i < coalVeinsPerChunk; i++) {
			coalVeins[i] = new CoalStartingPoint(this);
		}
		int mountainHeight = (int) (Math.random() * (maxMountainHeight)) + 10;
		//int leftWidth = (int) (Math.random() * (maxMountainHeight*55 - mountainHeight));
		//int rightWidth = (int) (Math.random() * (maxMountainHeight*55 - mountainHeight));
		int leftWidth = 20;
		int rightWidth = 20;
		//for(int i = 0; i <= mountainHeight; i++) {
			//blocks[mountainLoc][dirtLevel - i] = new DirtBlock();
		//}
		/*for(int i = mountainLoc - leftWidth; i <= mountainLoc + rightWidth; i++) {
			if(i < mountainLoc) {
				double slop = 1;
				int iterations = (int) (slop * i);
				for(int j = 0; j <= iterations; j++) {
					blocks[i][dirtLevel - j] = new DirtBlock();
				}
			}
			else if(i == mountainLoc) {
				for(int j = 0; j <= mountainHeight; j++) {
					blocks[mountainLoc][dirtLevel - j] = new DirtBlock();
				}
			}
			else {
				/*double slope = mountainHeight/rightWidth;
				int iterations = (int) ((i - (rightWidth - mountainLoc)) * slope);
				for(int j = 0; j < iterations; j++) {
					blocks[i][dirtLevel - j] = new DirtBlock();
				}
			}
		}*/
	}
}