package com.IJackDaniel.AdvancedGUICalculator.controller;

import com.IJackDaniel.AdvancedGUICalculator.model.CalculatorModel;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class CalculatorController {
    CalculatorModel model;

    @FXML
    TextField resultLabel, historyLabel;

    @FXML
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    @FXML
    Button buttonPlus, buttonMinus, buttonMultiply, buttonDivide;
    @FXML
    Button buttonExponentiationOfN, buttonRootOfDegreeN, buttonInverse;
    @FXML
    Button buttonComma;
    @FXML
    Button buttonClear;
    @FXML
    Button buttonEquals;


    @FXML
    public void initialize() {
        model = new CalculatorModel();
        resultLabel.setText("0");
        System.out.println("Контроллер инициализирован!");
    }

    @FXML
    public void onDigitButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int inputDigit = Integer.parseInt(clickedButton.getText());
    }

    @FXML
    public void onOperationButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String operation = clickedButton.getText();
    }

    @FXML
    public void onCommaClick() {

    }

    @FXML
    public void onClearClick() {

    }

    @FXML
    public void onEqualsClick() {

    }
}
