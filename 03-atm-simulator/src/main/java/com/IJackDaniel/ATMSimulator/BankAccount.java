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

    // Setters
    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    // Getters
    public double getBalance(){
        return balance;
    }
}
