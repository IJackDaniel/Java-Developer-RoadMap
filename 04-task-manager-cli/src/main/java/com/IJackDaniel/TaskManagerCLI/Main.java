package com.IJackDaniel.TaskManagerCLI;

import java.util.Scanner;

public class Main {
    private static final int ADD_NEW_TASK_CASE = 1;
    private static final int COMPLETE_TASK_CASE = 2;
    private static final int DELETE_TASK_CASE = 3;
    private static final int SHOW_ALL_TASKS_CASE = 4;
    private static final int EXIT_PROGRAM_CASE = 5;

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        // Main loop
        while (true) {
            printMenu();
            int userChoice = 0;
            do {
                System.out.print("Ввод: ");
                try {
                    userChoice = Integer.parseInt(scanner.nextLine());
                    if (userChoice < 1 || userChoice > 5) {
                        System.out.println("Некорректный выбор операции!");
                    }
                } catch (NumberFormatException exception) {
                    userChoice = 0;
                    System.out.println("Введите число!");
                }
            } while (userChoice < 1 || userChoice > 5);
        }
    }

    public static void printMenu() {
        System.out.println("\n===========Меню===========" +
                "\n|1-Добавить новую задачу |" +
                "\n|2-Завершить задачу      |" +
                "\n|3-Удалить задачу        |" +
                "\n|4-Показать все задачи   |" +
                "\n|5-Выход из программы    |" +
                "\n==========================");
    }
}
