package com.saymtfmtfmtf.core;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Table implements Runnable {
	private String[] header = { "PC", "Status", "User", "Start", "Time", "End" };;
	private Object[][] data;
	private String startTime;
	private int timeLeft;
	private String endTime;
	private String userType;
	private int position;
	private JTable jtable;
	private JScrollPane scroll;
	
	/**
	 * Sets all the member vals empty
	 * Creates a new JTable and JScroll
	 */
	public Table() {
	
	// Set empty vars
		startTime = "";
		timeLeft = 0;
		endTime = "";
		userType = "";
		position = 0;
		
	// Create Initial Data Information
		Object[][] data = {
			        {"PC 001", "offline",
			         "user", new String(""), new String(""), new String("")},
			        {"PC 002", "offline",
			         "user", new String(""), new String(""), new String("")},
			        {"PC 003", "offline",
			         "user", new String(""), new String(""), new String("")}
			    };
		this.data = data;
		
	// Creates Table
		jtable = new JTable(data, header);
		
		jtable.setPreferredScrollableViewportSize(new Dimension(400, 32));
	    jtable.setFillsViewportHeight(true);
	    jtable.setGridColor(new Color(150,150,150));
		
	//Creates Scroll Table
		scroll = new JScrollPane(jtable);
	}
	
	
	/**
	 * Changes the table for updated info
	 * 
	 * @param startTime
	 * @param timeLeft
	 * @param endTime
	 * @return table
	 */
	
	public void setCompTable(String startTime, int timeLeft, String endTime, String userType, int position) {
		this.startTime = startTime;
		this.timeLeft = timeLeft;
		this.endTime = endTime;
		this.userType = userType;
		this.position = position;
		setJTable();
	}
	
	/**
	 * Updates all the info in the table 
	 */
	public void setJTable() {
		/*
		 * Set An empty table
		 * Replaces old data with temp
		 */
		Object[][] temp = {
		        {"PC 001", "offline",
		         "user", startTime, timeLeft, endTime},
		        {"PC 002", "offline",
		         "user", startTime, timeLeft, endTime},
		        {"PC 003", "offline",
		         "user", startTime, timeLeft, endTime}
		        };
		data = temp;
		
		// Creates a new Table and resets it
		JTable table = new JTable(temp, header);
		table.setPreferredScrollableViewportSize(new Dimension(400, 32));
	    table.setFillsViewportHeight(true);
	    
		table.setGridColor(new Color(150,150,150));
		
		jtable = table; // replace old table with new
	}
	
	/**
	 * @return Allows user to retrieve the jtable
	 */
	public JTable getCompTable() { return jtable; }
	
	/**
	 * @return the scroll object of the JTable
	 */
	public JScrollPane compScroll() { return scroll; }
	
	/**
	 * Continuously runs to check for updates.
	 */
	public void run() {
		setJTable(); // resets the jtable
		
		scroll = new JScrollPane(jtable); // sets the new scroll obj
		
		//System.out.println("Table Run" + startTime); // DEBUG
	}
}
