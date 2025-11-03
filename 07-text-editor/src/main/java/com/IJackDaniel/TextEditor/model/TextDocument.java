package com.IJackDaniel.TextEditor.model;

import com.IJackDaniel.TextEditor.service.FileService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextDocument {
    private String path;
    private String fileName;
    private String text;

    public TextDocument() {
        this.text = "";
        this.path = "";
        this.fileName = "";
    }

    public void setFile(File file) throws FileNotFoundException {
        this.text = FileService.readFile(file);
        this.path = file.getPath();
        this.fileName = file.getName();

    }

    public void updateText(String text) {
        this.text = text;
    }

    public void save() throws IOException {
        FileService.writeToFile(this.path, this.text);
    }

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
