package test;

import java.io.IOException;

import fall2016.Image;
import fall2016.ImageFileUtilities;
import fall2016.Pixel;

public class Test {

	public static void main(String[] args) {
//		Pixel p1 = new Pixel(20, 100 ,20);
//	
//		Pixel[][] d = new Pixel[2][2];
//		
//		Image image = new Image("something for metadata", 100, d);
//		
//		image.getMetadata();
//		
//		System.out.println(p1.grey());
		
		try {
//			ImageFileUtilities.read("C:\\Users\\Administrator\\Desktop\\catskypegrey.pgm");
			ImageFileUtilities.read("C:\\Users\\Administrator\\Desktop\\catskype.pnm");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
