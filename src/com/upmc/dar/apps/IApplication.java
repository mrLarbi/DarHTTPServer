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
    
    protected abstract HttpResponse doGet(HttpRequest request);
	protected abstract HttpResponse doHead(HttpRequest request);
	protected abstract HttpResponse doPost(HttpRequest request);
	protected abstract HttpResponse doOptions(HttpRequest request);
	protected abstract HttpResponse doPut(HttpRequest request);
	protected abstract HttpResponse doDelete(HttpRequest request);
	protected abstract HttpResponse doTrace(HttpRequest request);
	protected abstract HttpResponse doBad(HttpRequest request);
	
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
