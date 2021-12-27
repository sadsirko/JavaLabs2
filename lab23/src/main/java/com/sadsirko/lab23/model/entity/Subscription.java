package com.sadsirko.lab23.model.entity;

public class Subscription {
    private int id;
    private int readerId;
    private int printCenterId;

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", readerId=" + readerId +
                ", printCenterId=" + printCenterId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getPrintCenterId() {
        return printCenterId;
    }

    public void setPrintCenterId(int printCenterId) {
        this.printCenterId = printCenterId;
    }
}
