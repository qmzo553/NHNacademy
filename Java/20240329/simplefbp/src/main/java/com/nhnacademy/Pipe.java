package com.nhnacademy;

import java.util.HashSet;
import java.util.PriorityQueue;

public class Pipe {
    private static HashSet<String> pipeId = new HashSet<>();
    private String identifier;
    private PriorityQueue<Message> messageList;
    private int messageListSize;

    public Pipe(int messageListSize ,String identifier) {
        if(pipeId.contains(identifier)) {
            throw new IllegalArgumentException();
        }

        this.messageListSize = messageListSize;
        this.identifier = identifier;
        this.messageList =  new PriorityQueue<>();
        pipeId.add(identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    public PriorityQueue<Message> getMessageList() {
        return messageList;
    }

    public boolean isEmptyMessageList() {
        synchronized(messageList) {
            return messageList.isEmpty();
        }
    }

    public void send(Message message) {
        synchronized (messageList) { 
            if (messageList.size() <= messageListSize) {
                messageList.add(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public Message receive() {
        synchronized(messageList) {
            return messageList.poll();
        }
    }
}
