package com.upmc.dar.apps.sports;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mohameddd on 3/25/16.
 */
public class GetUsers extends IApplication {

    @Override
    protected HttpResponse doGet(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "application/json");

        JSONObject rootObject = new JSONObject();
        rootObject.put("error", "none");
        JSONArray users = new JSONArray();
        for(User user : Sports.listUsers()) {
            users.put(user.toJSON());
        }

        rootObject.put("users", users);

        response.setBody(rootObject.toString());

        return response;
    }
}
