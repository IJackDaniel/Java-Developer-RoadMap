import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Введите первое число: ");
        calculator.setFirstNumber(scanner.nextDouble());

        System.out.printf("Введите операцию: ");
        calculator.setOperator(scanner.next());

        System.out.printf("Введите второе число: ");
        calculator.setSecondNumber(scanner.nextDouble());

        System.out.println("\nРезультат:");
        calculator.solve();
        System.out.println("\nРабота программы завершена!");
    }
}
