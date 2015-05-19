//**************************************************
//*********************************************************************
//  ##############   #####################      ######           ####
// ###############   ######################     #########      ######
// #######      ##   ########      ########     #########      ######
// #######           #########     ########     #####################
// ###############                 ########            ########
// #           ##     #######################            ######
// ###         ###    #######        #######             ######
// ###############     #######       ########             ######
//  ##############     ####################         ################
//
// ############    #########    ###############    ##################
// #############  ###########  #################   ##################
// #######    ####    #######        #####         ######       #####
// ########           #######        #####         ########     #####
// #########          #######        #####         ########
//  ########         ########        #####         ######
// ##########      ##########        #####         ######
//**********************************************************
//**************************************************
package com.saymtfmtfmtf.view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.awt.dnd.*;
import javax.swing.*;

import com.saymtfmtfmtf.core.*;

public class InterfaceComponents{
	
	/**
	 * This is the upper bar
	 * @return
	 */
	public JPanel actionBar() {
		JPanel action = new JPanel();
		action.setOpaque(true);
		action.setBackground(new Color(204,204,204));
		action.setPreferredSize(new Dimension(1024, 72));
		
		return action;
		
	}
	
	/**
	 * The is the panel for the computers
	 * @return
	 */
	public JPanel computers() {
		JPanel comps = new JPanel();
		comps.setOpaque(true);
		comps.setBackground(new Color(204,204,204));
		comps.setPreferredSize(new Dimension(200,32));
		
		return comps;
	}
	
	/**
	 * This is the panel for the accounts
	 * @return
	 */
	public JPanel accounts() {
		JPanel acc = new JPanel();
		acc.setOpaque(true);
		acc.setBackground(new Color(204,204,204));
		acc.setPreferredSize(new Dimension(200,32));
		
		return acc;
	}
	
	/**
	 * 
	 * @return
	 */
	public JPanel sideBar() {
		JPanel rightBar = new JPanel();
		rightBar.setOpaque(true);
		rightBar.setBackground(new Color(204,204,204));
		rightBar.setPreferredSize(new Dimension(256, 1024));
		
		return rightBar;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	/*
	public JTable compTable() {
		String[] header = { "PC", "Status", "User", "Start", "Time", "End" };
		Object[][] data = {
			        {"PC 001", "offline",
			         "user", new String(""), new String(""), new String("")},
			        {"PC 002", "offline",
			         "user", new String(""), new String(""), new String("")},
			        {"PC 003", "offline",
			         "user", new String(""), new String(""), new String("")}
			        };
		final JTable table = new JTable(data, header);
		table.setPreferredScrollableViewportSize(new Dimension(400, 32));
	    table.setFillsViewportHeight(true);
	    
		table.setGridColor(new Color(150,150,150));
		return table;
	}
	*/
	/**
	 * 
	 * @return
	 */
	public JTable userTable() {
		String[] acc = getAccounts();
		int size = getSize();
		
		String[] header = { "Username", "Time", "Activity" };
		Object[][] data = new Object[size][3];
		int k = 0;
		for(int i = 0; i < size-3; i++) {
			for(int j = 0; j < 1; j++) {
				data[i][j] = acc[k];
				data[i][j+1] = acc[k+1];
				data[i][j+2] = "Active";
				
			}
			k+=2;
		}
		
		final JTable userTable = new JTable(data, header);
		userTable.setPreferredScrollableViewportSize(new Dimension(400, 32));
		userTable.setFillsViewportHeight(true);
		userTable.setDragEnabled(false);
		userTable.setGridColor(new Color(150,150,150));
		
		return userTable;
	}

	
	
	/**
	 * 
	 * @param table
	 * @return
	 */
	public JScrollPane userScroll(JTable table) {
		JScrollPane scroll = new JScrollPane(table);
		
		return scroll;
	}
	
	/**
	 * 
	 * @return
	 */
	public JMenuBar menuBar() {
		// Create a menu bar
			JMenuBar greenMenuBar = new JMenuBar(); // get menu bar
			greenMenuBar.setOpaque(true);
			greenMenuBar.setBackground(new Color(204,204,204));
			greenMenuBar.setPreferredSize(new Dimension(1024,20));

		return greenMenuBar;
	}
	
	/**
	 * 
	 * @return
	 */
	public JTextField searchPane() {
		JTextField textBox = new JTextField();
		textBox.setPreferredSize(new Dimension(728,20));
		return textBox;
	}
	
	/**
	 * 
	 * @return
	 */
	public JTabbedPane tabs() {
		JTabbedPane tab = new JTabbedPane();
		
		return tab;
	}
	
	/**
	 * 
	 * @return
	 */
	public JButton goButton() {
		JButton go = new JButton("search");
		go.setBackground(new Color(200,150,100));
		
		return go;
	}
	
	/**
	 * 
	 * @return
	 */
	public JButton addUser() {
		JButton addUser = new JButton();
		
		return addUser;
	}
	
	/**
	 * 
	 * @return
	 */
	public JScrollPane timeLabel() {
<<<<<<< HEAD
<<<<<<< HEAD
		Integer[] times = {10, 15, 30, 45, 60, 90, 120};
=======
		String[] times = {"10 min", "15 min", "30 min", "45 min", "60 min", "90 min", "120 min"};
>>>>>>> FETCH_HEAD
=======
		String[] times = {"10 Minutes", "15 Minutes", "30 Minutes", "45 Minutes", "1 Hour 0 Minutes", "1 Hour 30 Minutes", "2 Hour 0 Minutes", "Custom"};
>>>>>>> parent of cf37021... New Update
		
		JList list = new JList(times);
		list.setDragEnabled(true);
		list.setVisibleRowCount(-1);
		list.setPreferredSize(new Dimension(248,1028));
		JScrollPane scrollPane = new JScrollPane(list);
		return scrollPane;
	}
	
	/**
	 * 
	 * @return
	 */
	public JLabel addTimeLabel() {
		JLabel text = new JLabel("Add Time");
		
		return text;
	}
	
	/**
	 * 
	 * @return
	 */
	public JMenu menuFile() {
		JMenu fileM = new JMenu("File");
		
		return fileM;
	}
	
	/**
	 * 
	 * @return
	 */
	public JMenu menuEdit() {
		JMenu editM = new JMenu("Edit");

		return editM;
	}
	
	/**
	 * 
	 * @return
	 */
	public JMenu menuHelp() {
		JMenu helpM = new JMenu("Help");
		
		return helpM;
	}
	
	/**
	 * 
	 * 
	 */
	private String[] accList = new String[100];
	private int size;
	
	public int getSize() { return size; }
	
	public String[] getAccounts() {
		
		File file = new File("/Users/saymtfmtfmtf/Documents/workspace/SmartLauncherV3/acc.txt");
		try {
			Scanner scnr = new Scanner(file);
			while(scnr.hasNext()) {
				accList[size++] = scnr.nextLine();

				scnr.nextLine();
				accList[size++] = scnr.nextLine();
			}
			scnr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return accList;
	}
	
	
}
