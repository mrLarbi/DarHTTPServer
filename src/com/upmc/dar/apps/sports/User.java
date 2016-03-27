package com.upmc.dar.apps.sports;

import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mohameddd on 3/12/16.
 */
public class User {

    private String username;
    private String password;
    private String sport;
    private boolean isLogged;


    public User(String username, String password, String sport) {
        this.username = username;
        this.password = password;
        this.sport = sport;
        isLogged = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSport() {
        return sport;
    }

    public void logIn() {
        isLogged = true;
    }

    public void logOut() {
        isLogged = false;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Username : " + username + "\nPassword : " + password + "\n");
        return builder.toString();
    }

    public JSONObject toJSON() {
        JSONObject rootObject = new JSONObject();
        rootObject.put("username", username);
        rootObject.put("isLogged", isLogged);
        rootObject.put("sport", sport);
        return rootObject;
    }
}
