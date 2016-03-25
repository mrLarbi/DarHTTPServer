package com.upmc.dar.apps;

import java.util.HashMap;

import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;
import com.upmc.dar.http.template.SimpleTemplate;

public class HelloServer extends IApplication {

	@Override
	protected HttpResponse doGet(HttpRequest request) {
		HashMap<String, String> ref = new HashMap<String, String>();
		
		ref.put("name", request.getParameter("name"));
		ref.put("age", request.getParameter("age"));
		
		SimpleTemplate temp = new SimpleTemplate(request.getParameter("type"));
		String body = "";
		
		try {
			body = temp.transform("helloserver", ref);
		} catch (Exception e) {
			return doBad(request);
		}
		
		HttpResponse response = new HttpResponse();
		
		switch(request.getParameter("type")) {
		case "html" : 
			response.addHeader("Content-Type", "text/html");
			break;
		case "json" : 
			response.addHeader("Content-Type", "application/json");
			break;
		case "xml" : 
			response.addHeader("Content-Type", "text/plain");
			break;	
		default: return doBad(request);
		}
		
		response.setBody(body);
		
		return response;
	}

}
