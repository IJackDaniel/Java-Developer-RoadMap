package com.IJackDaniel.ATMSimulator;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            double userBalance = bankAccount.getBalance();

            printMenu();
            int userChoice = 0;
            do {
                String newLine = scanner.nextLine();
                try {
                    userChoice = Integer.parseInt(newLine);
                } catch (NumberFormatException e) {
                    userChoice = 0;
                }
                if (userChoice > 4 || userChoice < 1) System.out.print("Некорректный выбор!\nНовый выбор: ");
            } while (userChoice > 4 || userChoice < 1);

            double amount = 0.0;
            switch (userChoice) {
                case 1:
                    System.out.print("Введите сумму для пополнения: ");
                    do {
                        String newLine = scanner.nextLine();
                        try {
                            amount = Double.parseDouble(newLine);
                        } catch (NumberFormatException e) {
                            amount = 0.0;
                        }
                        if (amount <= 0.0) System.out.print("Некорректная сумма для пополнения! Новый ввод: ");
                    } while (amount <= 0.0);

                    bankAccount.setBalance(userBalance + amount);
                    System.out.println("Успешное пополнение на " + amount + " Рублей");
                    break;
                case 2:
                    System.out.print("Введите сумму для снятия: ");
                    do {
                        String newLine = scanner.nextLine();
                        try {
                            amount = Double.parseDouble(newLine);
                        } catch (NumberFormatException e) {
                            amount = 0.0;
                        }
                        if (amount <= 0 || userBalance < amount) System.out.print("Некорректная сумма для снятия! Новый ввод: ");
                    } while (amount <= 0 || userBalance < amount);

                    bankAccount.setBalance(userBalance - amount);
                    System.out.println("Успешное cнятие " + amount + " Рублей");
                    break;
                case 3:
                    System.out.println("Ваш баланс: " + userBalance + " Рублей");

                    break;
                case 4:
                    flag = false;
                    break;
            }
        }
        System.out.println("\nРабота программы завершена!");
    }

    private static void printMenu() {
        System.out.print("\nМеню действий:" +
                "\n1 - Пополнить счёт" +
                "\n2 - Снять деньги" +
                "\n3 - Вывести баланс" +
                "\n4 - Завершение сессии" +
                "\nВыбор: ");
    }
}
