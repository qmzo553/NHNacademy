package com.nhnacademy;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam04 {
    public static void main(String[] args) {
        try {
            String jsonText = "{\"city\" : \"Seongnam\"}";
            String jsonText2 = "{\"name\" : \"nhn\"}";
            JSONObject object = new JSONObject(jsonText2);
            JSONObject object2 = new JSONObject(jsonText);

            object2.put("code", 13487);
            object.put("address", object2);

            System.out.println(object);
        } catch(JSONException e) {
            System.err.println(e.getMessage());
        }
    }
}
