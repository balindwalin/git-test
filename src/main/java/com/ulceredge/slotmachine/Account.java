package com.ulceredge.slotmachine;

public class Account {
    
    private int balance = 0;
    private static final Account instance = new Account();
    
    private Account() {}
    
    public static Account getInstance() {
        return instance;
    }
    
    public synchronized boolean deposit(int amount) {
        if (this.balance + amount < 0)
            return false;
        balance += amount;
        return true;
    }
    
    public synchronized int getBalance() {
        return this.balance;
    }
    
}
