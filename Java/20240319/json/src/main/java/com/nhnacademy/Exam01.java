package com.nhnacademy;

import org.json.JSONObject;

public class Exam01 {
    
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        JSONObject object2 = new JSONObject();

        object2.put("code", 13487);
        object2.put("city", "Seongnam");
        object.put("address", object2);
        object.put("name", "nhn");

        System.out.println(object);
    }
}
