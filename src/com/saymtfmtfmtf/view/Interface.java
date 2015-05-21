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
/**
 *  @author saymtfmtfmtf
 *  @date 4/4/14
 */
package com.saymtfmtfmtf.view;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.saymtfmtfmtf.core.*;

public class Interface extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3272006329833735737L;

	// VARS
	private InterfaceComponents ic;
	private Table table;
	private int x, y;
	private JPanel rightBar, actionBar, compMain, accMain;
	private JTable compTable, userTable;
	private JScrollPane compScrollTable, userScrollTable, times;
	private JMenuBar menuBar;
	private JMenu file, edit, help;
	private JButton searchButton, addNewUser;
	private JTabbedPane tab;
	private JTextField searchUser;
	private JLabel addTimeText;
	private Time_Remaining timeRemain;
	private Time time;
	private boolean timeAdded = false;
	
	public Interface() { // Constructor
		ic = new InterfaceComponents();
	// JFRAME Needed Info
		setName("Practice App");
		//setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());

		setEnabled(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content
		
	// Dragging 
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				System.out.println("Dragged");
				x = e.getX();
				y = e.getY();
				repaint();
			}
		});
		
	// Initializing
		timeRemain = new Time_Remaining();
		time = new Time(); // pbr
		table = new Table(); // constructor
		rightBar = ic.sideBar();
		actionBar = ic.actionBar();
		compMain = ic.computers();
		accMain = ic.accounts();
		addNewUser = ic.addUser();
		userTable = ic.userTable();
		compScrollTable = table.compScroll();
		userScrollTable = ic.userScroll(userTable);
		searchUser = ic.searchPane();
		tab = ic.tabs();
		searchButton = ic.goButton();
		addTimeText = ic.addTimeLabel();
		menuBar = ic.menuBar();
		times = ic.timeLabel();
		file = ic.menuFile();
		edit = ic.menuEdit();
		help = ic.menuHelp();
		
		
		
	//Set the menu bar and add the label to the content panel
	
		setJMenuBar(menuBar);
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);

		
	//Panels
		
		setLayout(new BorderLayout(4,4)); // Add Border Space of (4,4)
		getContentPane().add(actionBar, BorderLayout.PAGE_START);
		getContentPane().add(tab, BorderLayout.CENTER);
		getContentPane().add(rightBar, BorderLayout.EAST);
		
	// New Layouts
		
		rightBar.setLayout(new BoxLayout(rightBar, BoxLayout.Y_AXIS));
		compMain.setLayout(new GridLayout(1,0));
		accMain.setLayout(new GridLayout(1,0));
		
	//Component Objects
		
		actionBar.add(searchUser);
		actionBar.add(searchButton);
		actionBar.add(addNewUser);

		rightBar.add(addTimeText);
		rightBar.add(times);
		
	// Main Screen
		
		tab.addTab("Computers", compMain); // Computer TAB
		tab.addTab("Users", accMain); // User TAB
		
		searchButton.addActionListener(new ActionListen());
		searchUser.addActionListener(new ActionListen());
		addNewUser.addActionListener(new ActionListen());
		
		
	// Display Window
	
		pack();
		setVisible(true);
	}
	
	/**
	 * 
	 */
	public void run() {


		new Thread(time).start();
		new Thread(table).start();
		// I need to Check the computer table 
			//Whether the time has changed (DnD)
			//Activity(Someones logged on or not)
			//COMPUTER connection	
		// Add refresh to see screen
		//
		//
		//
		//
		// Computer Connection
		boolean connection = true;
		
		// Eligible to Drag and Drop time
		if(connection) {
			//enable transfer handle
			
			
			// Update info to computer grid
			int a = 3;
			// if its acc
			if(a == 2) { // find the specific location to add time and update
				
			}else { // if its dnd
				if(time.getTime() != null) {
					compMain.remove(compScrollTable); // need to replace
					String currTime = time.getTime(); // current time
					if(timeAdded == false) {
						time.setEndTime("10"); // ***replace with the amount the user moved into.
						timeAdded = true;
					}
					String endTime = time.getEndingTime(); // the end time
					timeRemain.setTimeLeft(endTime, currTime);
					int timeLeft = timeRemain.getTimeLeft();
					table.setCompTable(currTime, timeLeft, endTime, "guest", 1);
					compScrollTable = table.compScroll();

					//System.out.println("Time " + time.getTime());
				}
				
			}
		}
		compMain.add(compScrollTable);
		accMain.add(userScrollTable);
		compMain.validate();
		//System.out.println("Time " + time.getTime());
	}
	
	/**
	 * ActionListener
	 *
	 *
	 */
	private class ActionListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addNewUser) {
				RegisterUser register = null; // create register User Class
				JLabel jUserName = new JLabel("User Name"); // username
		        JTextField userName = new JTextField(); // empty field
		        JLabel jPassword = new JLabel("Password"); // Password
		        JTextField password = new JPasswordField(); // pass field
		        
		        do { // ask for the Name/Pass
		        	Object[] info = {jUserName, userName, jPassword, password};
		        	JOptionPane.showConfirmDialog(null, info, "Please enter the information", JOptionPane.OK_CANCEL_OPTION);
		        } while(userName.getText() == "" && password.getText() == "");
				try {
					register = new RegisterUser();
					register.setUser(userName.getName());
					register.setPassword(password.getName());
					register.setTime(100.00);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(userName.getText());
				
				
			}
			// TODO Auto-generated method stub
			if(e.getSource() == searchUser || e.getSource() == searchButton) {
				String text = searchUser.getText();
				System.out.println(text);
			}
		}
	}



}
