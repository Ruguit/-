package com.yangyang.atm;

/**
 * 账户类
 * @author 阳洋
 * @version IDEA
 */
public class Accout {
    /**
     *成员变量，私有
     */
    private String cardId;
    private String userName;
    private String passWord;
    private double money;
    private double quotaMoney;

    public Accout(){}

    public Accout(String cardId, String userName, String passWord, double money, double quotaMoney) {
        this.cardId = cardId;
        this.userName = userName;
        this.passWord = passWord;
        this.money = money;
        this.quotaMoney = quotaMoney;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getQuotaMoney() {
        return quotaMoney;
    }

    public void setQuotaMoney(double quotaMoney) {
        this.quotaMoney = quotaMoney;
    }
}
