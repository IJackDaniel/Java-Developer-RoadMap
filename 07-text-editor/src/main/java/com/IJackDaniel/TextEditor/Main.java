package com.IJackDaniel.TextEditor;

import com.IJackDaniel.TextEditor.model.TextDocument;
import com.IJackDaniel.TextEditor.service.FileService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/IJackDaniel/TextEditor/view/TextEditorView.fxml"));
        Scene scene =new Scene(root);
        primaryStage.setTitle("Text Editor with IJackDaniel");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

//    public static void main(String[] args) throws IOException {
//        String pathToFile = "C:\\Programming\\TestFile.txt";
//
//        FileService fileService = new FileService();
//        TextDocument textDocument = fileService.readFile(pathToFile);
//        String s = textDocument.getText();
//        System.out.println(s);
//        s = s + "All work correctly!";
//        fileService.writeToFile(pathToFile, s);
//    }
}
