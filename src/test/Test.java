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
//			Image test = ImageFileUtilities.read("C:\\Users\\Administrator\\Desktop\\catskypegrey.pgm");
			Image test = ImageFileUtilities.read("C:\\Users\\Administrator\\Desktop\\catskype.pnm");
//			test.flip(false);
			test.crop(0, 0, 100, 100);
			ImageFileUtilities.writePnm(test, "C:\\Users\\Administrator\\Desktop\\new_catskype.pnm");
//			ImageFileUtilities.writePgm(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
