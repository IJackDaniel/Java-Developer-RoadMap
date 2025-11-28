package com.IJackDaniel.TextEditor.model;

public class TextDocument {
    private String path;
    private String text;
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderline;

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

    public void setPath(String path) {
        this.path = path;
    }

    public void clear() {
        this.text = "";
        this.path = "";
        this.isBold = false;
        this.isItalic = false;
        this.isUnderline = false;
    }

    public void changeBold() {
        this.isBold = !this.isBold;
    }

    public void changeItalic() {
        this.isItalic = !this.isItalic;
    }

    // Getters
    public String getText() {
        return this.text;
    }

    public String getPath() {
        return this.path;
    }

    public boolean isBold() {
        return isBold;
    }

    public boolean isItalic() {
        return isItalic;
    }
}
