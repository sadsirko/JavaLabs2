package com.sadsirko.lab23.model.entity;

public class PrintCenter {
    private int id;
    private String name;
    private int price;
    private int themeId;


    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThemeId() {
        return themeId;
    }

    @Override
    public String toString() {
        return "PrintCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", themeId=" + themeId +
                '}';
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }
}
