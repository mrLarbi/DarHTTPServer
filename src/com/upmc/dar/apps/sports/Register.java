package com.upmc.dar.apps.sports;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.apps.signup.*;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;
import org.json.JSONObject;

/**
 * Created by mohameddd on 3/25/16.
 */
public class Register extends IApplication {

    @Override
    protected HttpResponse doPost(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "application/plain");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sport = request.getParameter("sport");

        String valid = "none";

        if(username == null) {
            valid = "noUsername";
        }
        if(password == null) {
            valid = "noPassword";
        }
        if(sport == null) {
            valid = "noSport";
        }

        if("none".equals(valid)) {
            if(!Sports.isUser(username)) {
                User user = new User(username, password, sport);
                Sports.addUser(user);
                request.getSession().setAttribute("user", user);
                user.logIn();
            }
            else {
                valid = "alreadyUsed";
            }
        }

        JSONObject rootObject = new JSONObject();
        rootObject.put("error", valid);
        response.setBody(rootObject.toString());

        return response;
    }
}
