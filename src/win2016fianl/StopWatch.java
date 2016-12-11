package win2016fianl;

import java.util.Scanner;

import javax.management.timer.TimerMBean;

public class StopWatch {
	private boolean running = false;	
	private Time startTime;
	
	public void startStop(){
		if(running){
			running = false;
			Time currentTime = getCurrentTime();
			int currentTimeInSecond = 3600 * currentTime.getHour() + 60 * currentTime.getMinute() +  currentTime.getSecond();
			int startTimeInSecond = 3600 * startTime.getHour() + 60 * startTime.getMinute() + startTime.getSecond();
			System.out.println(currentTimeInSecond - startTimeInSecond);
		}else{
			running = true;
			startTime = getCurrentTime();
		}
	}
	
	public static void main(String[] args){
		StopWatch sWatch = new StopWatch();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Entern any String to stop the stopwatch");
		String userInput = scanner.nextLine();
		sWatch.startStop();
		
	}
	
	
	public Time getCurrentTime(){
		return null;
	}
}
