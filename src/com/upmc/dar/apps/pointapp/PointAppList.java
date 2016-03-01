package com.upmc.dar.apps.pointapp;

import com.upmc.dar.apps.IApplication;
import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;

/**
 * Created by mohameddd on 3/4/16.
 */
public class PointAppList extends IApplication {


    @Override
    protected HttpResponse doGet(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        response.addHeader("Content-Type", "text/plain");
        response.setBody(PointApp.pointsToString());

        return response;
    }

    protected HttpResponse doHead(HttpRequest request) {
        return notImplemented();
    }

    protected HttpResponse doPost(HttpRequest request) {
        return notImplemented();
    }

    protected HttpResponse doOptions(HttpRequest request) {
        return notImplemented();
    }

    protected HttpResponse doPut(HttpRequest request) {
        return notImplemented();
    }

    protected HttpResponse doDelete(HttpRequest request) {
        return notImplemented();
    }

    protected HttpResponse doTrace(HttpRequest request) {
        return notImplemented();
    }

    protected HttpResponse doBad(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.setStatus(400);
        return response;
    }

    private HttpResponse notImplemented() {
        HttpResponse response = new HttpResponse();
        response.setStatus(501);
        return response;
    }
}
