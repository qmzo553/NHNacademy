package com.nhnacademy.hello.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class SessionListener implements HttpSessionListener {
    private static Logger log = LoggerFactory.getLogger(SessionListener.class.getName());
    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        int sessionCounter = atomicInteger.incrementAndGet();
        log.error("session-counter : {}", sessionCounter);
    }
}
