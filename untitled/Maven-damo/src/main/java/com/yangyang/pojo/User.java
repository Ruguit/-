package com.yangyang.pojo;

public class User {
    private String name;
    private String cardId;
    private String password;
    private String userName;
    private double money;

    public User() {
    }

    public User( String name, String cardId, String password, String userName, double money) {
        this.name = name;
        this.cardId = cardId;
        this.password = password;
        this.userName = userName;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
