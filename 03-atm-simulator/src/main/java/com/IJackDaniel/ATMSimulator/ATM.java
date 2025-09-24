package com.IJackDaniel.ATMSimulator;

import java.util.Scanner;

public class ATM {
    final static int DEPOSIT_MONEY_CASE = 1;
    final static int WITHDRAW_MONEY_CASE = 2;
    final static int CHECK_MONEY_CASE = 3;
    final static int EXIT_PROGRAM_CASE = 4;

    public static void main(String[] args) {
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);
        BankAccount bankAccount = new BankAccount();

        int tryLogIn = 1;
        while (tryLogIn <= 3 && !flag) {
            System.out.print("Введите логин: ");
            String login = scanner.nextLine();
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();
            if (bankAccount.checkLoginAndPassword(login, password)) {
                flag = true;
            } else {
                System.out.println("Неправильный логин или пароль!");
                tryLogIn++;
                System.out.println("Осталось попыток входа: " + (4 - tryLogIn));
            }
        }
        if (tryLogIn == 4) System.out.println("\nВаша карта заблокирована!");
        else System.out.println("\nУспешный вход!");

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
                case DEPOSIT_MONEY_CASE:
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
                case WITHDRAW_MONEY_CASE:
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
                case CHECK_MONEY_CASE:
                    System.out.println("Ваш баланс: " + userBalance + " Рублей");

                    break;
                case EXIT_PROGRAM_CASE:
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
