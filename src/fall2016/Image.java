package fall2016;

public class Image {
	
	private String metadata;
	private int maxRange;
	private Pixel[][] data;
	
	public Image(String metadata, int maxRange, Pixel[][] data){
		if(maxRange < 0){
			throw new IllegalArgumentException();
		}
		this.metadata = metadata;
		this.maxRange = maxRange;
		this.data = data.clone();
	}
	
	public String getMetadata(){
		return metadata;
	}
	
	public int getRange(){
		return maxRange;
	}
	
	public int getWidth(){
		return data[0].length;
	}

	public void flip(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Pixel getPixel(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
