package com.nhnacademy;

public class NumberMessage extends Message {

    protected NumberMessage(int message, String identifier) {
        super(message, identifier);
    }

    public int getMessage(int message) {
        return message;
    }
}
