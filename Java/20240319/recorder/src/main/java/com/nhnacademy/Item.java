package com.nhnacademy;

public class Item {

    String itemId;
    String name;
    int energy;
    int power;
    int defense;
    int movingSpeed;
    int attackSpeed;

    public Item(String itemId, String name, int energy, int power, int defense, int movingSpeed, int attackSpeed) {
        this.itemId = itemId;
        this.name = name;
        this.energy = energy;
        this.power = power;
        this.defense = defense;
        this.movingSpeed = movingSpeed;
        this.attackSpeed = attackSpeed;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setMovingSpeed(int movingSpeed) {
        this.movingSpeed = movingSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public int getPower() {
        return power;
    }

    public int getDefense() {
        return defense;
    }

    public int getMovingSpeed() {
        return movingSpeed;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public String toString() {
        return getItemId() + "  " + getName() + "    " + getEnergy() + "    " + getPower() + "    " + getDefense() + "    " + getDefense() + "    "
                + getMovingSpeed() + "    " + getAttackSpeed();
    }
}
