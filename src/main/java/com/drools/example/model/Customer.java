package com.drools.example.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private CustomerType type;
    private float balance;
    private List<String> nudges;

    public Customer(CustomerType type, float balance) {
        super();
        this.type = type;
        this.balance = balance;
        this.nudges = new ArrayList<>();
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

    public List<String> getNudges() {
        return this.nudges;
    }

    public void setNudge(String nudge) {
        this.nudges.add(nudge);
    }

    public enum CustomerType {
        BB, SME;
    }
}
