package com.IJackDaniel.ATMSimulator;

public class BankAccount {
    private double balance;
    private String login;
    private String password;

    public BankAccount() {
        balance = 100;
        login = "User";
        password = "12345";
    }

    public BankAccount(double balance, String login, String password) {
        this.balance = balance;
        this.login = login;
        this.password = password;
    }

    // Check login and password
    public boolean checkLoginAndPassword(String login, String password) {
        return (login.equals(this.login) && password.equals(this.password));
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    // Getters
    public double getBalance(){
        return balance;
    }
}
