package com.nhnacademy;

public enum StoreItem {
    
    APPLE("Apple", 5),
    BANANA("Banana", 10),
    ORANGE("Orange", 7),
    MILK("Milk", 4),
    BREAD("Bread", 3),
    EGGS("Eggs", 2),
    POTATO("Potato", 1),
    TOMATO("Tomato", 8),
    CHICKEN("Chicken", 9),
    RICE("Rice", 6);

    private String itemName;
    private int maxItemNum;

    StoreItem(String itemName, int maxItemNum) {
        this.itemName = itemName;
        this.maxItemNum = maxItemNum;
    }

    public String getItemName() {
        return itemName;
    }

    public int getMaxItemNum() {
        return maxItemNum;
    }
}
