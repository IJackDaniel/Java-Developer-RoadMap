package com.IJackDaniel.AdvancedGUICalculator.controller;

import com.IJackDaniel.AdvancedGUICalculator.model.CalculatorModel;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class CalculatorController {
    CalculatorModel model;

    @FXML
    TextField resultLabel;

    @FXML
    public void initialize() {
        model = new CalculatorModel();
        resultLabel.setText("0");
        System.out.println("Контроллер инициализирован!");
    }
}
