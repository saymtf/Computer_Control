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


public class Verification {
	private Dictionary<String, String> dict;
	private Time_Remaining timeRemain;
	public Verification() {
		dict = new Dictionary<String, String>();
		timeRemain = new Time_Remaining();
	}
	
	public boolean logIn(String user, String pass) {
		if(dict.getSize() > 0) {
			//System.out.println("The username and password is : " + dict.getKey(pass).equals(user) + " " + dict.getValue(user).equals(pass) );
			if(dict.containsKey(user) && dict.containsVal(pass)) {
				if((dict.getKey(pass).equals(user) && dict.getValue(user).equals(pass))) {
					if(timeRemain.getTimeLeft() > 0) {
						return true;
					}else {
						System.out.println("Sorry, You Need More Time.");
						return false;
					}
				}else
					return false;
			}else
				return false;
		}else
			return false;
	}
	
	public String getUser(String user) {
		return dict.getValue(user);
	}

	public static void main(String[] args) {
		System.out.println("Verify Class.");
	}
}
