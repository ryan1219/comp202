package fall2016;

public class WeatherEntry {
	private double temperatureInCelsius;
	private boolean isSunny;
	
	public WeatherEntry(double temperatureInCelsius, boolean isSunny){
		this.temperatureInCelsius = temperatureInCelsius;
		this.isSunny = isSunny;
	}
	
	public double getTemperatureCelsius(){
		return this.temperatureInCelsius;
	}
	
	public boolean isGoodWeather(){
		if(temperatureInCelsius > -30 && isSunny){
			return true;
		}else{
			return false;
		}
	}
	
	public void display(boolean isCelsius){
		if(isCelsius){
			System.out.println("It is " + temperatureInCelsius + " degrees Celsius and is" + isSunny);
		}else{
			System.out.println("It is " + (temperatureInCelsius * 1.8 + 32) + " degrees Fahrenheit and is" + isSunny);
		}
	}
}
