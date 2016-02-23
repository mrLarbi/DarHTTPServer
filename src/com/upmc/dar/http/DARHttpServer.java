package com.upmc.dar.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DARHttpServer {

	private static final int TIMEOUT = 30; //seconds
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
				new AcceptConnection(socket).start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) { }
		}

	}

	private static class AcceptConnection extends Thread {

		private Socket s;

		public AcceptConnection(Socket socket) {
			s = socket;
		}

		public void run() {
			try {
				System.out.println("Client connected");
				System.out.println();

				BufferedReader br = new BufferedReader( new InputStreamReader (s.getInputStream()));
				StringBuilder strRequest = new StringBuilder();

				ExecutorService executor = Executors.newCachedThreadPool();
				Callable<String> task = () -> { 
					//get request
					String line;
					StringBuilder s = new StringBuilder();
					while( !(line = br.readLine()).isEmpty()) {
						s.append(line).append(System.lineSeparator());
					}
					
					return s.toString();
				};
				Future<String> future = executor.submit(task);
				try {
					   strRequest.append(future.get(TIMEOUT, TimeUnit.SECONDS)); 
					} catch (TimeoutException e) {
						HttpResponse response = new HttpResponse();
						response.setStatus(408);
					} finally {
					   future.cancel(true); // may or may not desire this
					}
				
				//parse request
				HttpRequest request = new HttpRequest();
				HttpResponse response = new HttpResponse();
				request.parse(strRequest.toString());
				
				String strLength = request.getHeader("Content-Lenght");
				if(strLength == null) strLength = "0";
				
				try{
					int iLength = Integer.parseInt(strLength);
					System.out.println(iLength);
					char[] cBody = new char[iLength];
					System.out.println(br.read(cBody, 0, iLength));
					System.out.println(cBody.toString());
					request.setBody(new String(cBody));					
					
				} catch (Exception e) {
					response.setStatus(400);
					sendResponse(s, response);					
					s.close();
					return;
				}
				
				System.out.println("Request = ");
				System.out.println(request);
				System.out.println();

				//treat request
				response = app.accept(request);			
				sendResponse(s, response);

				br.close();
				s.close();
			} catch (Exception e) {
				try {
					s.close();
				} catch (Exception e2) {}
				e.printStackTrace();
			}
		}
	}
	
	private static void sendResponse(Socket s, HttpResponse response) throws IOException {
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		pw.write(response.toString());
		pw.close();
	}

}
