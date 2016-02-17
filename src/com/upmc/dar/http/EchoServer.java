package com.upmc.dar.http;

public class EchoServer implements IApplication {

    public HttpResponse doGet(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        String contentType = request.getHeader("content-type");
        switch (contentType) {
            case "text/plain":
                response.setBody(request.toString());
            case "text/html":
                response.setBody(request.toHTML());
            case "application/json":
                response.setBody(request.toJSON());
            default:
                response.setBody("Format not supported : " + contentType);
        }
        return response;
    }

    public HttpResponse doHead(HttpRequest request) {
        return notImplemented();
    }

    public HttpResponse doPost(HttpRequest request) {
        return notImplemented();
    }

    public HttpResponse doOptions(HttpRequest request) {
        return notImplemented();
    }

    public HttpResponse doPut(HttpRequest request) {
        return notImplemented();
    }



    public HttpResponse doDelete(HttpRequest request) {
        return notImplemented();
    }

    public HttpResponse doTrace(HttpRequest request) {
        return notImplemented();
    }

    private HttpResponse notImplemented() {
        HttpResponse response = new HttpResponse();
        response.setBody("Not Implemented");
        response.setStatus(501);
        return response;
    }
}
