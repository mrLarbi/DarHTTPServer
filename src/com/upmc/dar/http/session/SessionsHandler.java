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
        String hash = makeHash(ipAddress);
        HttpSession session = new HttpSession();
        sessions.put(hash, session);
        resp.addHeader("Set-Cookie", hash);
        req.setSession(session);
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
        return ipAddress;
    }
}
