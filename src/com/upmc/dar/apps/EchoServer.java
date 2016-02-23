package com.upmc.dar.apps;

import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;

public class EchoServer extends IApplication {

	public HttpResponse doGet(HttpRequest request) {
		HttpResponse response = new HttpResponse();
		
		String contentType = request.getParameter("type");
			
		if(contentType == null) contentType = "text/plain";
		switch (contentType) {
		case "text":
			response.addHeader("Content-Type", "text/plain");
			response.setBody(request.toString());
			break;
		case "html":
			response.addHeader("Content-Type", "text/html");
			response.setBody(request.toHTML());
			break;
		case "json":
			response.addHeader("Content-Type", "application/json");
			response.setBody(request.toJSON());
			break;
		default:
			response.setStatus(415);
			response.setBody("Format not supported : " + contentType);
		}
		return response;
	}

	protected HttpResponse doHead(HttpRequest request) {
		return notImplemented();
	}

	protected HttpResponse doPost(HttpRequest request) {
		return notImplemented();
	}

	protected HttpResponse doOptions(HttpRequest request) {
		return notImplemented();
	}

	protected HttpResponse doPut(HttpRequest request) {
		return notImplemented();
	}

	protected HttpResponse doDelete(HttpRequest request) {
		return notImplemented();
	}

	protected HttpResponse doTrace(HttpRequest request) {
		return notImplemented();
	}
	
	protected HttpResponse doBad(HttpRequest request) {
		HttpResponse response = new HttpResponse();
		response.setStatus(400);
		return response;
	}

	private HttpResponse notImplemented() {
		HttpResponse response = new HttpResponse();
		response.setStatus(501);
		return response;
	}
}
