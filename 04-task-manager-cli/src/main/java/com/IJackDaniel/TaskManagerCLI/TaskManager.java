package com.IJackDaniel.TaskManagerCLI;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskManager {
    private ArrayList<Task> tasks;
    private int maxId;

    public TaskManager() {
        tasks = new ArrayList<>();
        maxId = 0;
    }

    // Function for read data from data.txt
    public String readFromFile() {
        File file = new File("data\\data.txt");
        File dataDir = new File("data");

        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                return "Файл данных создан!";
            } catch (IOException exception) {
                return "Ошибка при создании файла: " + exception.getMessage();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data\\data.txt"))) {
            String string;
            while ((string=reader.readLine()) != null) {
                if (string.trim().isEmpty()) continue;

                String[] parseString = string.split("\\|");

                int id = Integer.parseInt(parseString[0]);
                String description = parseString[1];
                boolean status = Boolean.parseBoolean(parseString[2]);

                Task readTask = new Task(id, description, status);
                tasks.add(readTask);
                maxId = Math.max(maxId, id);
            }
            return "Успешное чтение файла! Загружено задач: " + tasks.size();
        } catch (IOException exception) {
            return "Ошибка при чтении файла: " + exception.getMessage();
        }
    }

    // Function for write data from file.txt
    public String writeToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data\\data.txt"))){
            for (Task task : tasks) {
                writer.write(task.toWriteInFile());
                writer.newLine();
            }
            return "Успешная запись в файл!";
        } catch (IOException exception) {
            return exception.getMessage();
        }
    }

    // Add task to array list
    public String addTask(String description) {
        if (!description.isEmpty()) {
            Task newTask = new Task(maxId + 1, description, false);
            maxId++;
            tasks.add(newTask);
            return "Задача добавлена в список!";
        }
        return "Ошибка! Отсутствует название!";
    }

    // Delete task from array list by ID
    public String deleteTask(int id) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                return "Задача удалена!";
            }
        }
        if (maxId == id) {
            maxId = getMaxId();
        }
        return "Ошибка! Задачи с данным ID не существует!";
    }

    // Complete task
    public String completeTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(true);
                return "Задача выполнена!";
            }
        }
        return "Ошибка! Задачи с данным ID не существует!";
    }

    // Get max id
    public int getMaxId() {
        int max = -1;
        for (Task task : tasks) {
            max = Math.max(max, task.getId());
        }
        return max;
    }

    // Get all of tasks
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
