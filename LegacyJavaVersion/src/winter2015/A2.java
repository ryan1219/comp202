package winter2015;

import java.util.Scanner;

public class A2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		while(true){
			
			System.out.println("Enter an input: ");
			String inputString = scan.nextLine();
			
			if(validInput(inputString) == true){
				System.out.println("Input " +inputString+ " convert to "+ convert(inputString)+" in base 10");
			}else{
				System.out.println("Enter again!");
			}
			
		}
	}
	public static int convert(String a){
		int sum = 0;
		for(int i = 0; i <a.length(); i++){
			if(a.charAt(i) == '0'){
				sum = sum + 0*(int)Math.pow(2, a.length()-1-i);
			}else{
				sum = sum + 1*(int)Math.pow(2, a.length()-1-i);
			}
		}
		return sum;
	}
	public static boolean validInput(String a){
		for(int i = 0; i < a.length(); i ++){
			if(a.charAt(i) != '0' && a.charAt(i) != '1'){
				return false;
			}
		}
		return true;
	}
}
