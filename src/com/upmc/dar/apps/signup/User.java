package com.upmc.dar.apps.signup;

/**
 * Created by mohameddd on 3/12/16.
 */
public class User {

    public enum State {
        AUTHORIZED, BANNED
    }

    private String username;
    private String password;
    private String sport;
    State state;


    public User(String username, String password, String sport) {
        this.username = username;
        this.password = password;
        this.sport = sport;
        state = State.AUTHORIZED;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Username : " + username + "\nPassword : " + password + "\n");
        return builder.toString();
    }
}
