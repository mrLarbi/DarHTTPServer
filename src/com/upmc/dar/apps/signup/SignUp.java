package com.upmc.dar.apps.signup;

import sun.security.util.Password;

import java.util.HashMap;

/**
 * Created by mohameddd on 3/12/16.
 */
public class SignUp {

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

    static void banUser(String username) {
        // username is not smart if this method is called on him :)
        if(isUser(username)) {
            User user = users.get(username);
            user.setState(User.State.BANNED);
        }
    }

    static void removeUser(String username) {
        users.remove(username);
    }

    static void registerUser(String username, String password, String sport) {
        addUser(new User(username, password, sport));
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

}
