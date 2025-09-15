package com.IJackDaniel.GuessTheNumber;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите сложность: \n1 - лёгкая\n2 - средняя\n3 - сложная\n4 - экспертная");
        System.out.print("Ввод: ");
        int userDifficultyChioce = scanner.nextInt();
        int min, max;
        switch (userDifficultyChioce){
            case 1:
                min = 0;
                max = 10;
                break;
            case 2:
                min = 0;
                max = 100;
                break;
            case 3:
                min = 0;
                max = 1000;
                break;
            case 4:
                min = 0;
                max = 10000;
                break;
            default:
                min = 0;
                max = 10;
                System.out.println("Некорректный ввод! Выбрана средняя сложность");
                break;
        }

        Random random = new Random();
        int target = random.nextInt(min, max + 1);

        int numberOfAttempts = 0;
        int userNumber;
        System.out.println("\nУгадайте число от " + min + " до " + max);
        do {
            System.out.print("Ввод: ");
            userNumber = scanner.nextInt();
            numberOfAttempts++;

            if (userNumber == target) continue;
            else if (userNumber > target) System.out.println("Загаданное число меньше");
            else System.out.println("Загаданное число больше");
        } while (userNumber != target);

        System.out.println("\nПоздравляю! Вы угадали число " + target + "!");
        System.out.println("Вам понадобилось " + numberOfAttempts + " попыток! Неплохой результат)");
    }
}
