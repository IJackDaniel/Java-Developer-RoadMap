package com.IJackDaniel.TaskManagerCLI;

public class Task {
    private int id;
    private String description;
    private boolean status;

    public Task(int id, String description, boolean status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    // Getters
    public int getId() {
        return id;
    }

    // Setters
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toWriteInFile() {
        return this.id + "|" + this.description + "|" + this.status;
    }

    @Override
    public String toString() {
        String statusString = this.status? "[X] " : "[ ] ";
        return this.id + ". " + statusString + this.description;
    }
}
