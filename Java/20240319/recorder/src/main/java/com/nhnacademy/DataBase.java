package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    JSONObject dataBase;
    List<User> users;
    List<Item> items;

    public DataBase() {
        dataBase = new JSONObject();
        this.users = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void save(String path) {
        File file = new File(path);
        if(file.exists()) {
            read(path);
        }
        
        try (FileWriter jsonFileWriter = new FileWriter(path)) {
            dataBase.put("users", users);
            dataBase.put("items", items);
            jsonFileWriter.write(dataBase.toString(3));
            jsonFileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void deletedUser(User user) {
        users.remove(user);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void printUsers() {
        System.out.println("ID  NAME");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void printItems() {
        System.out.println("ID   NAME   ENERGY  POWER  DEFENSE  MOVING_SPEED  ATTACK_SPEED");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public void read(String path) {
        // 파일 읽기
        try (FileReader jsonFileReader = new FileReader(path)) {
            StringBuilder builder = new StringBuilder();
            int ch;
            while ((ch = jsonFileReader.read()) != -1) {
                builder.append((char) ch);
            }

            dataBase = new JSONObject(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 객체에 입력
        JSONArray userArray = dataBase.getJSONArray("users");
        for (int i = 0; i < userArray.length(); i++) {
            JSONObject userObject = userArray.getJSONObject(i);
            users.add(new User(
                    userObject.getString("userId"), userObject.getString("nickName")));
        }

        JSONArray itemArray = dataBase.getJSONArray("items");
        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject itemObject = itemArray.getJSONObject(i);
            items.add(new Item(
                    itemObject.getString("itemId"),
                    itemObject.getString("name"),
                    itemObject.getInt("energy"),
                    itemObject.getInt("power"),
                    itemObject.getInt("defense"),
                    itemObject.getInt("movingSpeed"),
                    itemObject.getInt("attackSpeed")));
        }

    }
}
