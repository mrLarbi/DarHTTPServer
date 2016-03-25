package com.upmc.dar.http.session;

import java.util.HashMap;
import java.util.Set;

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

    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }

    public long getCreationDate() {
        return creationDate;
    }
    public void print() {
        for(String key : attributes.keySet()) {
            System.out.println(key + ":" + attributes.get(key));
        }
    }
}
