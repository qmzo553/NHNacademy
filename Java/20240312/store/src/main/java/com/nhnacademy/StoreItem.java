package com.nhnacademy;

public enum StoreItem {
    
    APPLE("Apple"),
    BANANA("Banana"),
    ORANGE("Orange"),
    MILK("Milk"),
    BREAD("Bread"),
    EGGS("Eggs"),
    POTATO("Potato"),
    TOMATO("Tomato"),
    CHICKEN("Chicken"),
    RICE("Rice");

    private final String itemName;

    StoreItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
