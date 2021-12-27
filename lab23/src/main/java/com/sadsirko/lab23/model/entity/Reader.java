package com.sadsirko.lab23.model.entity;

public class Reader {

    private String name;
    private int balance;
    private int id;
    private boolean status;
    private int personId;

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", id=" + id +
                ", status=" + status +
                ", personId=" + personId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
