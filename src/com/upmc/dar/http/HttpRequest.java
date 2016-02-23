package com.upmc.dar.http;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.hp.gagawa.java.elements.*;

public class HttpRequest {
	
	private enum METHOD { GET, HEAD, POST, PUT, OPTIONS, DELETE, TRACE, NONE }
	
	private METHOD method;
	private double http_version;
	private URL url;
	private Map<String, String> headers;
	private String body;
	private boolean valid;
	
	public HttpRequest() {
		
		method = METHOD.NONE;
		url = new URL();
		http_version = 0;
		headers = new HashMap<String, String>();
		body = "";
		valid = false;
	}
	
	public void parse(String request) {
		
		try {
			parseRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
			valid = false;
		}
		valid = true;
	}
	
	private void parseRequest(String request) throws Exception  {
		
		String[] lines = request.split("\n");
		String[] request_line = lines[0].split("\\s+");
		
		method = METHOD.valueOf(request_line[0]);
		url.setUrl(request_line[1]);
		http_version = Double.parseDouble(request_line[2].replace("HTTP/", ""));
		
		for(String param : url.getUrl().split("\\?")[1].split("\\&")) {
			String p[] = param.split("=");
			url.getParameters().put(p[0], p[1]);
		}
		
		int l = 1;
		while(l < lines.length && lines[l].contains(":")) {
			String values[] = lines[l].split("\\: ");
			String header = values[0];
			String val = values[1].trim();
			
			headers.put(header, val);
			l++;
		}
	}
	
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(method.toString()).append(" ").append(url.getUrl()).append(" ").append("HTTP/").append(http_version);
		res.append(System.lineSeparator());
		
		for(String header : headers.keySet()) {
			res.append(header).append(": ").append(headers.get(header)).append(System.lineSeparator());
		}
		res.append(System.lineSeparator());
		res.append(body);
		
		return res.toString();
	}
	
	public String toHTML() {
		Html html = new Html();
		Head header = new Head();
		Title title = new Title();
		
		title.appendText("HTTP Request");
		header.appendChild(title);
		html.appendChild(header);
		
		Body body = new Body();
		H1 h1 = new H1();
		h1.appendText(method.toString() + " " + url.getUrl() + " HTTP/" + http_version);
		body.appendChild(h1);
		P p = new P();
		
		for(String httpheader : headers.keySet()) {
			p.appendText(httpheader + ": " + headers.get(httpheader));
			p.appendChild(new Br());
		}
		p.appendText(System.lineSeparator());
		p.appendText(this.body);
		
		body.appendChild(p);
		html.appendChild(body);
		
		return html.write();
	}
	
	public String toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("method", method.toString());
		json.put("uri", url.getUrl());
		json.put("htp_version", http_version);
		
		for(String header : headers.keySet()) {
			json.put(header, headers.get(header));
		}	
		json.put("body", body);
		
		return json.toString();
	}
	
	public String getMethod() {
		return method.toString();
	}

	public String getRequest_uri() {
		return url.getUrl();
	}

	public Map<String, String> getParameters() {
		return url.getParameters();
	}

	public String getParameter(String parameter) {
		return url.getParameters().get(parameter);
	}
	
	public double getHttp_version() {
		return http_version;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public String getHeader(String header) {
		return headers.get(header);
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void invalid() {
		valid = false;
	}
}
