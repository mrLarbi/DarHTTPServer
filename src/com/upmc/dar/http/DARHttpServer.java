package com.upmc.dar.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class DARHttpServer {
	
	private static IApplication app;
	
	public static void main(String args[]) {
		
		ServerSocket server = null;
		int port = -1;
		
		//get port and application name
		try {
			port = Integer.parseInt(args[0]);
			Class<?> appClass = Class.forName(args[1]);
			app = (IApplication) appClass.newInstance(); 
		} catch (NumberFormatException 	|				 
				 ClassNotFoundException | 
				 IllegalAccessException | 
				 InstantiationException |
				 SecurityException e) {
			
			e.printStackTrace();
			return;
		}
		
		//initialize server
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		//server code
		try {
			for(;;) {
				Socket socket = server.accept();
				
				BufferedReader br = new BufferedReader( new InputStreamReader (socket.getInputStream()));
				StringBuilder request = new StringBuilder();
				String line;
				
				//get request
				while( (line = br.readLine()) != null) {
					request.append(line);
				}
				
				//parse request
				
				
				//treat request
				
				//string response
				
				//send response
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) { }
		}
		
	}

}
