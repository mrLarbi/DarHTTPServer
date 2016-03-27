package com.upmc.dar.apps.sports;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;
import com.upmc.dar.http.template.SimpleTemplate;

import java.util.HashMap;

/**
 * Created by mohameddd on 3/26/16.
 */
public class Home extends IApplication {

    @Override
    protected HttpResponse doGet(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "text/html");

        HashMap<String, String> ref = new HashMap<String, String>();

        User user = (User) request.getSession().getAttribute("user");

        SimpleTemplate temp = new SimpleTemplate("html");
        String body = "";



        try {
            if(user == null) {
                body = temp.transform("sports_home", ref);
            }
            else {
                ref.put("username", user.getUsername());
                ref.put("sport", "" + user.getSport());
                body = temp.transform("sports_users", ref);
            }

        } catch (Exception e) {
            return doBad(request);
        }

        response.setBody(body);

        return response;
    }
}
