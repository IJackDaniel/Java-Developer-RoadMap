package com.IJackDaniel.TextEditor.service;

public class TextStatisticService {
    public static int countOfSymbolsWithSpaces(String text) {
        return text.length();
    }

    public static int countOfSymbolsWithoutSpaces(String text) {
        return (text.replace(" ","")).length();
    }

    public static int countOfWords(String text) {
        text = text.trim();
        while (text.contains("  ")) {
            text = text.replace("  ", " ");
        }
        return (text.split(" ")).length;
    }
}
