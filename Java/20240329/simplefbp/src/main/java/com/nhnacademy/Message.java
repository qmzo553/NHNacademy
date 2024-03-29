package com.nhnacademy;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

public class Message implements Comparable<Message> {
    protected static HashSet<String> messageId = new HashSet<>();
    protected Object message;
    protected String identifier;
    protected LocalDateTime createTime; 

    protected Message(Object message, String identifier) {
        if(messageId.contains(identifier)) {
            throw new IllegalArgumentException();
        }

        this.message = message;
        this.identifier = identifier;
        this.createTime = LocalDateTime.now();
        messageId.add(identifier);
    }

    public Object getMessage() {
        return message;
    }

    public String getIdentifier() {
        return identifier;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @Override
    public int compareTo(Message otherMessage) {
        return this.createTime.compareTo(otherMessage.getCreateTime());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Message other = (Message) obj;
        return Objects.equals(identifier, other.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return message.toString();
    }
}
