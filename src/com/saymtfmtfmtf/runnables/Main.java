//******************************
//
//	GeniusExecuter.java
//
//
//
//  Created by saymtfmtfmtf (Mitchell Fenton).
//  Created on 1/02/14.
//  Copyright (c) 2014 saymtf. All rights reserved.
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
package com.saymtfmtfmtf.runnables;

import java.io.IOException;
import java.util.Scanner;

import com.saymtfmtfmtf.core.*;
import com.saymtfmtfmtf.view.Interface;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws IOException{
		ScheduledThreadPoolExecutor threadPoolExe = new ScheduledThreadPoolExecutor(5);

		Time time = new Time();
		Interface inter = new Interface();
		
        threadPoolExe.scheduleWithFixedDelay(time, 1, 1, TimeUnit.SECONDS);
        threadPoolExe.scheduleWithFixedDelay(inter, 1, 1, TimeUnit.SECONDS);
        
        new Thread(inter).start();
	}
}
