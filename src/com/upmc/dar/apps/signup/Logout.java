package com.upmc.dar.apps.signup;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;
import com.upmc.dar.utilities.Utilities;

/**
 * Created by mohameddd on 3/25/16.
 */
public class Logout extends IApplication {

    @Override
    protected HttpResponse doPost(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "text/plain");

        boolean valid = true;
        String message = "Good bye :)";

        if(!request.getSession().isAttribute("user")) {
            valid = false;
            message = "Not logged, dou ahou";
        }

        Utilities.textResponse(valid, message, response);

        if(valid) {
            request.getSession().removeAttribute("user");
        }

        return response;
    }
}
