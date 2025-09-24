package com.IJackDaniel.ATMSimulator;

import java.util.Scanner;

public class ATM {
    final static int DEPOSIT_MONEY_CASE = 1;
    final static int WITHDRAW_MONEY_CASE = 2;
    final static int CHECK_MONEY_CASE = 3;
    final static int HISTORY_OF_OPERATIONS_CASE = 4;
    final static int EXIT_PROGRAM_CASE = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount bankAccount = new BankAccount();

        int attemptsCount = 0;
        while (attemptsCount < 3) {
            System.out.print("Введите логин: ");
            String login = scanner.nextLine();
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();
            if (bankAccount.checkLoginAndPassword(login, password)) {
                break;
            } else {
                System.out.println("Неправильный логин или пароль!");
                attemptsCount++;
                System.out.println("Осталось попыток входа: " + (3 - attemptsCount));
            }
        }
        if (attemptsCount >= 3) {
            System.out.println("\nВаша карта заблокирована!");
            return;
        }
        else System.out.println("\nУспешный вход!");

        while (true) {
            printMenu();
            int userChoice = 0;
            do {
                String newLine = scanner.nextLine();
                try {
                    userChoice = Integer.parseInt(newLine);
                    if (userChoice > 5 || userChoice < 1) System.out.print("Некорректный выбор!\nНовый выбор: ");
                } catch (NumberFormatException e) {
                    System.out.println("Введено не число!");
                    userChoice = 0;
                }
            } while (userChoice > 5 || userChoice < 1);

            double amount = 0.0;
            switch (userChoice) {
                case DEPOSIT_MONEY_CASE:
                    do {
                        System.out.print("Введите сумму для пополнения: ");
                        String newLine = scanner.nextLine();
                        try {
                            amount = Double.parseDouble(newLine);
                            if (amount <= 0.0) System.out.println("Введена некорректная сумма!");
                        } catch (NumberFormatException e) {
                            amount = 0.0;
                            System.out.println("Введено не число!");
                        }
                    } while (amount <= 0.0);

                    bankAccount.deposit(amount);
                    System.out.println("Успешное пополнение на " + amount + " Рублей");
                    break;
                case WITHDRAW_MONEY_CASE:
                    do {
                        System.out.print("Введите сумму для снятия: ");
                        String newLine = scanner.nextLine();
                        try {
                            amount = Double.parseDouble(newLine);
                            if (amount <= 0.0) System.out.println("Введена некорректная сумма!");
                            else if (amount > bankAccount.getBalance()) {
                                System.out.println("На балансе недостаточно средств");
                                amount = 0.0;
                            }
                        } catch (NumberFormatException e) {
                            amount = 0.0;
                            System.out.println("Введено не число!");
                        }
                    } while (amount <= 0.0);

                    bankAccount.withdraw(amount);
                    System.out.println("Успешное cнятие " + amount + " Рублей");
                    break;
                case HISTORY_OF_OPERATIONS_CASE:
                    if (bankAccount.isHistoryEmpty()) System.out.println("История операций пуста");
                    else {
                        for (String operation : bankAccount.getHistoryOfOperations()) {
                            System.out.println(operation);
                        }
                    }
                    break;
                case CHECK_MONEY_CASE:
                    System.out.println("Ваш баланс: " + bankAccount.getBalance() + " Рублей");

                    break;
                case EXIT_PROGRAM_CASE:
                    System.out.println("\nРабота программы завершена!");
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.print("\nМеню действий:" +
                "\n1 - Пополнить счёт" +
                "\n2 - Снять деньги" +
                "\n3 - Вывести баланс" +
                "\n4 - История операций" +
                "\n5 - Завершение сессии" +
                "\nВыбор: ");
    }
}
