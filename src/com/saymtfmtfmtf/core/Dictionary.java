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
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Dictionary<K,V> {
	private Vector<K> key; // users
	private Vector<V> value; // pass
	private Scanner scnr; // read the file
	private File file; // import the file
	private int size; // Dict. Size 
	
	@SuppressWarnings("unchecked")
	public Dictionary() {
		file = new File("/Users/saymtfmtfmtf/Documents/JAVA/SmartLauncherV2/acc.txt");
		key = new Vector<K>();
		value = new Vector<V>();
		
		try {
			scnr = new Scanner(file);
			if(scnr.hasNext()) {
				while(scnr.hasNext()) {
					K keyTemp = (K)scnr.nextLine();
					V valTemp = (V)scnr.nextLine();
					key.add(keyTemp);
					value.add(valTemp);
					size++;
				}
			}else
				System.out.println("File is Empty");
		} catch(IOException e) {
			System.out.println("File Not Found " + e);
		}

		scnr.close();
	}
	
	public boolean containsKey(K key) { return this.key.contains(key); }
	
	public boolean containsVal(V val) { return this.value.contains(val); }
	
	public K getKey(V password) {
		if(this.value.contains(password)) {
			int location = this.value.indexOf(password);
			return this.key.get(location);
		}else
		return null;
	}
	
	public V getValue(K key) {
		// find the key that matches with the value using iterator
		if(this.key.contains(key)) {
			int location = this.key.indexOf(key);
			return this.value.get(location);
		}else
		return null;
	}
	
	public int getSize() { return key.size(); }
	
	public String toString() {
		return "The Key is " + key.toString() + " and the value is " + value.toString();
	}
	
	public static void main(String[] args) { 
		System.out.println("Dictionary Class."); 
	}
}
