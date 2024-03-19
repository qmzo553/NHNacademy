package com.nhnacademy;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam04 {
    public static void main(String[] args) {
        try {
            JSONObject customer = new JSONObject();
            
            customer.put("name", "nhn");
            customer.put("age", 20);

            System.out.println(customer);

            Object nameObject = customer.get("name");
            System.out.println("Name type : " + nameObject.getClass().getTypeName());
            if(nameObject instanceof String) {
                System.out.println("Name is String");
            }

            Object ageObject = customer.get("age");
            System.out.println("Age type : " + ageObject.getClass().getTypeName());
        } catch(JSONException e) {
            System.err.println(e.getMessage());
        }
    }
}
