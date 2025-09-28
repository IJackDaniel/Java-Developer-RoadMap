package com.IJackDaniel.GUICalculator.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField display;

    @FXML
    public void initialize() {
        display.setText("0");
        System.out.println("CalculatorController инициализирован!");
    }
}
