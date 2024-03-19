package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;

public class Exam06 {
    
    public static void main(String[] args) {
        String[] items = new String[] {"갈매기", "참새", "펭귄"};
        String[] items2 = new String[] {"사자", "호랑이", "말"};

        JSONObject animal = new JSONObject();
        JSONObject birds = new JSONObject();
        JSONObject mammalia = new JSONObject();
        JSONArray animalsArray = new JSONArray();
        JSONArray birdsArray = new JSONArray();
        JSONArray mammaliaArray= new JSONArray();

        for(String item : items) {
            birdsArray.put(item);
        }
        birds.put("조류", birdsArray);

        for(String item : items2) {
            mammaliaArray.put(item);
        }
        mammalia.put("포유류", mammaliaArray);

        animalsArray.put(birds);
        animalsArray.put(mammalia);

        animal.put("동물", animalsArray);

        System.out.println(animal);
    }
}