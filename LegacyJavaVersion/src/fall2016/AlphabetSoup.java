package fall2016;

public class AlphabetSoup {

	public static void main(String[] args) {
		if(args.length < 2){
			throw new IllegalArgumentException("args is less than two");
		}else if(args[0].length() != 1){
			throw new IllegalArgumentException("First String in args is not a single character");
		}
		
		char key = args[0].charAt(0);
		String[] stringList = copySubArray(args, 1, args.length);
		if(haveChar(stringList, key)){
			System.out.println("The letter "+ key+ " is present in all of the Strings");
		}else{
			System.out.println("Then letter " +  key + " is not present in all of the Strings");
		}
	}

	public static int countChar(String str, char key) {
		int counter = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == key) {
				counter++;
			}
		}
		return counter;
	}

	public static boolean haveChar(String[] strings, char key) {
		for (int i = 0; i < strings.length; i++) {
			if (countChar(strings[i], key) == 0) {
				return false;
			}
		}
		return true;
	}

	public static String[] copySubArray(String[] original, int startIndex, int endIndex) {
		String[] subArray = new String[endIndex - startIndex + 1];
		int indexOfSubArray = 0;
		for (int i = startIndex; i < endIndex; i++) {
			subArray[indexOfSubArray] = original[i];
			indexOfSubArray++;
		}
		return subArray;
	}
}
