package com.upmc.dar.http;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by mohameddd on 2/23/16.
 */
public class URL {
    private String request_uri;
    private Map<String, String> parameters;

    public URL() {
        this.request_uri = "";
        parameters = new HashMap<String, String>();
    }

    public URL(String requestUri) {
        this.request_uri = requestUri;
        parameters = new HashMap<String, String>();
    }

    public String getRequest_uri() {
        return request_uri;
    }

    public void setRequest_uri(String request_uri) {
        this.request_uri = request_uri;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getParameter(String parameter) {
        return parameters.get(parameter);
    }
}
