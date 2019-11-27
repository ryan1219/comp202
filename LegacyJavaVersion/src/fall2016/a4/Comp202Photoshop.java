package fall2016.a4;

import java.io.IOException;

public class Comp202Photoshop {

	public static void main(String[] args) {
		if(args.length < 4){
			System.out.println("ERROR: user input format error.");
			System.exit(0);
		}else if(!args[2].contains("pgm") && !args[2].contains("pgm")){
			System.out.println("ERROR: output format invalid");
			System.exit(0);
		}else if(!args[3].equals("-fh")&&!args[3].equals("-fv")&&!args[3].equals("-gs")&&!args[3].equals("-cr") ){
			System.out.println("ERROR: requested operation does not exist.");
			System.exit(0);
		}
		try {
			Image inputImage = ImageFileUtilities.read(args[0]);
			if(args[3].equals("-fh")){
				inputImage.flip(true);
			}else if(args[3].equals("-fv")){
				inputImage.flip(false);
			}else if(args[3].equals("-gs")){
				inputImage.toGrey();
			}else if(args[3].equals("-cr")){
				if(args.length != 8){
					System.out.println("ERROR: cannot perform -cr, missing arguments");
					System.exit(0);
				}
				inputImage.crop(Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]));
			}
			
			if(args[2].equals("pgm")){
				ImageFileUtilities.writePgm(inputImage, args[1]);
			}else{
				ImageFileUtilities.writePnm(inputImage, args[1]);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
	}

}
