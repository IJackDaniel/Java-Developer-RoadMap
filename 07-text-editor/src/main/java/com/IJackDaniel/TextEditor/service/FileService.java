package com.IJackDaniel.TextEditor.service;

import com.IJackDaniel.TextEditor.Exceptions.EmptyFilePath;
import com.IJackDaniel.TextEditor.Exceptions.FileReadException;
import com.IJackDaniel.TextEditor.Exceptions.FileWriteException;
import com.IJackDaniel.TextEditor.model.TextDocument;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileService {
    public TextDocument readFile(String filePath) throws FileNotFoundException {
        if (filePath.isEmpty()) {
            throw new EmptyFilePath("Не указан путь к файлу");
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String string;
            while ((string=reader.readLine()) != null) {
                stringBuilder.append(string).append("\n");
            }
        } catch (IOException exception) {
            throw new FileReadException("Ошибка при чтении файла: " + exception.getMessage());
        }
        return new TextDocument(stringBuilder.toString());
    }

    public void writeToFile(String filePath, String text) throws IOException {
        if (filePath.isEmpty()) {
            throw new EmptyFilePath("Не указан путь к файлу");
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(text.replace("\n", System.lineSeparator()));
        } catch (IOException exception) {
            throw new FileWriteException("Ошибка при записи в файл: " + exception.getMessage());
        }
    }
}
