package com.IJackDaniel.TextEditor.model;

public class TextDocument {
    private String path;
    private String text;

    public TextDocument() {
        clear();
    }

    public void setProperties(String text, String path) {
        this.text = text;
        this.path = path;
    }

    public void updateText(String text) {
        this.text = text;
    }

    public void clear() {
        this.text = "";
        this.path = "";
    }

    // Getters
    public String getText() {
        return this.text;
    }

    public String getPath() {
        return this.path;
    }
}
