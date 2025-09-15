import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        String input;
        System.out.printf("Введите первое число: ");
        input = scanner.next();
        if (input.contains(",")) {
            input = input.replace(",", ".");
        }
        calculator.setFirstNumber(Double.parseDouble(input));

        System.out.printf("Введите операцию: ");
        input = scanner.next();
        calculator.setOperator(input);

        System.out.printf("Введите второе число: ");
        input = scanner.next();
        if (input.contains(",")) {
            input = input.replace(",", ".");
        }
        calculator.setSecondNumber(Double.parseDouble(input));

        System.out.println("\nРезультат:");
        calculator.solve();
        System.out.println("\nРабота программы завершена.");
    }
}
