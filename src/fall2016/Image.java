package fall2016;

public class Image {

	private String metadata;
	private int maxRange;
	private Pixel[][] data;

	public Image(String metadata, int maxRange, Pixel[][] data) {
		if (maxRange < 0) {
			throw new IllegalArgumentException();
		}
		this.metadata = metadata;
		this.maxRange = maxRange;
		this.data = copyImage(data);
	}

	private Pixel[][] copyImage(Pixel[][] data) {
		Pixel[][] dataCopy = new Pixel[data.length][];
		for (int i = 0; i < data.length; i++) {
			dataCopy[i] = new Pixel[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				dataCopy[i][j] = new Pixel(data[i][j].getRed(), data[i][j].getBlue(), data[i][j].getBlue());
			}
		}
		return dataCopy;
	}

	public String getMetadata() {
		return metadata;
	}

	public int getRange() {
		return maxRange;
	}

	public int getWidth() {
		return data[0].length;
	}

	public int getHeight() {
		return data.length;
	}

	public Pixel getPixel(int i, int j) {
		return data[i][j];
	}

	public void flip(boolean b) {
		Pixel[][] flippedImage = copyImage(data);
		// horizontally
		if (b) {
			for (int i = 0; i < flippedImage.length; i++) {
				for (int j = 0; j < flippedImage[i].length; j++) {
					flippedImage[i][j] = new Pixel(data[i][data[i].length - 1 - j].getRed(),
							data[i][data[i].length - 1 - j].getGreen(), data[i][data[i].length - 1 - j].getBlue());
				}
			}
		}
		// vertically
		else {
			for (int i = 0; i < flippedImage.length; i++) {
				for (int j = 0; j < flippedImage[i].length; j++) {
					flippedImage[i][j] = new Pixel(data[data.length - 1 - i][j].getRed(),
							data[data.length - 1 - i][j].getGreen(), data[data.length - 1 - i][j].getBlue());
				}
			}

		}
		data = flippedImage;
	}

	public void toGrey() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = new Pixel(data[i][j].grey());
			}
		}
	}

	public void crop(int startX, int startY, int endX, int endY) {
		Pixel[][] croppedImage = new Pixel[endX - startX][endY - startY];
		for (int i = 0; i < croppedImage.length; i++) {
			for (int j = 0; j < croppedImage[i].length; j++) {
				croppedImage[i][j] = data[startX + i][startY + j];
			}
		}
		data = croppedImage;
	}
}
