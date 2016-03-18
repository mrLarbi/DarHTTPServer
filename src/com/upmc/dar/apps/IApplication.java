package com.upmc.dar.apps;

import java.util.HashMap;

import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;

public abstract class IApplication {

	private HashMap<Integer, String> params = new HashMap<Integer, String>();
	
    public HttpResponse accept(HttpRequest request) {
    	String m = request.getMethod();
    	
    	if(!request.isValid())
    		return doBad(request);
    	
    	switch(m) {
    	case "GET" : return doGet(request);
    	case "HEAD" : return doHead(request);
    	case "POST" : return doPost(request);
    	case "OPTIONS" : return doOptions(request);
    	case "PUT" : return doPut(request);
    	case "DELETE" : return doDelete(request);
    	case "TRACE" : return doTrace(request);
    	default : return doBad(request);
    	}
    };

	protected HttpResponse doGet(HttpRequest request) {
		return notImplemented();
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
		return notImplemented();
	}

	private HttpResponse notImplemented() {
		HttpResponse response = new HttpResponse();
		response.setStatus(501);
		return response;
	}
	
	public void addParams(HashMap<Integer, String> p) {
		params.putAll(p);
	}
	
	public void addParam(int p, String v) {
		params.put(p, v);
	}
	
	public String getParam(int p) {
		return params.get(p);
	}

}
