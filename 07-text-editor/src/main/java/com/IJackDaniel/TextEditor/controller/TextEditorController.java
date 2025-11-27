package com.IJackDaniel.TextEditor.controller;

import com.IJackDaniel.TextEditor.model.TextDocument;
import com.IJackDaniel.TextEditor.service.FileService;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
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
    Button buttonBold, buttonItalic, buttonUnderline;
    @FXML
    Label labelCountOfCharacters;

    @FXML
    public void initialize() {
        this.model = new TextDocument();
        this.fileService = new FileService();
        updateAvailability();
        setupStageListener();
    }

    @FXML
    public void onCreateClick(ActionEvent event) {
        String filePath = createNewFilePath(event);
        if (!filePath.isEmpty()) {
            onCloseClick(event);
            model.setProperties("", filePath);
        }
        updateAvailability();
    }

    @FXML
    public void onOpenClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбор файла");
        //fileChooser.setInitialDirectory(new File("C:/"));
        // Для удобства тестирования:
        fileChooser.setInitialDirectory(new File("C:/Programming"));
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);

        Window ownerWindow = textField.getScene().getWindow();
        File file = fileChooser.showOpenDialog(ownerWindow);
        if (file != null) {
            try {
                this.model.setProperties(this.fileService.readFile(file), file.getPath());
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
    public void onSaveAsClick(ActionEvent event) {
        // Выбираем место и название файла (Будет схоже с функцией onCreateClick)

        // Обновляем путь и название в модели

        // Сохраняем (Думаю нужно создать отдельную функцию для сохранения, чтобы вызывать её тут и в onSaveClick)
    }

    @FXML
    public void onCloseClick(ActionEvent event) {
        if (doesFileOpen()) {
            if (hasUnsavedChanges()) {
                Alert alert = createConfirmationAlert();
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
                    if (buttonType != ButtonType.CANCEL) {
                        model.clear();
                        textField.setText(model.getText());
                        updateAvailability();
                    }
                }
            }
        }
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
                Alert alert = createConfirmationAlert();
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

    @FXML
    public void makeBold(ActionEvent event) {
        System.out.println("Bold");
    }

    @FXML
    public void makeItalic(ActionEvent event) {
        System.out.println("Italic");
    }

    @FXML
    public void makeUnderline(ActionEvent event) {
        System.out.println("Underline");
    }

    private boolean hasUnsavedChanges() {
        return !(model.getText()).equals(textField.getText());
    }

    private boolean doesFileOpen() {
        return !model.getPath().isEmpty();
    }

    private void updateAvailability() {
        boolean isAvailable = !doesFileOpen();
        textField.setDisable(isAvailable);
        buttonBold.setDisable(isAvailable);
        buttonItalic.setDisable(isAvailable);
        buttonUnderline.setDisable(isAvailable);
    }

    private Alert createConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Сохранение изменений");
        alert.setHeaderText("У вас есть несохраненные изменения");
        alert.setContentText("Вы хотите сохранить изменения закрытием файла?");

        alert.getButtonTypes().setAll(
                ButtonType.YES,
                ButtonType.NO,
                ButtonType.CANCEL
        );

        return alert;
    }

    private Alert createErrorAlert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка расположения файла");
        alert.setContentText(error);

        return alert;
    }

    private String createNewFilePath(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выбор местоположения");
        //directoryChooser.setInitialDirectory(new File("C:/"));
        // Для удобства тестирования
        directoryChooser.setInitialDirectory(new File("C:/Programming"));

        Window ownerWindow = textField.getScene().getWindow();
        File directory = directoryChooser.showDialog(ownerWindow);

        TextInputDialog inputNameDialog = new TextInputDialog("Название файла");
        inputNameDialog.setHeaderText("Введите название файла");
        inputNameDialog.showAndWait();

        String fileName = inputNameDialog.getEditor().getText();
        if (directory != null) {
            if (!fileName.isEmpty()) {
                fileName = fileName + ".txt";
                return directory.getPath() + "\\" + fileName;
            } else {
                Alert alert = createErrorAlert("Не указано имя файла");
                alert.show();
            }
        } else {
            Alert alert = createErrorAlert("Не указана директория");
            alert.show();
        }
        return "";
    }
}
