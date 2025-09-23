package com.IJackDaniel.ATMSimulator;

public class BankAccount {
    private double balance;

    public BankAccount() {
        balance = 100;
    }

    // Setters
    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    // Getters
    public double getBalance(){
        return balance;
    }
}
