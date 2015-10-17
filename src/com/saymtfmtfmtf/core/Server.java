package com.saymtfmtfmtf.core;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket server;
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	public void startServer() {
		try {
			server = new ServerSocket(6769, 100);
			while(true) {
				waitForConnection();
				setupStreams();
			}
		}catch(IOException e) {
			showMessage("Server Disconnected");
		}finally {
			
		}
	}
	
	private void waitForConnection() throws IOException {
		System.out.println("Waiting For Connection..");
		socket = server.accept();
	}
	
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(socket.getOutputStream());
		System.out.println(output);
		output.flush();
		input = new ObjectInputStream(socket.getInputStream());
		System.out.println("Connection Setting up");
	}
	
	
	private void showMessage(String message) {
		
	}
}
