package com.IJackDaniel.GUICalculator.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField displayField;

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    private Button buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonClear, buttonEquals;

    @FXML
    public void initialize() {
        displayField.setText("0");
        System.out.println("CalculatorController инициализирован!");
    }

    @FXML
    private void onDigitButtonClick(javafx.event.ActionEvent event) {
        System.out.println("Нажата кнопочка с цифоркой");
    }

    @FXML
    private void onOperatorButtonClick(javafx.event.ActionEvent event) {
        System.out.println("Нажата кнопочка с опирацией");
    }

    @FXML
    private void onEqualsClick() {
        System.out.println("Нажал на =");
    }

    @FXML
    private void onClearClick() {
        System.out.println("Решил что в два раза мощнее таноса и очистил ВСЮ вселенную");
    }
}
