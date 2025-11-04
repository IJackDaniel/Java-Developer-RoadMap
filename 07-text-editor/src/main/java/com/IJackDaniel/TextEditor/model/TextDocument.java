package com.IJackDaniel.TextEditor.model;

public class TextDocument {
    private String path;
    private String fileName;
    private String text;

    public TextDocument() {
        this.text = "";
        this.path = "";
        this.fileName = "";
    }

    public void setProperties(String text, String path, String fileName) {
        this.text = text;
        this.path = path;
        this.fileName = fileName;

    }

    public void updateText(String text) {
        this.text = text;
    }

    // Getters
    public String getText() {
        return this.text;
    }

    public String getPath() {
        return this.path;
    }

    public String getFileName() {
        return fileName;
    }
}
