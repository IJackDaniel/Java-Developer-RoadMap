package com.IJackDaniel.GuessTheNumber;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int DIFFICULTY_EASY = 1;
    static final int DIFFICULTY_NORMAL = 2;
    static final int DIFFICULTY_HARD = 3;
    static final int DIFFICULTY_EXPERT = 4;

    static final int UPPER_BOUND_EASY = 10;
    static final int UPPER_BOUND_NORMAL = 100;
    static final int UPPER_BOUND_HARD = 1000;
    static final int UPPER_BOUND_EXPERT = 10000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите сложность: \n1 - лёгкая\n2 - средняя\n3 - сложная\n4 - экспертная");
        int userDifficultyChioce = 0;
        do {
            System.out.print("Ввод: ");
            if (scanner.hasNextInt()) {
                userDifficultyChioce = Integer.parseInt(scanner.nextLine());
            } else {
                scanner.next();
            }
        } while (userDifficultyChioce < 1 || userDifficultyChioce > 4);

        int min = 1, max = 0;
        switch (userDifficultyChioce){
            case DIFFICULTY_EASY:
                System.out.print("\nВыбранная сложность: Лёгкая");
                max = UPPER_BOUND_EASY;
                break;
            case DIFFICULTY_NORMAL:
                System.out.print("\nВыбранная сложность: Средняя");
                max = UPPER_BOUND_NORMAL;
                break;
            case DIFFICULTY_HARD:
                System.out.print("\nВыбранная сложность: Сложная");
                max = UPPER_BOUND_HARD;
                break;
            case DIFFICULTY_EXPERT:
                System.out.print("\nВыбранная сложность: Экспертная");
                max = UPPER_BOUND_EXPERT;
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
                userNumber = Integer.parseInt(scanner.nextLine());
            } else {
                System.out.println("Это не число!");
                scanner.next();
                continue;
            }
            numberOfAttempts++;

            if (userNumber == target) break;
            else if (userNumber > target) System.out.println("Загаданное число меньше");
            else System.out.println("Загаданное число больше");
        } while (userNumber != target);

        System.out.println("\nПоздравляю! Вы угадали число " + target + "!");
        System.out.println("Вы угадали на " + numberOfAttempts + "-й попытке! Неплохой результат)");
    }
}
