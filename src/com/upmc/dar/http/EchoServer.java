package com.upmc.dar.http;

public class EchoServer implements IApplication {

    public HttpResponse doGet(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        String contentType = request.getContentType();
        switch (contentType) {
            case "text/plain":
                return response.body(request.toString());
            case "text/html":
                return response.body(request.toHTML());
            case "application/json":
                return response.body(request.toJSON());
            default:
                response.setBody("Format not supported : " + contentType);
        }
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
