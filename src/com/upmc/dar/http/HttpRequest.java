package com.upmc.dar.http;

import java.util.HashMap;
import org.json.*;

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

	public METHOD getMethod() {
		return method;
	}

	public void setMethod(METHOD method) {
		this.method = method;
	}

	public String getRequest_uri() {
		return request_uri;
	}

	public void setRequest_uri(String request_uri) {
		this.request_uri = request_uri;
	}

	public double getHttp_version() {
		return http_version;
	}

	public void setHttp_version(double http_version) {
		this.http_version = http_version;
	}

	public String getHeader(String header) {
		return headers.get(header);
	}

	public boolean isHeader(String header) {
		return headers.containsKey(header);
	}

	public void setHeader(String header, String value) {
		headers.put(header, value);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(method + "HTTP/" + http_version + "\n");
		for(String header : headers.keySet()) {
			builder.append(header + ": " + headers.get(header) + "\n");
		}
		builder.append(body);
		return builder.toString();
	}

	public String toHTML() {
		//TODO
		return "";
	}

	public String toJSON() {
		JSONObject json = new JSONObject();
		json.put("method", method.toString());
		json.put("version", http_version);
		json.put("uri", request_uri);
		for(String header : headers.keySet()) {
			json.put(header,headers.get(header));
		}
		json.put("body", body);
		return json.toString();
	}
}
