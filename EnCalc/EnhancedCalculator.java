import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnhancedCalculator {

    private static List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculation = true;

        while (continueCalculation) {
            System.out.println("Choose an operation:");
            System.out.println("1. Basic Arithmetic");
            System.out.println("2. Scientific Calculations");
            System.out.println("3. Unit Conversions");
            System.out.println("4. View Calculation History");
            System.out.println("5. Exit");

            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    basicArithmetic(scanner);
                    break;
                case 2:
                    scientificCalculations(scanner);
                    break;
                case 3:
                    unitConversions(scanner);
                    break;
                case 4:
                    viewHistory();
                    break;
                case 5:
                    continueCalculation = false;
                    System.out.println("Exiting the calculator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private static void basicArithmetic(Scanner scanner) {
        System.out.println("Basic Arithmetic Operations:");
        System.out.println("Enter first number:");
        double num1 = getValidDouble(scanner);
        System.out.println("Enter second number:");
        double num2 = getValidDouble(scanner);

        System.out.println("Choose an operation: +, -, *, /");
        char operation = scanner.next().charAt(0);

        double result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid operation.");
                return;
        }
        System.out.println("Result: " + result);
        history.add(num1 + " " + operation + " " + num2 + " = " + result);
    }

    private static void scientificCalculations(Scanner scanner) {
        System.out.println("Scientific Calculations:");
        System.out.println("Choose an operation: sqrt (square root), exp (exponentiation), sin, cos, tan, log, factorial");
        String operation = scanner.next();

        switch (operation) {
            case "sqrt":
                System.out.println("Enter a number:");
                double num = getValidDouble(scanner);
                if (num < 0) {
                    System.out.println("Error: Cannot compute square root of a negative number.");
                } else {
                    double sqrtResult = Math.sqrt(num);
                    System.out.println("Result: " + sqrtResult);
                    history.add("sqrt(" + num + ") = " + sqrtResult);
                }
                break;
            case "exp":
                System.out.println("Enter base:");
                double base = getValidDouble(scanner);
                System.out.println("Enter exponent:");
                double exponent = getValidDouble(scanner);
                double expResult = Math.pow(base, exponent);
                System.out.println("Result: " + expResult);
                history.add(base + "^" + exponent + " = " + expResult);
                break;
            case "sin":
                System.out.println("Enter angle in degrees:");
                double angleSin = getValidDouble(scanner);
                double sinResult = Math.sin(Math.toRadians(angleSin));
                System.out.println("Result: " + sinResult);
                history.add("sin(" + angleSin + "°) = " + sinResult);
                break;
            case "cos":
                System.out.println("Enter angle in degrees:");
                double angleCos = getValidDouble(scanner);
                double cosResult = Math.cos(Math.toRadians(angleCos));
                System.out.println("Result: " + cosResult);
                history.add("cos(" + angleCos + " °) = " + cosResult);
                break;
            case "tan":
                System.out.println("Enter angle in degrees:");
                double angleTan = getValidDouble(scanner);
                double tanResult = Math.tan(Math.toRadians(angleTan));
                System.out.println("Result: " + tanResult);
                history.add("tan(" + angleTan + "°) = " + tanResult);
                break;
            case "log":
                System.out.println("Enter a number:");
                double logNum = getValidDouble(scanner);
                if (logNum <= 0) {
                    System.out.println("Error: Logarithm of non-positive number is undefined.");
                } else {
                    double logResult = Math.log(logNum);
                    System.out.println("Result: " + logResult);
                    history.add("log(" + logNum + ") = " + logResult);
                }
                break;
            case "factorial":
                System.out.println("Enter a non-negative integer:");
                int factorialNum = getValidInteger(scanner);
                if (factorialNum < 0) {
                    System.out.println("Error: Factorial of a negative number is undefined.");
                } else {
                    long factorialResult = factorial(factorialNum);
                    System.out.println("Result: " + factorialResult);
                    history.add(factorialNum + "! = " + factorialResult);
                }
                break;
            default:
                System.out.println("Invalid operation.");
        }
    }

    private static void unitConversions(Scanner scanner) {
        System.out.println("Unit Conversions:");
        System.out.println("Choose a conversion: 1. Temperature, 2. Length, 3. Weight");
        int choice = getValidInteger(scanner);

        switch (choice) {
            case 1:
                System.out.println("Temperature Conversion:");
                System.out.println("Enter temperature in Celsius:");
                double celsius = getValidDouble(scanner);
                double fahrenheit = (celsius * 9/5) + 32;
                System.out.println("Temperature in Fahrenheit: " + fahrenheit);
                history.add(celsius + "°C = " + fahrenheit + "°F");
                break;
            case 2:
                System.out.println("Length Conversion:");
                System.out.println("Enter length in meters:");
                double meters = getValidDouble(scanner);
                double feet = meters * 3.28084;
                System.out.println("Length in feet: " + feet);
                history.add(meters + "m = " + feet + "ft");
                break;
            case 3:
                System.out.println("Weight Conversion:");
                System.out.println("Enter weight in kilograms:");
                double kilograms = getValidDouble(scanner);
                double pounds = kilograms * 2.20462;
                System.out.println("Weight in pounds: " + pounds);
                history.add(kilograms + "kg = " + pounds + "lbs");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void viewHistory() {
        System.out.println("Calculation History:");
        if (history.isEmpty()) {
            System.out.println("No calculations performed yet.");
        } else {
            for (String entry : history) {
                System.out.println(entry);
            }
        }
    }

    private static double getValidDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number:");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static int getValidInteger(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer:");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static long factorial(int num) {
        if (num == 0) return 1;
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}