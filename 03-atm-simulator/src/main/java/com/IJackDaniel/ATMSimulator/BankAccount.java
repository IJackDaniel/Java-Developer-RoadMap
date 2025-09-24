package com.IJackDaniel.ATMSimulator;

import java.util.ArrayList;

public class BankAccount {
    private double balance;
    private String login;
    private String password;
    private ArrayList<String> historyOfOperations;

    public BankAccount() {
        this.balance = 100;
        this.login = "User";
        this.password = "12345";
        this.historyOfOperations = new ArrayList<>();
    }

    public BankAccount(double balance, String login, String password) {
        this.balance = balance;
        this.login = login;
        this.password = password;
        this.historyOfOperations = new ArrayList<>();
    }

    // Check login and password
    public boolean checkLoginAndPassword(String login, String password) {
        return (login.equals(this.login) && password.equals(this.password));
    }

    // Deposin money to balance
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addOperation("Пополнение на " + amount + " Рублей. Баланс: " + getBalance());
        }
    }

    // Withdraw money from balance
    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            addOperation("Снятие " + amount + " Рублей. Баланс: " + getBalance());
        }
    }

    // Add operation to history
    public void addOperation(String operation) {
        this.historyOfOperations.add(operation);
    }

    // Check for history of operations
    public boolean isHistoryEmpty() {
        return this.historyOfOperations.isEmpty();
    }

    // Getters
    public double getBalance(){
        return this.balance;
    }

    public ArrayList<String> getHistoryOfOperations() {
        return this.historyOfOperations;
    }
}
