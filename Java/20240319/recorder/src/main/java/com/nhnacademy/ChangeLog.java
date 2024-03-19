package com.nhnacademy;

public class ChangeLog {
    String log;
    String timeStamp;

    public ChangeLog(String log, String timeStamp) {
        this.log = log;
        this.timeStamp = timeStamp;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLog() {
        return log;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "[" + timeStamp + "] " + log;
    }
}
