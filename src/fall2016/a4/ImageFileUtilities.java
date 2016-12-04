package fall2016.a4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ImageFileUtilities {
	
	public static void writePgm(Image image, String fileName) throws IOException {
//		String fileLocation = "C:\\Users\\Administrator\\Desktop\\new_catskypegrey.pgm";
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
//		System.out.println("write image first pixel point green:" + image.getPixel(0, 0).getGreen());
		bw.write("P2\n");
		bw.write(image.getMetadata() + "\n");
		bw.write(image.getWidth() + " " + image.getHeight() + "\n");
		bw.write(image.getRange() + "\n");
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				Pixel pixelToWrite = image.getPixel(i, j);
				bw.write(pixelToWrite.grey() + "\n");
			}
		}
		bw.close();
	}
	
	public static void writePnm(Image image, String fileName) throws IOException {
//		String fileLocation = "C:\\Users\\Administrator\\Desktop\\new_catskype.pnm";
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
//		System.out.println("write image first pixel point green:" + image.getPixel(0, 0).getGreen());
		bw.write("P3\n");
		bw.write(image.getMetadata() + "\n");
		bw.write(image.getWidth() + " " + image.getHeight() + "\n");
		bw.write(image.getRange() + "\n");
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				Pixel pixelToWrite = image.getPixel(i, j);
				bw.write(pixelToWrite.getRed() + "\n");
				bw.write(pixelToWrite.getGreen() + "\n");
				bw.write(pixelToWrite.getBlue() + "\n");
			}
		}
		bw.close();
	}

	public static Image read(String fileName) throws IOException {
		String metadata = "";
		int maxRange = 0;
		String width = null, height = null;
		Pixel[][] data = null;
		Scanner sc = new Scanner(new File(fileName));
		int i = 0;
		int j = 0;

		String fileType = sc.next();

		if (fileType.contains("P2")) {
			while (sc.hasNext()) {
				if (sc.hasNext("#")) {
					metadata = metadata + sc.nextLine();
					System.out.println(metadata);
				} else {
					if (width == null) {
						width = sc.next();
						System.out.println("width: " + width);
					} else if (height == null) {
						height = sc.next();
						System.out.println("height: " + height);
						data = new Pixel[Integer.parseInt(height)][Integer.parseInt(width)];
					} else if (maxRange == 0) {
						maxRange = sc.nextInt();
						System.out.println("maxRange: " + maxRange);
					} else {
						data[i][j] = new Pixel(sc.nextInt());
						// System.out.println(sc.nextInt());
						if (j < Integer.parseInt(width) - 1) {
							j++;
						} else {
							j = 0;
							i++;
						}
					}
				}
			}
		} else if (fileType.contains("P3")) {
			while (sc.hasNext()) {
				if (sc.hasNext("#")) {
					metadata = metadata + sc.nextLine();
					System.out.println(metadata);
				} else {
					if (width == null) {
						width = sc.next();
						System.out.println("width: " + width);
					} else if (height == null) {
						height = sc.next();
						System.out.println("height: " + height);
						data = new Pixel[Integer.parseInt(height)][Integer.parseInt(width)];
					} else if (maxRange == 0) {
						maxRange = sc.nextInt();
						System.out.println("maxRange: " + maxRange);
					} else {
						data[i][j] = new Pixel(sc.nextInt(), sc.nextInt(), sc.nextInt());
						// System.out.println(sc.nextInt());
						if (j < Integer.parseInt(width) - 1) {
							j++;
						} else {
							j = 0;
							i++;
						}
					}
				}
			}
		} else {
			sc.close();
			throw new IOException("wrong file type");
		}

		// System.out.println("first pixel green point: " + data[0][0].getGreen());
		// System.out.println(data[Integer.parseInt(height)-1][Integer.parseInt(width)-1].getRed());
		// System.out.println(data[Integer.parseInt(height)-1][Integer.parseInt(width)-1].getGreen());
		// System.out.println(data[Integer.parseInt(height)-1][Integer.parseInt(width)-1].getBlue());
		sc.close();
		return new Image(metadata, maxRange, data);
	}
}
