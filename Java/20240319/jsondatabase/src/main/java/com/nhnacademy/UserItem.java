package com.nhnacademy;

public class UserItem {
    
    int itemId;
    String model;
    int health;
    int power;
    int defense;
    int speed;
    int attackSpeed;

    public UserItem(int itemId, String model) {
        this.itemId = itemId;
        this.model = model;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    public int getItemId() {
        return itemId;
    }

    public String getModel() {
        return model;
    }
}
