package com.IJackDaniel.GUICalculator.model;

public class CalculatorModel {
    private double accumulator;
    private double current;
    private String operation;
    private final int accuracy = 5;

    public CalculatorModel() {
        reset();
    }

    public void reset() {
        this.accumulator = 0.0;
        this.current = 0.0;
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
                if (divisionByZero()) throw new ArithmeticException("Деление на ноль!");
                division();
                break;
        }
    }

    private double round(double value){
        double scale = Math.pow(10, getAccuracy());
        return Math.round(value * scale) / scale;
    }

    public void addition() {
        double result = round(this.accumulator + this.current);
        reset();
        this.current = result;
    }

    public void subtraction() {
        double result = round(this.accumulator - this.current);
        reset();
        this.current = result;
    }

    public void multiplication() {
        double result = round(this.accumulator * this.current);
        reset();
        this.current = result;
    }

    public void division() {
        double result = round(this.accumulator / this.current);
        reset();
        this.current = result;
    }

    private boolean divisionByZero(){
        return this.current == 0.0;
    }

    // Getters
    public int getAccuracy() {
        return this.accuracy;
    }

    public double getCurrent() {
        return this.current;
    }

    public String getOperation() {
        return this.operation;
    }

    // Setters
    public void inputDigit(int digit) {
        this.current = this.current * 10 + digit;
    }

    public void inputOperation(String operation) {
        this.operation = operation;
        shiftValue();
    }
}
