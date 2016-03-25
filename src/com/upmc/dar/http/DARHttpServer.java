package com.upmc.dar.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.routing.Router;
import com.upmc.dar.http.session.HttpSession;
import com.upmc.dar.http.session.SessionsHandler;

public class DARHttpServer {

	private static final int TIMEOUT = 30; //seconds
	private static String app;
	private static Router router;
	private static SessionsHandler sessionHandler = new SessionsHandler();

	public static void main(String args[]) {

		ServerSocket server = null;
		int port = -1;

		//get port and application name
		try {
			port = Integer.parseInt(args[0]);
			app = args[1]; 
		} catch (NumberFormatException e) {
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
		
		//parse routing file
		try {
			String[] classTokens = app.split("\\.");
			router = new Router(classTokens[classTokens.length - 1]);
			
			router.initDocument();
			router.map();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				server.close();
			} catch (IOException e2) { }
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

				String ip_address = s.getRemoteSocketAddress().toString();

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

				String strLength = request.getHeader("Content-Length");
				if(strLength == null) strLength = "0";
				
				try{
					int iLength = Integer.parseInt(strLength);
					char[] cBody = new char[iLength];
					br.read(cBody, 0, iLength);
					request.setBody(new String(cBody));					
					
				} catch (Exception e) {
					e.printStackTrace();
					response.setStatus(400);
					sendResponse(s, response);					
					s.close();
					return;
				}
				
				System.out.println(request);

				//Session Handling
				String cookie = request.getHeader("Cookie");
				if(cookie != null) {
					request.setSession(sessionHandler.getSession(cookie));
				} else {
					sessionHandler.createSession(ip_address, request, response);
				}

				//choose class that will treat the request

				IApplication application = null;
				try {
					application = chooseClass(request);
				} catch (Exception e) {
					e.printStackTrace();
					response.setStatus(404);
					sendResponse(s, response);					
					s.close();
					return;
				}
				
				if(application == null) {
					response.setStatus(404);
					sendResponse(s, response);					
					s.close();
					return;
				}
				
				//treat request
				response = application.accept(request);			
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
	
	private static IApplication chooseClass(HttpRequest r) throws Exception {
		String clazz = "";
		HashMap<Integer, String> params = new HashMap<Integer, String>();
		
		String url = r.getRequest_uri().split("\\?")[0];
		String pattern = "";
		
		String[] tokens = url.split("/");
		Set<Pattern> patterns = router.getPatterns();
		
		if(tokens.length == 0) {
			pattern = "/";
			clazz = checkPattern(pattern, patterns);
		}
		
		int i = 1;
		for(String t : tokens) {
			if(t.isEmpty()) continue;
			pattern += "/" + t;
			params.put(i, t);
			clazz = checkPattern(pattern, patterns);
			i++;
		}
		
		Class<?> appClass = Class.forName(clazz);
		try {
			IApplication app = (IApplication) appClass.newInstance();
			app.addParams(params);
			return app;
		} catch (Exception e) {
			return null;
		}
	}
	
	private static String checkPattern(String pattern, Set<Pattern> patterns) {
		for(Pattern p : patterns) {
			if(pattern.matches(p.pattern())) {
				return router.getMapping(p);
			}
		}
		
		return "";
	}
}
