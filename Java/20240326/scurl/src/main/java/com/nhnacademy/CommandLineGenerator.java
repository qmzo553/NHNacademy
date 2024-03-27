package com.nhnacademy;

public class CommandLineGenerator {

    private CommandLineGenerator() {
    }

    public static String generate(String method, String location, String version, String host) {
        StringBuilder cmd = new StringBuilder();
        cmd.append(String.format("%s %s %s", method, location, version));
        cmd.append("\r\nHost: " + host + "\r\n");
        cmd.append("Connection: close\r\n\r\n");
        return cmd.toString();
    }

    public static String generate(String method, String location, String version, String host, String value) {
        StringBuilder cmd = new StringBuilder();
        cmd.append(String.format("%s %s %s", method, location, version));
        cmd.append("\r\nHost: " + host + "\r\n");
        cmd.append("Connection: close\r\n");
        cmd.append(value);
        cmd.append("\r\n\r\n");
        return cmd.toString();
    }
}
