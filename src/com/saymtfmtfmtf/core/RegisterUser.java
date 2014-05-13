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

package com.saymtfmtfmtf.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterUser {
	private File file;
	private FileWriter w;
	//private String usersName;
	//private String usersPass;
	//private UserTime timeLeft;
	
	public RegisterUser() throws IOException{
		//timeLeft = new UserTime();
		file = new File("/Users/saymtfmtfmtf/Documents/JAVA/SmartLauncherV2/acc.txt");
		w = new FileWriter(file, true);
	}
	
	public void setUser(String username) throws IOException { 
		writeUser(username);
	} // set username

	public void setPassword(String password) throws IOException { 
		writePass(password); 
		
	} // set pass 
	
	public void setTime(double time) throws IOException { 
		writeTime(time); 
	}
	
	
	/*
	 * // get the key from the dictionary class
	public String getUser() { return usersName; }

	// get the value from the dictionary class
	public String getPassword() { return usersPass; }
	
	//get the value of time
	public double getTime() { return timeLeft.getTime(); }
	*/
	public void writeUser(String userName) throws IOException{
		w.write(userName + "\n");
		w.close();
	}
	public void writePass(String userPass) throws IOException{
		w.write(userPass + "\n");
		w.close();
	}
	public void writeTime(double userTime) throws IOException{
		w.write(userTime + "\n");
		w.close();
	}
}
