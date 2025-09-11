public class Calculator {
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public Calculator() {
        firstNumber = 0;
        secondNumber = 0;
        operator = "";
    }

    private void clear() {
        firstNumber = 0;
        secondNumber = 0;
        operator = "";
    }

    public void solve() {
        switch (operator) {
            case "+":
                System.out.println(firstNumber + secondNumber);
                break;
            case "-":
                System.out.println(firstNumber - secondNumber);
                break;
            case "*":
                System.out.println(firstNumber * secondNumber);
                break;
            case "/":
                if (secondNumber != 0) System.out.println(firstNumber / secondNumber);
                else System.out.println("Деление на 0!");
                break;
            default:
                System.out.println("Неверная операция!");
                break;
        };
        clear();
    }

    // Setters
    public void setFirstNumber(double newValue) {
        firstNumber = newValue;
    }

    public void setSecondNumber(double newValue) {
        secondNumber = newValue;
    }

    public void setOperator(String newOperator) {
        operator = newOperator;
    }

    // Getters
    public double getFirstNumber() {
        return firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public String  getOperator() {
        return operator;
    }
}
