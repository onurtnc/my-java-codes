import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the first number
        System.out.print("Enter the first number: ");
        double number1 = scanner.nextDouble();

        // Read the second number
        System.out.print("Enter the second number: ");
        double number2 = scanner.nextDouble();

        // Operation selection
        System.out.println("\nSelect the operation you want to perform:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.print("Your choice (1-4): ");

        int choice = scanner.nextInt();
        double result = 0;
        boolean validOperation = true;

        // Perform the operation
        switch (choice) {
            case 1:
                result = number1 + number2;
                System.out.println("\nResult: " + number1 + " + " + number2 + " = " + result);
                break;
            case 2:
                result = number1 - number2;
                System.out.println("\nResult: " + number1 + " - " + number2 + " = " + result);
                break;
            case 3:
                result = number1 * number2;
                System.out.println("\nResult: " + number1 + " * " + number2 + " = " + result);
                break;
            case 4:
                if (number2 != 0) {
                    result = number1 / number2;
                    System.out.println("\nResult: " + number1 + " / " + number2 + " = " + result);
                } else {
                    System.out.println("\nError: A number cannot be divided by zero!");
                    validOperation = false;
                }
                break;
            default:
                System.out.println("\nInvalid operation choice!");
                validOperation = false;
        }

        scanner.close();
    }
}
