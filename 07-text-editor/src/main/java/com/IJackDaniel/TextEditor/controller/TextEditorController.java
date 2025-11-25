package com.IJackDaniel.TextEditor.controller;

import com.IJackDaniel.TextEditor.model.TextDocument;
import com.IJackDaniel.TextEditor.service.FileService;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TextEditorController {
    TextDocument model;
    FileService fileService;

    @FXML
    TextArea textField;
    @FXML
    MenuItem openFile, saveFile;

    @FXML
    public void initialize() {
        this.model = new TextDocument();
        this.fileService = new FileService();
        updateAvailability();
        setupStageListener();
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
        if (file != null) {
            try {
                this.model.setProperties(this.fileService.readFile(file), file.getPath(), file.getName());
                textField.setText(model.getText());
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        updateAvailability();
    }

    @FXML
    public void onSaveClick(ActionEvent event) throws IOException {
        if (doesFileOpen()) {
            model.updateText(textField.getText());
            this.fileService.writeToFile(model.getPath(), model.getText());
        }
    }

    @FXML
    public void onCloseClick(ActionEvent event) {
        if (doesFileOpen()) {
            if (hasUnsavedChanges()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Сохранение изменений");
                alert.setHeaderText("У вас есть несохраненные изменения");
                alert.setContentText("Вы хотите сохранить изменения перед закрытием файла?");

                alert.getButtonTypes().setAll(
                        ButtonType.YES,
                        ButtonType.NO,
                        ButtonType.CANCEL
                );

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent()) {
                    ButtonType buttonType = result.get();

                    if (buttonType == ButtonType.YES) {
                        try {
                            model.updateText(textField.getText());
                            this.fileService.writeToFile(model.getPath(), model.getText());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (buttonType != ButtonType.CANCEL) model.clear();
                }
            }
        }
        textField.setText(model.getText());
        updateAvailability();
    }

    public void setupStageListener() {
        textField.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                Window window = newScene.getWindow();

                if (window != null) {
                    setupCloseEventHandler((Stage) window);
                } else {
                    setupWindowListener(newScene);
                }
            }
        });
    }

    private void setupWindowListener(Scene scene) {
        scene.windowProperty().addListener((windowObs, oldWindow, newWindow) -> {
            if (newWindow != null) {
                setupCloseEventHandler((Stage) newWindow);
            }
        });
    }

    public void setupCloseEventHandler(Stage stage) {
        stage.setOnCloseRequest(windowEvent -> {
            if (hasUnsavedChanges()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Сохранение изменений");
                alert.setHeaderText("У вас есть несохраненные изменения");
                alert.setContentText("Вы хотите сохранить изменения перед выходом?");

                alert.getButtonTypes().setAll(
                        ButtonType.YES,
                        ButtonType.NO,
                        ButtonType.CANCEL
                );

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent()) {
                    ButtonType buttonType = result.get();

                    if (buttonType == ButtonType.YES) {
                        try {
                            model.updateText(textField.getText());
                            this.fileService.writeToFile(model.getPath(), model.getText());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (buttonType == ButtonType.CANCEL) {
                        windowEvent.consume();
                    }
                }
            }
        });
    }

    private boolean hasUnsavedChanges() {
        return !(model.getText()).equals(textField.getText());
    }

    private boolean doesFileOpen() {
        return !model.getPath().isEmpty();
    }

    private void updateAvailability() {
        textField.setDisable(!doesFileOpen());
    }
}
