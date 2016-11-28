package test;

import fall2016.Image;
import fall2016.Pixel;

public class Test {

	public static void main(String[] args) {
		Pixel p1 = new Pixel(20, 1000 ,20);
	
		Pixel[][] d = new Pixel[2][2];
		
		Image image = new Image("something for metadata", 100, d);
		
		image.getMetadata();
		
		System.out.println(p1.grey());
	}

}
