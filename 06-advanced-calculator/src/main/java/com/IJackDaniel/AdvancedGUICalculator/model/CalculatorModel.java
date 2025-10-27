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
                exponentiationOfN();
                break;
            case "ⁿ√x":
                if (currentIsZero()) throw new ArithmeticException("Корень нулевой степени!");
                rootOfDegreeN();
                break;
        }
        shouldResetOnNextInput = true;
        addToHistory();
    }

    private double round(double value){
        double scale = Math.pow(10, getAccuracy());
        return Math.round(value * scale) / scale;
    }

    public void addition() {
        this.lastOperation = this.accumulator + " + " + this.current;

        double result = round(this.accumulator + this.current);
        resetOperation();
        this.current = result;

        this.lastOperation = this.lastOperation + " = " + this.current;
    }

    public void subtraction() {
        this.lastOperation = this.accumulator + " - " + this.current;

        double result = round(this.accumulator - this.current);
        resetOperation();
        this.current = result;

        this.lastOperation = this.lastOperation + " = " + this.current;
    }

    public void multiplication() {
        this.lastOperation = this.accumulator + " * " + this.current;

        double result = round(this.accumulator * this.current);
        resetOperation();
        this.current = result;

        this.lastOperation = this.lastOperation + " = " + this.current;
    }

    public void division() {
        this.lastOperation = this.accumulator + " / " + this.current;

        double result = round(this.accumulator / this.current);
        resetOperation();
        this.current = result;

        this.lastOperation = this.lastOperation + " = " + this.current;
    }

    public void exponentiationOfN() {
        this.lastOperation = this.accumulator + " ^ " + this.current;

        double result = round(Math.pow(this.accumulator, this.current));
        resetOperation();
        this.current = result;

        this.lastOperation = this.lastOperation + " = " + this.current;
    }

    public void rootOfDegreeN() {
        this.lastOperation = this.accumulator + " root of degree " + this.current;

        double result = round(Math.pow(this.accumulator, 1/this.current));
        resetOperation();
        this.current = result;

        this.lastOperation = this.lastOperation + " = " + this.current;
    }

    public void inverse() {
        double result = round(1/this.current);
        this.lastOperation = "reverse " + this.current + " = ";
        resetOperation();
        this.current = result;
        this.lastOperation = this.lastOperation + this.current;
        addToHistory();
    }

    private boolean currentIsZero(){
        return this.current == 0.0;
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
        if (!operation.isEmpty()) evaluate();
        if (shouldResetOnNextInput) shouldResetOnNextInput = false;
        this.operation = operation;
        shiftValue();
    }

    public void addToHistory() {
        this.historyOfOperations.add(this.lastOperation);
        this.lastOperation = "";
    }
}
