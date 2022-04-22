package com.example.augmentedreality;

public class FoodData {
    private String itemName;
    private String itemDescription;
    private String itemprice;
    private String itemimage;
    private String key;

    public FoodData() {}

    public FoodData(String itemName, String itemDescription, String itemprice, String itemimage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemprice = itemprice;
        this.itemimage = itemimage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemprice() {
        return itemprice;
    }

    public String getItemimage() {
        return itemimage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
