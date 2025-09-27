package com.IJackDaniel.TaskManagerCLI;

import java.util.ArrayList;
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

        System.out.println(taskManager.readFromFile());

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

            int id = 0;
            switch (userChoice) {
                case ADD_NEW_TASK_CASE:
                    System.out.print("Название задачи: ");
                    String description = scanner.nextLine();
                    System.out.println(taskManager.addTask(description));
                    break;
                case COMPLETE_TASK_CASE:
                    id = readTaskId(scanner);
                    System.out.println(taskManager.completeTask(id));
                    break;
                case DELETE_TASK_CASE:
                    id = readTaskId(scanner);
                    System.out.println(taskManager.deleteTask(id));
                    break;
                case SHOW_ALL_TASKS_CASE:
                    ArrayList<Task> tasks = taskManager.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("Список задач пуст!");
                    }
                    else {
                        for (Task task : tasks) {
                            System.out.println(task.toString());
                        }
                    }
                    break;
                case EXIT_PROGRAM_CASE:
                    System.out.println(taskManager.writeToFile());
                    System.out.print("Выход из программы");
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException exception) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.print(".");
                    }
                    System.out.println("Программа заверена!");
                    return;

            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===========Меню===========" +
                "\n|1-Добавить новую задачу |" +
                "\n|2-Завершить задачу      |" +
                "\n|3-Удалить задачу        |" +
                "\n|4-Показать все задачи   |" +
                "\n|5-Выход из программы    |" +
                "\n==========================");
    }

    private static int readTaskId(Scanner scanner) {
        System.out.print("ID задачи: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            return -1;
        }
    }
}
