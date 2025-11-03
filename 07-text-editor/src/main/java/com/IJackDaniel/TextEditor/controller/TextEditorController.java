package com.IJackDaniel.TextEditor.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class TextEditorController {
    @FXML
    TextArea textField;
    @FXML
    MenuItem openFile;

    @FXML
    public void initialize() {
        System.out.println("Контроллер инициализирован!");
    }

    @FXML
    public void onOpenClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбор файла");
        fileChooser.setInitialDirectory(new File("C:/"));
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);

        Window ownerWindow = textField.getScene().getWindow();
        File file = fileChooser.showOpenDialog(ownerWindow);
    }
}
