package com.nhnacademy;

public class DataExtractor {
    String header;
    String data;
    String body;

    public DataExtractor(String data) {

        this.data = data;
        parse();
    }

    public void parse() {
        // header
        header = data.substring(0, data.indexOf("\r\n"));
        // body
        body = data.substring(data.indexOf("\r\n"));
    }

    public String getBody() {
        return body;
    }

}
