package com.upmc.dar.apps.musics;

import java.util.HashMap;

/**
 * Created by mohameddd on 3/25/16.
 */
public class Musics {

    static HashMap<String, User> users = new HashMap<>();

    static User getUser(String username) {
        return users.get(username);
    }

    static boolean isUser(String username) {
        return users.containsKey(username);
    }

    static void addUser(User user) {
        if(!isUser(user.getUsername())) {
            users.put(user.getUsername(), user);
        }
    }
}
