package com.IJackDaniel.TextEditor.model;

public class TextDocument {
    private String text;

    public TextDocument(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
