package com.upmc.dar.apps.signup;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;
import com.upmc.dar.utilities.Utilities;

/**
 * Created by mohameddd on 3/12/16.
 */
public class Login extends IApplication {

    @Override
    protected HttpResponse doPost(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "text/plain");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean valid = true;
        String message = "Welcome back :)";

        if(request.getSession().isAttribute("user")) {
            valid = false;
            message = "Already logged";
        }

        if(username == null) {
            valid = false;
            message = "No username";
        }
        if(password == null) {
            valid = false;
            message = "No password";
        }

        Utilities.textResponse(valid, message, response);

        if(valid) {
            User user = SignUp.logUser(username, password);
            request.getSession().setAttribute("user", user);
        }

        return response;
    }
}
