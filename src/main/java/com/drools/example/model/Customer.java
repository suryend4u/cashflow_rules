package com.drools.example.model;

public class Customer {

    private CustomerType type;
    private float balance;
    private String nudge;

    public Customer(CustomerType type, float balance) {
        super();
        this.type = type;
        this.balance = balance;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getNudge() {
        return nudge;
    }

    public void setNudge(String nudge) {
        this.nudge = new String(nudge);
    }

    public enum CustomerType {
        BB, SME;
    }
}
