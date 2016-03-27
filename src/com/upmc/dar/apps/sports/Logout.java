package com.upmc.dar.apps.sports;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;
import org.json.JSONObject;

/**
 * Created by mohameddd on 3/25/16.
 */
public class Logout extends IApplication{
    @Override
    protected HttpResponse doPost(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "application/json");

        String valid = "none";

        if(!request.getSession().isAttribute("user")) {
            valid = "NotLogged";
        }

        JSONObject rootObject = new JSONObject();
        rootObject.put("error", valid);
        response.setBody(rootObject.toString());

        if("none".equals(valid)) {
            User user = (User) request.getSession().getAttribute("user");
            request.getSession().removeAttribute("user");
            user.logOut();
        }

        return response;
    }
}
