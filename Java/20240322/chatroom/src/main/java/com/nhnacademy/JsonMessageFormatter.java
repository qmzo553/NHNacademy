package com.nhnacademy;

import org.json.JSONObject;

public class JsonMessageFormatter {

    private JsonMessageFormatter() {
        throw new IllegalStateException("Utility class");
      }
    
    public static JSONObject transRequestFormat(String str) {
        JSONObject message = new JSONObject();
        String[] tokens = str.split(" ");

        int id = Integer.parseInt(tokens[0]);
        String type = tokens[1];
        String clientId = tokens[2];

        
        message.put("id", id);
        message.put("type", type);
        message.put("clientId", clientId);

        return message;
    }

    public static JSONObject transResponseFormat(String str) {
        JSONObject message = new JSONObject();
        String[] tokens = str.split(" ");

        int id = Integer.parseInt(tokens[0]);
        String type = tokens[1];
        String response = tokens[2];
        String clientId = tokens[3];

        message.put("id", id);
        message.put("type", type);
        message.put("response", response);
        message.put("clientId", clientId);

        return message;
    }

}
