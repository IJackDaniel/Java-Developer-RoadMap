package com.IJackDaniel.GUICalculator.controller;

import com.IJackDaniel.GUICalculator.model.CalculatorModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
    CalculatorModel model = new CalculatorModel();

    @FXML
    private TextField displayField;

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    private Button buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonClear, buttonEquals;

    @FXML
    public void initialize() {
        displayField.setText("Введите число");
        System.out.println("CalculatorController инициализирован!");
    }

    @FXML
    private void onDigitButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        displayField.setText("Это цифорка " + clickedButton.getText());
        model.reset();
    }

    @FXML
    private void onOperatorButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        displayField.setText("Это функция " + clickedButton.getText());
    }

    @FXML
    private void onEqualsClick() {
        System.out.println("Нажал на =");
    }

    @FXML
    private void onClearClick() {
        model.reset();
        updateDisplay();
    }

    private void updateDisplay() {
        displayField.setText(String.valueOf(model.getCurrent()));
    }
}
