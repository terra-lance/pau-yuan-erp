package com.terrase.frame.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
    	synchronized(sessions) {
    		HttpSession session = event.getSession();
    		sessions.put(session.getId(), session);
    	}
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    	synchronized(sessions) {
    		sessions.remove(event.getSession().getId());
    	}
    }

    public static HttpSession find(String sessionId) {
    	synchronized(sessions) {
    		return sessions.get(sessionId);
    	}
    }

}