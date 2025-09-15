package com.IJackDaniel.GuessTheNumber;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите сложность: \n1 - лёгкая\n2 - средняя\n3 - сложная\n4 - экспертная");
        String input;
        do {
            System.out.print("Ввод: ");
            input = scanner.next();
        } while (!input.matches("[1-4]"));
        int userDifficultyChioce = Integer.parseInt(input);

        int min = 0, max;
        switch (userDifficultyChioce){
            case 1:
                System.out.print("\nВыбранная сложность: Лёгкая");
                max = 10;
                break;
            case 2:
                System.out.print("\nВыбранная сложность: Средняя");
                max = 100;
                break;
            case 3:
                System.out.print("\nВыбранная сложность: Сложная");
                max = 1000;
                break;
            case 4:
                System.out.print("\nВыбранная сложность: Экспертная");
                max = 10000;
                break;
            default:
                max = 100;
                System.out.println("\nНекорректный ввод! Выбрана средняя сложность");
                break;
        }

        Random random = new Random();
        int target = random.nextInt(min, max + 1);

        int numberOfAttempts = 0;
        int userNumber = -1;
        System.out.println("\nУгадайте число от " + min + " до " + max);
        do {
            System.out.print("Ввод: ");
            if (scanner.hasNextInt()) {
                userNumber = scanner.nextInt();
            } else {
                System.out.println("Это не число!");
                scanner.next();
                continue;
            }
            numberOfAttempts++;

            if (userNumber == target) continue;
            else if (userNumber > target) System.out.println("Загаданное число меньше");
            else System.out.println("Загаданное число больше");
        } while (userNumber != target);

        System.out.println("\nПоздравляю! Вы угадали число " + target + "!");
        System.out.println("Вы угадали на " + numberOfAttempts + "-й попытке! Неплохой результат)");
    }
}
