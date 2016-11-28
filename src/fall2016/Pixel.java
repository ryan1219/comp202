package fall2016;

public class Pixel {
	private int red;
	private int green;
	private int blue;

	public Pixel(int red, int green, int blue) {
		if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
			throw new IllegalArgumentException("pixel value out of range");
		}
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public Pixel(int intensity) {
		if (intensity < 0 || intensity > 255) {
			throw new IllegalArgumentException("pixel value out of range");
		}
		this.red = intensity;
		this.green = intensity;
		this.blue = intensity;
	}

	public int grey() {
		return (red + green + blue) / 3;
	}

}
