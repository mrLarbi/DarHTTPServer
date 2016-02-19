package com.upmc.dar.http;

public interface IApplication {

    public HttpResponse doGet(HttpRequest request);
    public HttpResponse doHead(HttpRequest request);
    public HttpResponse doPost(HttpRequest request);
    public HttpResponse doOptions(HttpRequest request);
    public HttpResponse doPut(HttpRequest request);
    public HttpResponse doDelete(HttpRequest request);
    public HttpResponse doTrace(HttpRequest request);

}
