package com.upmc.dar.http.session;

import com.upmc.dar.http.HttpRequest;
import com.upmc.dar.http.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mohameddd on 3/11/16.
 */
public class SessionsHandler {

    private HashMap<String, HttpSession> sessions;
    private static final long sessionLifeTime = 2592000;

    public SessionsHandler() {
        sessions = new HashMap<>();
    }

    public boolean isSession(String cookie) {
        return sessions.containsKey(cookie);
    }

    public HttpSession getSession(String cookie) {
        if(isSession(cookie)) {
            return sessions.get(cookie);
        }
        else {
            return new HttpSession();
        }
    }

    public String createSession(String ipAddress, HttpRequest req, HttpResponse resp) {
        //String hash = makeHash(ipAddress, req.getHeader("User-Agent"));
        String hash = makeHash(ipAddress);
        sessions.put(hash, req.getSession());
        resp.addHeader("Set-Cookie", hash);
        Timer timer = new Timer();
        timer.schedule(new RemindTask(this, hash), sessionLifeTime * 1000);
        return hash;
    }

    class RemindTask extends TimerTask {

        SessionsHandler handler;
        String cookie;

        public RemindTask(SessionsHandler handler, String cookie) {
            this.handler = handler;
            this.cookie = cookie;
        }

        public void run() {
            handler.removeSession(cookie);
        }
    }

    public void removeSession(String cookie) {
        sessions.remove(cookie);
    }

    private String makeHash(String ipAddress) {
        StringBuilder builder = new StringBuilder();
        builder.append("sessionToken=" + ipAddress + System.currentTimeMillis());
        return builder.toString();
    }

    private String makeHash(String ipAddress, String userAgent) {
        StringBuilder builder = new StringBuilder();
        String[] agents = userAgent.split(" ");
        String userAgentCookie = "Dummy";
        if(agents.length > 0) {
            userAgentCookie = agents[0];
        }
        builder.append("sessionToken=" + ipAddress + userAgentCookie);
        return builder.toString();
    }

    public void print() {
        for(String key : sessions.keySet()) {
            System.out.println(key + " : " + sessions.get(key));
        }
    }
}
