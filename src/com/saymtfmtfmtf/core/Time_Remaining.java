package com.saymtfmtfmtf.core;

public class Time_Remaining {
	//private Time time;
	private int timeLeft;
	
	/**
	 * 
	 */
	public Time_Remaining() {
		timeLeft = -1; // init to -1
	}
	
	/**
	 * 
	 * @param endTimer
	 * @param currentTime
	 */
	public void setTimeLeft(String endTimer, String currentTime) {
	// Setting the Hour/Minutes
		
		int endHour = Integer.parseInt(endTimer.split(":")[0]); // time to end
		int currHour = Integer.parseInt(currentTime.split(":")[0]); // time Started
		int hourLeft = 0; // init hoursLeft
		
		//Whether the hour is > or < endHour
		if(currHour > endHour) {
			hourLeft = (currHour - endHour)*60;
		}else {
			hourLeft = (endHour - currHour)*60;
		}
		
		// Minutes Time
		int currMin = Integer.parseInt(currentTime.split(":")[1]);
		int endMin = Integer.parseInt(endTimer.split(":")[1]);
		
		if(currMin > endMin && hourLeft == 0) {
			timeLeft = endMin - currMin; // just sub min
		}else if(endMin > currMin && hourLeft == 0){ 
			timeLeft =  currMin - endMin; // just sub min
		}if(currMin > endMin && hourLeft > 0) { // Hours > 0
			timeLeft = hourLeft - (endMin - currMin);
		}else {
			timeLeft = hourLeft - (currMin - endMin);
		}

	}
	
	/**
	 * 
	 */
	public void setTimer() {
		timeLeft = -1; // if timers over set to -1
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTimeLeft() {
		return timeLeft;
	}
}
