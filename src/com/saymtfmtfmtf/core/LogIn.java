package com.saymtfmtfmtf.core;

import java.util.Scanner;

public class LogIn {
	public LogIn() {

		Verification ver = new Verification();
		Scanner scnr = new Scanner(System.in);
		
		while(true) {
			// Reading (Logging)
			System.out.println("Log In");
		//	System.out.println(ver.logIn(user.getUser(), user.getPassword()));
			System.out.print("Username : ");
			String name = scnr.nextLine();
			System.out.print("Password : ");
			String pass = scnr.nextLine();
			if(ver.logIn(name, pass)) { System.out.println("Welcome, " + name + "."); break; }
			else System.out.println("Username and or Password is not correct.");
		}
	}
}
