package com.IJackDaniel.ATMSimulator;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        Scanner scanner = new Scanner(System.in);

        while (true) {
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
            } while (userChoice > 4 || userChoice < 1);

            switch (userChoice) {
                case 1:
                    System.out.println("1");
                    // Ввод суммы для пополнения

                    // Проверка суммы

                    // Пополнение баланса

                    // Вывод об успешной операции

                    break;
                case 2:
                    System.out.println("2");
                    // Ввод суммы для вывода

                    // Проверка суммы

                    // Снятие суммы

                    // Вывод об успешной операции

                    break;
                case 3:
                    System.out.println("3");
                    // Вывод баланса

                    break;
                case 4:
                    System.out.println("4");
                    // Выход из программы
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("LoL");
    }
}
