package com.IJackDaniel.AdvancedGUICalculator.model;

import java.util.ArrayList;
import java.util.List;

public class CalculatorModel {
    private double accumulator;
    private double current;
    private String operation;
    private String lastOperation;
    private final int accuracy = 5;
    private boolean shouldResetOnNextInput;
    private List<String> historyOfOperations;

    public CalculatorModel() {
        this.historyOfOperations = new ArrayList<>();
        reset();
    }

    public void reset() {
        this.accumulator = 0.0;
        this.current = 0.0;
        this.operation = "";
        this.lastOperation = "";
        this.shouldResetOnNextInput = false;
    }

    private void resetOperation() {
        this.operation = "";
    }

    private void shiftValue() {
        this.accumulator = this.current;
        this.current = 0;
    }

    public void evaluate() {
        double saveAccumulator = this.accumulator;
        double saveCurrent = this.current;
        String currentOperation = this.operation;

        if (this.operation.isEmpty()) return;
        switch (operation) {
            case "+":
                addition();
                break;
            case "-":
                subtraction();
                break;
            case "*":
                multiplication();
                break;
            case "/":
                if (currentIsZero()) throw new ArithmeticException("Деление на ноль!");
                division();
                break;
            case "xⁿ":
                currentOperation = "^";
                exponentiationOfN();
                break;
            case "ⁿ√x":
                if (currentIsZero()) throw new ArithmeticException("Корень нулевой степени!");
                currentOperation = "root of degree";
                rootOfDegreeN();
                break;
        }
        shouldResetOnNextInput = true;
        formOperationString(saveAccumulator, saveCurrent, currentOperation, this.current);
        addToHistory();
    }

    private double round(double value){
        double scale = Math.pow(10, getAccuracy());
        return Math.round(value * scale) / scale;
    }

    public void addition() {
        double result = round(this.accumulator + this.current);
        resetOperation();
        this.current = result;
    }

    public void subtraction() {
        double result = round(this.accumulator - this.current);
        resetOperation();
        this.current = result;
    }

    public void multiplication() {
        double result = round(this.accumulator * this.current);
        resetOperation();
        this.current = result;
    }

    public void division() {
        double result = round(this.accumulator / this.current);
        resetOperation();
        this.current = result;
    }

    public void exponentiationOfN() {
        double result = round(Math.pow(this.accumulator, this.current));
        resetOperation();
        this.current = result;
    }

    public void rootOfDegreeN() {
        if (currentIsZero()) throw new ArithmeticException("Корень нулевой степени!");
        if (this.accumulator < 0 && this.current % 2 == 0)
            throw new ArithmeticException("Корень четной степени из отрицательного числа!");

        double result = round(Math.pow(this.accumulator, 1.0/this.current));
        resetOperation();
        this.current = result;
    }

    public void inverse() {
        double result = round(1/this.current);
        resetOperation();
        formOperationString(this.current, "reverse", result);
        this.current = result;
        addToHistory();
    }

    private boolean currentIsZero(){
        return Math.abs(this.current) < 1e-10;
    }

    private void formOperationString(double num1, double num2, String currentOperation, double result) {
        this.lastOperation = num1 + " " + currentOperation + " " + num2 + " = " + result;
    }

    private void formOperationString(double num1, String currentOperation, double result) {
        this.lastOperation = num1 + " " + currentOperation + " = " + result;
    }

    // Getters
    public int getAccuracy() {
        return this.accuracy;
    }

    public double getCurrent() {
        return this.current;
    }

    public List<String> getHistoryOfOperations() {
        return this.historyOfOperations;
    }

    // Inputs
    public void inputDigit(int digit) {
        if (shouldResetOnNextInput) {
            shouldResetOnNextInput = false;
            reset();
            this.current = digit;
        } else {
            this.current = this.current * 10 + digit;
        }

    }

    public void inputOperation(String operation) {
        if (!operation.isEmpty() && !shouldResetOnNextInput) evaluate();
        if (shouldResetOnNextInput) shouldResetOnNextInput = false;
        this.operation = operation;
        shiftValue();
    }

    public void addToHistory() {
        this.historyOfOperations.add(this.lastOperation);
        this.lastOperation = "";
    }
}
