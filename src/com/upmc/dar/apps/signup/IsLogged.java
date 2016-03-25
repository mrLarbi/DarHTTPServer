package com.upmc.dar.apps.signup;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;

/**
 * Created by mohameddd on 3/20/16.
 */
public class IsLogged extends IApplication {

    @Override
    protected HttpResponse doGet(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        User user = (User) request.getSession().getAttribute("user");

        response.addHeader("Content-Type", "text/plain");
        response.setBody("" + (user != null));

        return response;
    }
}
