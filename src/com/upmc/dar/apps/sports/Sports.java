package com.upmc.dar.apps.sports;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mohameddd on 3/25/16.
 */
public class Sports {

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

    static User logUser(String username, String password) {
        if(isUser(username)) {
            User user = users.get(username);
            if(user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    static ArrayList<User> listUsers() {
        ArrayList<User> result  = new ArrayList<>();
        for(String user : users.keySet()) {
            result.add(users.get(user));
        }
        return result;
    }
}
