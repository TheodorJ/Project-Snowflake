package proj.snowflake.src;

public class DeepChunk extends Chunk {
	public DeepChunk(int x, int y) {
		this.x = x;
		this.y = y;
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				blocks[i][j] = new StoneBlock();
			}
		}
		for(int i = 0; i < aquifersPerChunk; i++) {
			aquifers[i] = new AquiferStartingPoint(this);
		}
		for(int i = 0; i < coalVeinsPerChunk; i++) {
			coalVeins[i] = new CoalStartingPoint(this);
		}
	}
}
