package com.upmc.dar.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
	
	private static final Map<Integer, String> STATUS_PHRASES;
    static { //codes initialization
        Map<Integer, String> codes = new HashMap<Integer, String>();
        
        codes.put(100, "Continue");							codes.put(408, "Request Timeout");
        codes.put(101, "Switching Protocols");				codes.put(409, "Conflict");
        													codes.put(410, "Gone");
        codes.put(200, "OK");								codes.put(411, "Length Required");
        codes.put(201, "Created");							codes.put(412, "Precondition Failed");
        codes.put(202, "Accepted");							codes.put(413, "Request Entity Too Large");
        codes.put(203, "Non-Authoritative Information");	codes.put(414, "Request Uri Too Long");
        codes.put(204, "No Content");						codes.put(415, "Unsupported Media Type");
        codes.put(205, "Reset Content");					codes.put(416, "Request Range Not Satisfied");
        codes.put(206, "Partial Content");					codes.put(417, "Expectation Failed");
        
        codes.put(300, "Multiple Choices");					codes.put(500, "Internal Server Error");
        codes.put(301, "Moved Permanently");				codes.put(501, "Not Implemented");
        codes.put(302, "Found");							codes.put(502, "Bad Gateway");
        codes.put(303, "See Other");						codes.put(503, "Service Unavailable");
        codes.put(304, "Not Modified");						codes.put(504, "Gateway Timeout");
        codes.put(305, "Use Proxy");						codes.put(505, "HTTP Version Not Supported");

        codes.put(400, "Bad Request");
        codes.put(401, "Unauthorized");
        codes.put(403, "Forbidden");
        codes.put(404, "Not Found");
        codes.put(405, "Method Not Allowed");
        codes.put(406, "Not Acceptable");
        codes.put(407, "Proxy Authentification Required");
        
        STATUS_PHRASES = Collections.unmodifiableMap(codes);
    }
	
	private double http_version;
	private int status;
	private Map<String, String> headers;
	private String body;

    public HttpResponse() {
    	http_version = 1.1;
    	status = 200;
    	headers = new HashMap<String, String>();
    	body = "";
    }
    
    public String toString() {
    	StringBuilder res = new StringBuilder();
    	res.append("HTTP/").append(http_version).append(" ").append(status).append(" ").append(STATUS_PHRASES.get(status));
    	res.append(System.lineSeparator());
    	
    	for(String header : headers.keySet()) {
			res.append(header).append(": ").append(headers.get(header)).append(System.lineSeparator());
		}
		res.append(System.lineSeparator());
		res.append(body);
		
		return res.toString();
    }
    
    public double getHttp_version(){
    	return http_version;
    }
    
    public void setHttp_version(double http_version) {
    	this.http_version = http_version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public Map<String, String> getHeaders() {
    	return headers;
    }
    
    public String getHeader(String header) {
    	return headers.get(header);
    }
    
    public void addHeader(String header, String value) {
    	headers.put(header, value);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
