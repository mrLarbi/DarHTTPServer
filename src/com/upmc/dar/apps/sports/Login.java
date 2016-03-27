package com.upmc.dar.apps.sports;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;
import org.json.JSONObject;

/**
 * Created by mohameddd on 3/25/16.
 */
public class Login extends IApplication {

    @Override
    protected HttpResponse doPost(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "application/json");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String valid = "none";

        if(request.getSession().isAttribute("user")) {
            valid = "alreadyLogged";
        }

        if(username == null) {
            valid = "noUsername";
        }
        if(password == null) {
            valid = "noPassword";
        }

        if("none".equals(valid)) {
            User user = Sports.logUser(username, password);
            if(user != null) {
                request.getSession().setAttribute("user", user);
                user.logIn();
            }
            else {
                valid = "notRegistered";
            }
        }

        JSONObject rootObject = new JSONObject();
        rootObject.put("error", valid);
        response.setBody(rootObject.toString());

        return response;
    }
}
