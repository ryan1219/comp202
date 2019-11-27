package fall2016;

import java.util.Random;

public class BirthdayParadox {

	public static void main(String[] args) {
		for(int i = 1; i < 101; i++){
			System.out.println(i + " " + runExperiment(i));
		}
	}

	public static double runExperiment(int size) throws IllegalArgumentException {
		int iterations = 200;
		int range = 365;
		int duplicatedArrays = 0;

		if (size < 1) {
			throw new IllegalArgumentException();
		}
		int[][] dates = generateAllData(iterations, size, range);
		for (int i = 0; i < dates.length; i++) {
			if (hasDuplicates(dates[i])) {
				duplicatedArrays++;
			}
		}

		return (double) duplicatedArrays / (double) iterations;

	}

	// try to use q3 method
	public static int maxDay(int[][] dataTable) {
		int mode = 0;
		int frequencyOfMode = 0;

		for (int i = 0; i < dataTable.length; i++) {
			for (int j = 0; j < dataTable[i].length; j++) {
				if (countElement(dataTable, dataTable[i][j]) > frequencyOfMode) {
					mode = dataTable[i][j];
					frequencyOfMode = countElement(dataTable, dataTable[i][j]);
				}
			}
		}
		return mode;
	}

	public static boolean hasDuplicates(int[] input) {
		int[][] dataTable = new int[2][];
		dataTable[0] = input.clone();
		dataTable[1] = input.clone();
		
		int mode = maxDay(dataTable);
		if(countElement(dataTable, mode) > 2){
			return true;
		}else{
			return false;
		}
		
	}

	// not using q3 method
	// public static int maxDay(int[][] dataTable) {
	// int frequecny = 0;
	// int mode = 0;
	//
	// for (int i = 0; i < dataTable.length; i++) {
	// for (int j = 0; j < dataTable[i].length; j++) {
	// if (countElement(dataTable, dataTable[i][j]) > frequecny) {
	// mode = dataTable[i][j];
	// }
	// }
	// }
	// return mode;
	// }

//	public static boolean hasDuplicates(int[] input) {
//		int[][] dataTable = new int[2][];
//		dataTable[0] = input.clone();
//		dataTable[1] = input.clone();
//
//		for (int i = 0; i < dataTable[0].length; i++) {
//			if (countElement(dataTable, dataTable[0][i]) != 2) {
//				return true;
//			}
//		}
//		return false;
//	}

	public static int countElement(int[][] dataTable, int element) {
		int counter = 0;
		for (int i = 0; i < dataTable.length; i++) {
			for (int j = 0; j < dataTable[i].length; j++) {
				if (dataTable[i][j] == element) {
					counter++;
				}
			}
		}
		return counter;
	}

	public static int[][] generateAllData(int iterations, int size, int range) {
		int[][] allData = new int[iterations][size];
		for (int i = 0; i < iterations; i++) {
			allData[i] = generateArray(size, range);
		}
		return allData;
	}

	public static int[] generateArray(int size, int range) {
		int[] arrayToReturn = new int[size];
		Random rand = new Random();
		for (int i = 0; i < arrayToReturn.length; i++) {
			arrayToReturn[i] = rand.nextInt(range);
		}
		return arrayToReturn;
	}

}
