package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    JSONObject dataBase;
    List<User> users;
    List<Item> items;
    MatchHistory matchHistory;
    List<ChangeLog> changeLogList;

    public DataBase() {
        dataBase = new JSONObject();
        this.users = new ArrayList<>();
        this.items = new ArrayList<>();
        this.changeLogList = new ArrayList<>();
        matchHistory = new MatchHistory();
    }

    public void save(String path) {
        File file = new File(path);
        if(file.exists()) {
            read(path);
        }
        
        try (FileWriter jsonFileWriter = new FileWriter(path)) {
            dataBase.put("users", users);
            dataBase.put("items", items);
            dataBase.put("log", changeLogList);
            jsonFileWriter.write(dataBase.toString(3));
            jsonFileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        users.add(user);
        addToChangeLog("user add : " + user.getUserId());
    }

    public void deletedUser(User user) {
        users.remove(user);
        addToChangeLog("user del : " + user.getUserId());
    }

    public void addItem(Item item) {
        items.add(item);
        addToChangeLog("item add : " + item.getItemId());
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
                    itemObject.getInt("attackSpeed")
                ));
        }

        JSONArray changeLogArray = dataBase.getJSONArray("log");
        for(int i = 0; i < changeLogArray.length(); i++) {
            JSONObject changeLogObject = changeLogArray.getJSONObject(i);
            changeLogList.add(new ChangeLog(
                    changeLogObject.getString("log"),
                    changeLogObject.getString("timeStamp")
                ));
        }

    }

    private void addToChangeLog(String log) {
        changeLogList.add(new ChangeLog(log, LocalDateTime.now().toString()));
    }

    public void printChangeLog() {
        System.out.println("변경 이력 : ");
        for (ChangeLog log : changeLogList) {
            System.out.println(log);
        }
    }

    public void printMatches() {
        System.out.println(matchHistory.getMatches());
    }

    public void printWins() {
        System.out.println(matchHistory.getWins());
    }
}
