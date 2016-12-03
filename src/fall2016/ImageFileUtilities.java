package fall2016;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ImageFileUtilities {
	public static Image read(String fileName) throws IOException{
		String metadata = "";
		int maxRange = 0;
		String width = null, height = null;
		Pixel[][] data = null;
		Scanner sc = new Scanner(new File(fileName));
		int totalPixelPoints = 0;
		int counter = 0;
		int i = 0;
		int j = 0;
		
		String fileType = sc.next();
		if(fileType.contains("P2")){
			while(sc.hasNext()){
				if(sc.hasNext("#")){
					metadata = metadata + sc.nextLine();
					System.out.println(metadata);
				}else{
					if(width == null){
						width = sc.next();
						System.out.println("width: "+width);
					}else if(height == null){
						height = sc.next();
						System.out.println("height: "+height);
						data = new Pixel[Integer.parseInt(height)][Integer.parseInt(width)];
						totalPixelPoints = Integer.parseInt(height) * Integer.parseInt(width);
					}else if(maxRange == 0){
						maxRange = sc.nextInt();
						System.out.println("maxRange: "+ maxRange);
					}
					else{
						data[i][j] = new Pixel(sc.nextInt());
//						System.out.println(sc.nextInt());
						if(j < Integer.parseInt(width)-1){
							j++;
						}else{
							j = 0;
							i++;
						}
					}
				}
			}
		}
		else if(fileType.contains("P3")){
			while(sc.hasNext()){
				if(sc.hasNext("#")){
					metadata = metadata + sc.nextLine();
					System.out.println(metadata);
				}else{
					if(width == null){
						width = sc.next();
						System.out.println("width: "+width);
					}else if(height == null){
						height = sc.next();
						System.out.println("height: "+height);
						data = new Pixel[Integer.parseInt(height)][Integer.parseInt(width)];
						totalPixelPoints = Integer.parseInt(height) * Integer.parseInt(width);
					}else if(maxRange == 0){
						maxRange = sc.nextInt();
						System.out.println("maxRange: "+ maxRange);
					}
					else{
						data[i][j] = new Pixel(sc.nextInt(), sc.nextInt(), sc.nextInt());
//						System.out.println(sc.nextInt());
						if(j < Integer.parseInt(width)-1){
							j++;
						}else{
							j = 0;
							i++;
						}
					}
				}
			}
		}else{
			throw new IOException("wrong file type");
		}
		
		System.out.println(data[0][0].getRed());
		System.out.println(data[Integer.parseInt(height)-1][Integer.parseInt(width)-1].getRed());
		System.out.println(data[Integer.parseInt(height)-1][Integer.parseInt(width)-1].getGreen());
		System.out.println(data[Integer.parseInt(height)-1][Integer.parseInt(width)-1].getBlue());
		
		return new Image(metadata, maxRange, data);
	}
}
