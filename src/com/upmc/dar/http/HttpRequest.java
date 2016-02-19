package com.upmc.dar.http;

import java.util.HashMap;

public class HttpRequest {
	
	private enum METHOD { GET, HEAD, POST, POUT, OPTIONS, PUT, DELETE, TRACE, NONE }
	
	private METHOD method;
	private String request_uri;
	private double http_version;
	//one line per header
	private HashMap<String, String> headers;
	private String body;



	public HttpRequest(String request) {
		
		method = METHOD.NONE;
		request_uri = "";
		http_version = 0;
		headers = new HashMap<String, String>();
		body = "";
		
	}
}
