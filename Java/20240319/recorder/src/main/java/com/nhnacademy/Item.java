package com.nhnacademy;

public class Item {
    
    String itemId;
    String model;
    int energy;
    int power;
    int defense;
    int speed;
    int attackSpeed;

    public Item(String itemId, String model, int energy, int power, int defense, int speed, int attackSpeed) {
        this.itemId = itemId;
        this.model = model;
        this.energy = energy;
        this.power = power;
        this.defense = defense;
        this.speed = speed;
        this.attackSpeed = attackSpeed;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setHealth(int energy) {
        this.energy = energy;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
    
    public String getItemId() {
        return itemId;
    }

    public String getModel() {
        return model;
    }

    public int getHealth() {
        return energy;
    }

    public int getPower() {
        return power;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }
}
