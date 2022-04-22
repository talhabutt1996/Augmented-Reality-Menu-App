package com.example.augmentedreality;

class Dish {
    public  String name;
    public String desc;
    public String image;
    public String price;

    public Dish() {
    }

    public Dish(String name, String desc, String image, String price) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
