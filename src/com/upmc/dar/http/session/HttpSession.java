package com.upmc.dar.http.session;

import java.util.HashMap;

/**
 * Created by mohameddd on 3/11/16.
 */
public class HttpSession {

    private HashMap<String, Object> attributes;
    private long creationDate;

    public HttpSession() {
        creationDate = System.currentTimeMillis();
        attributes = new HashMap<>();
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public boolean isAttribute(String key) {
        return attributes.containsKey(key);
    }

    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    public String[] getAttributeNames() {
        return (String[])attributes.keySet().toArray();
    }

    public long getCreationDate() {
        return creationDate;
    }
}
