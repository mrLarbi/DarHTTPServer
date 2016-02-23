package com.upmc.dar.http;

import java.util.Map;
import java.util.HashMap;

public class URL {
    private String url;
    private Map<String, String> parameters;

    public URL() {
        this.url = "";
        parameters = new HashMap<String, String>();
    }

    public URL(String url) {
        this.url = url;
        parameters = new HashMap<String, String>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getParameter(String parameter) {
        return parameters.get(parameter);
    }
}
