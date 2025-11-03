package com.IJackDaniel.TextEditor.controller;

import javafx.fxml.FXML;

import java.awt.*;

public class TextEditorController {
    @FXML
    TextArea textField;

    @FXML
    public void initialize() {
        System.out.println("Контроллер инициализирован!");
    }
}
