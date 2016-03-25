package com.upmc.dar.apps.signup;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;

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

        if(username == null) {
            valid = false;
            message = "No username";
        }
        if(password == null) {
            valid = false;
            message = "No password";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(valid);
        builder.append('\n');
        builder.append(message + "\n");

        response.setBody(builder.toString());

        return response;
    }
}
