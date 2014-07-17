package com.saymtfmtfmtf.core;

import java.text.DateFormat;
import java.util.*;

public class Time implements Runnable{
	private Date date;
	private Locale loc;
	private DateFormat df;
	private String time;
	private String endTime;
	
	
	/**
	 * Sets the end time to None Set
	 */
	public Time() {
		endTime = "None Set"; /*+ time.split(":")[1];*/
	}
	
	/**
	 * Constantly grabs the current time in PST
	 */
	public void run() {
		date = new Date();
		loc = new Locale("en");
		df = DateFormat.getTimeInstance(DateFormat.DEFAULT, loc);
		time = df.format(date);
	}
	
	/**
	 * @return The current Time (in hour:minutes:seconds)
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * @return The Time left (in hour:minutes:seconds)
	 */
	public String getEndingTime() {
		return endTime;
	}
	
	/**
	 * This will split up the current time on the clock and add the AMOUNT of time the USER has so the user knows when it'll end..
	 * Splitting all the :
	 * Checking the Hours, Minutes then seconds so they're not over (60) and adding to the time
	 * 
	 * Then finally puts it back together.
	 * 
	 * @param endTime is the time thats going to modify to know when it's going to end.
	 */
	public void setEndTime(String endTime) {
		
		String[] a = time.split(":"); // split the current time
		
		int minTime = Integer.parseInt(time.split(":")[1])+Integer.parseInt(endTime); // change minute
		
		
		if(minTime >= 60) { // if time becomes over 60 minutes
			minTime -= 60; // subtract the hour
			int hourTime = Integer.parseInt(time.split(":")[0]) + 1; // change back to specific hour HOUR:MIN:SEC
			if(hourTime > 13) {
				a[0] = Integer.toString(12 - hourTime); // change hour if over 12
			}else {
				a[0] = Integer.toString(hourTime); // change hour
			}
		}
		
		if(minTime < 10) {
			a[1] = "0" + Integer.toString(minTime); // concat minutes
		}else {
			a[1] = Integer.toString(minTime); // concat back to HOUR:MIN:SEC
		}
		
		
		this.endTime = ""; // set empty
		
		for(int i = 0; i < 3; i++) {
			if(i < 2) {
				this.endTime += a[i] + ":";
			}else {
				this.endTime += a[i];
			}
			//System.out.println(this.endTime);
		}
	}
}
