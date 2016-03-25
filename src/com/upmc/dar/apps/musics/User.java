package com.upmc.dar.apps.musics;

import java.util.ArrayList;

/**
 * Created by mohameddd on 3/12/16.
 */
public class User {

    private String username;
    private String password;
    private ArrayList<String> musics;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.musics = new ArrayList<>();
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

    public void addMusic(String music) {
        musics.add(music);
    }

    public void removeMusic(String music) {
        musics.remove(music);
    }

    public ArrayList<String> getMusics(String music) {
        return musics;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Username : " + username + "\nPassword : " + password + "\n");
        return builder.toString();
    }
}
