package com.upmc.dar.apps.sports;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;
import org.json.JSONObject;

/**
 * Created by mohameddd on 3/25/16.
 */
public class IsLogged extends IApplication {

    @Override
    protected HttpResponse doGet(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        User user = (User) request.getSession().getAttribute("user");

        JSONObject rootObject = new JSONObject();
        rootObject.put("error","none");
        rootObject.put("isLogged",(user != null));

        response.addHeader("Content-Type", "application/json");
        response.setBody(rootObject.toString());

        return response;
    }
}
