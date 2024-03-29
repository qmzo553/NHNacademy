package com.nhnacademy;

public class StringMessage extends Message {

    protected StringMessage(String message, String identifier) {
        super(message, identifier);
    }

    @Override
    public String getMessage() {
        return (String) message;
    }
    
}
