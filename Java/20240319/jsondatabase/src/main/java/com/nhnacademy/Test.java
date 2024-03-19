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

        while (true) {
            System.out.print("command : ");
            String line = reader.readLine();

            if(line.equals("quit") || line.equals("0")) {
                break;
            }

            System.out.println("input command : " + line);

            String[] tokens = line.split(" ");
            if(tokens[0].equals("user") && tokens[1].equals("add")) {
                User newUser = new User(tokens[2], tokens[3]);
                JSONObject userObject = new JSONObject(newUser);
                userArray.put(userObject);
                System.out.println("사용자" + tokens[3] + "가 추가되었습니다.");
                System.out.println(userArray);
            } else if(tokens[0].equals("user") && tokens[1].equals("del")) {
                if(userArray != null) {
                    for(int i = 0; i < userArray.length(); i++) {
                        JSONObject value = userArray.getJSONObject(i);
                        Object userId = value.get("userId");
                        if((userId instanceof String) && userId.equals(tokens[2])) {
                            userArray.remove(i);
                        }
                    }
                }
                System.out.println(userArray);
            }
        }

    }
}
