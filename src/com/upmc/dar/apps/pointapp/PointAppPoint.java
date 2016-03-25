package com.upmc.dar.apps.pointapp;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;

import java.awt.*;

/**
 * Created by mohameddd on 3/4/16.
 */
public class PointAppPoint extends IApplication {

    @Override
    protected HttpResponse doGet(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "text/plain");

        int id = Integer.valueOf(getParam(2));
        String type = getParam(3);

        if("x".equals(type)) {
            response.setBody("X : " + PointApp.getPointX(id));
        }

        if("y".equals(type)) {
            response.setBody("Y : " + PointApp.getPointY(id));
        }

        return response;
    }

    protected HttpResponse doPost(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "text/plain");

        String body = request.getBody();

        String[] lines = body.split("&");
        String x = null;
        String y = null;
        if(lines[0].charAt(0) == 'x') {
            x = lines[0].split("=")[1];
        }
        if(lines[1].charAt(0) == 'x') {
            x = lines[1].split("=")[1];
        }
        if(lines[0].charAt(0) == 'y') {
            y = lines[0].split("=")[1];
        }
        if(lines[1].charAt(0) == 'y') {
            y = lines[1].split("=")[1];
        }

        System.out.println(x);
        System.out.println(y);
        
        if(x != null && y != null) {
            PointApp.addPoint(Integer.valueOf(x), Integer.valueOf(y));
            response.setBody("OK");
        }
        else {
            response.setBody("KO");
        }

        return response;
    }

    protected HttpResponse doPut(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "text/plain");
        int id = Integer.valueOf(getParam(2));     

        String x = request.getParameter("x");
        String y = request.getParameter("y");

        if(x != null && y != null) {
            PointApp.modifyPointXY(id, Integer.valueOf(x), Integer.valueOf(y));
            response.setBody("OK");
        }
        else if (x != null) {
            PointApp.modifyPointX(id, Integer.valueOf(x));
            response.setBody("OK");
        }
        else if (y != null) {
            PointApp.modifyPointY(id, Integer.valueOf(y));
            response.setBody("OK");
        }
        else {
            response.setBody("KO");
        }

        return response;
    }

    protected HttpResponse doDelete(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "text/plain");
        int id = Integer.valueOf(getParam(2));
        PointApp.deletePoint(id);

        return response;
    }
}
