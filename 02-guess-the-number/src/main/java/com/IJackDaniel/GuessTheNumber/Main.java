package com.IJackDaniel.GuessTheNumber;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Выбор сложности, от которого зависит диапазон чисел

        Random random = new Random();
        int min = 0, max = 100;
        int target = random.nextInt(min, max + 1);

        int numberOfAttempts = 0;
        int userNumber;
        System.out.println("Угадайте число от " + min + " до " + max);
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Ввод: ");
            userNumber = scanner.nextInt();

            if (userNumber == target) continue;
            else if (userNumber > target) System.out.println("Загаданное число меньше");
            else System.out.println("Загаданное число больше");
        } while (userNumber != target);

        System.out.println("Поздравляю! Вы угадали число " + target + "!");
    }
}
