package com.sadsirko.lab23.model.entity;

public class Role {
    private String name;
    private int id;

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
