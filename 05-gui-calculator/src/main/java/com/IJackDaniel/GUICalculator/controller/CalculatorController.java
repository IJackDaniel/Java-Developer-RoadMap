package com.IJackDaniel.GUICalculator.controller;

import com.IJackDaniel.GUICalculator.model.CalculatorModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
    CalculatorModel model;

    @FXML
    private TextField displayField;

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;

    @FXML
    private Button buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonClear, buttonEquals;

    @FXML
    public void initialize() {
        model = new CalculatorModel();
        displayField.setText("0");
        System.out.println("CalculatorController инициализирован!");
    }

    @FXML
    private void onDigitButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int input = Integer.parseInt(clickedButton.getText());
        model.inputDigit(input);
        updateDisplay();
    }

    @FXML
    private void onOperatorButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        model.inputOperation(clickedButton.getText());
        updateDisplay();
    }

    @FXML
    private void onEqualsClick() {
        try {
            model.evaluate();
        } catch (ArithmeticException exception) {
            displayField.setText(exception.getMessage());
        }
        updateDisplay();
    }

    @FXML
    private void onClearClick() {
        model.reset();
        updateDisplay();
    }

    private void updateDisplay() {
        double digit = model.getCurrent();
        if (digit == (int) digit) {
            displayField.setText(String.valueOf((int)digit));
        } else {
            displayField.setText(String.valueOf(digit));
        }
    }
}
