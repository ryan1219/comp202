package fall2016;

import java.util.Scanner;

public class WeatherUtilities {
	public static void main(String[] args){
		WeatherEntry[] weathers = new WeatherEntry[Integer.parseInt(args[0])];
		
		Scanner scanner = new Scanner(System.in);
		for(int i = 0; i < weathers.length; i++){
			System.out.println("Type the temperature in Celsius: ");
			double temperatureInCelsius = scanner.nextDouble();
			System.out.println("Type the sunny status of each day");
			boolean isSunny = scanner.nextBoolean();
			WeatherEntry oneDay = new WeatherEntry(temperatureInCelsius, isSunny);
			weathers[i] = oneDay;
		}
		scanner.close();
		int goodDays = countGoodDays(weathers);
		double highest = findMax(weathers);
		double lowest = findMin(weathers);
		System.out.println("There were " + goodDays + " nice days.");
		System.out.println("The highest temperature was " + highest + " degree Celsius and the lowest was " + lowest);
	}
	
	private static double findMax(WeatherEntry[] weatherEntries){
		double highestTemperature = weatherEntries[0].getTemperatureCelsius();
		for(int i= 1; i < weatherEntries.length; i++){
			if(weatherEntries[i].getTemperatureCelsius() > highestTemperature){
				highestTemperature = weatherEntries[i].getTemperatureCelsius();
			}
		}
		return highestTemperature;
	}
	
	private static double findMin(WeatherEntry[] weatherEntries){
		double lowestTemperature = weatherEntries[0].getTemperatureCelsius();
		for(int i= 0; i < weatherEntries.length; i++){
			if(weatherEntries[i].getTemperatureCelsius() < lowestTemperature){
				lowestTemperature = weatherEntries[i].getTemperatureCelsius();
			}
		}
		return lowestTemperature;
	}
	
	public static int countGoodDays(WeatherEntry[] weatherEntries){
		int goodDays = 0;
		for(int i = 0 ; i < weatherEntries.length; i++){
			if(weatherEntries[i].isGoodWeather()){
				goodDays++;
			}
		}
		return goodDays;
	}
	
	public static int countGoodDays(double[] temperaturesInCelsius, boolean[] sunninessDays){
		if(temperaturesInCelsius.length != sunninessDays.length){
			throw new IllegalArgumentException("temperaturesInCelsius.length != sunninessDays.length");
		}
		int goodDays = 0;
		for(int i = 0; i < temperaturesInCelsius.length; i++){
			if(temperaturesInCelsius[i] > -30 && sunninessDays[i]){
				goodDays ++;
			}
		}
		return goodDays;
	}
}
