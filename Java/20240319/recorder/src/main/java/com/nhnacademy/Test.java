package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        JSONArray userArray = new JSONArray();
        JSONArray itemArray = new JSONArray();

        while (true) {
            System.out.print("command : ");
            String line = reader.readLine();

            if (line.equals("quit") || line.equals("0")) {
                break;
            }

            System.out.println("input command : " + line);

            String[] tokens = line.split(" ");
            if (tokens[0].equals("user") && tokens[1].equals("add")) {
                User newUser = new User(tokens[2], tokens[3]);
                JSONObject userObject = new JSONObject(newUser);
                userArray.put(userObject);
                System.out.println("사용자" + tokens[3] + "가 추가되었습니다.");
                System.out.println(userArray);
            } else if (tokens[0].equals("user") && tokens[1].equals("del")) {
                if (userArray != null) {
                    for (int i = 0; i < userArray.length(); i++) {
                        JSONObject value = userArray.getJSONObject(i);
                        Object userId = value.get("userId");
                        if ((userId instanceof String) && userId.equals(tokens[2])) {
                            userArray.remove(i);
                        }
                    }
                }
                
                System.out.println("사용자" + tokens[2] + "가 삭제되었습니다.");
                System.out.println(userArray);
            }

            if (tokens[0].equals("item") && tokens[1].equals("add")) {
                int health = Integer.parseInt(tokens[4]);
                int power = Integer.parseInt(tokens[5]);
                int defense = Integer.parseInt(tokens[6]);
                int speed = Integer.parseInt(tokens[7]);
                int attackSpeed = Integer.parseInt(tokens[8]);
                Item newItem = new Item(tokens[2], tokens[3], health, power,
                        defense, speed, attackSpeed);
                
                JSONObject itemObject = new JSONObject(newItem);
                itemArray.put(itemObject);

                System.out.println("아이템" + tokens[3] + "가 추가되었습니다.");
                System.out.println(itemObject);
            } else if (tokens[0].equals("item") && tokens[1].equals("del")) {
                if (itemArray != null) {
                    for (int i = 0; i < itemArray.length(); i++) {
                        JSONObject value = itemArray.getJSONObject(i);
                        Object itemId = value.get("itemId");
                        if ((itemId instanceof String) && itemId.equals(tokens[2])) {
                            itemArray.remove(i);
                        }
                    }
                }

                System.out.println("아이템" + tokens[2] + "가 삭제되었습니다.");
                System.out.println(itemArray);
            }
        }

    }
}
