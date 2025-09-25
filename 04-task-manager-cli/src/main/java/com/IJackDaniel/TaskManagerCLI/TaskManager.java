package com.IJackDaniel.TaskManagerCLI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskArrayList ;
    private int maxId;

    public TaskManager() {
        taskArrayList = new ArrayList<>();
        maxId = 0;
    }

    // Function for read data from data.txt
    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data\\data.txt"))) {
            String string;
            while ((string=reader.readLine()) != null) {
                String[] parseString = string.split("\\|");

                int id = Integer.parseInt(parseString[0]);
                String description = parseString[1];
                boolean status = Boolean.getBoolean(parseString[2]);

                Task readedTask = new Task(id, description, status);
                taskArrayList.add(readedTask);
                maxId = Math.max(maxId, id);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    // Function for write data from file.txt
    public void writeToFile(){

    }

    // Add task to array list
    public void addTask(String description) {
        if (!description.isEmpty()) {
            Task newTask = new Task(maxId + 1, description, false);
            maxId++;
            taskArrayList.add(newTask);
        }
    }

    // Delete task from array list by ID
    public void deleteTask(int id) {
        for (Task task : taskArrayList) {
            if (task.getId() == id) {
                taskArrayList.remove(task);
                break;
            }
        }
    }

    // Complete task
    public void completeTask(int id) {
        for (Task task : taskArrayList) {
            if (task.getId() == id) {
                task.setStatus(true);
                break;
            }
        }
    }

    // Show all of tasks
    public void showAllTasks() {
        for (Task task : taskArrayList) {
            System.out.println(task.toString());
        }
    }
}
