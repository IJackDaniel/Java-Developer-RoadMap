package com.IJackDaniel.TextEditor.controller;

import com.IJackDaniel.TextEditor.model.TextDocument;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextEditorController {
    TextDocument model;

    @FXML
    TextArea textField;
    @FXML
    MenuItem openFile, saveFile;

    @FXML
    public void initialize() {
        this.model = new TextDocument();
        System.out.println("Контроллер инициализирован!");
    }

    @FXML
    public void onOpenClick(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбор файла");
        fileChooser.setInitialDirectory(new File("C:/"));
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);

        Window ownerWindow = textField.getScene().getWindow();
        File file = fileChooser.showOpenDialog(ownerWindow);
        if (file != null) {
            try {
                model.setFile(file);
                textField.setText(model.getText());
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    @FXML
    public void onSaveClick(ActionEvent event) throws IOException {
        model.updateText(textField.getText());
        model.save();
    }
}
