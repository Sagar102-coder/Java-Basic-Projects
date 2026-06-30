import java.util.Scanner;

public class CalculatorApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n================================");
            System.out.println("      CALCULATOR APPLICATION");
            System.out.println("================================");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Modulus");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

            if (choice == 6) {
                System.out.println("Thank You!");
                break;
            }

            System.out.print("Enter First Number: ");
            double num1 = sc.nextDouble();

            System.out.print("Enter Second Number: ");
            double num2 = sc.nextDouble();

            switch (choice) {

                case 1:
                    System.out.println("Result = " + (num1 + num2));
                    break;

                case 2:
                    System.out.println("Result = " + (num1 - num2));
                    break;

                case 3:
                    System.out.println("Result = " + (num1 * num2));
                    break;

                case 4:

                    if (num2 == 0) {
                        System.out.println("Cannot divide by zero!");
                    } else {
                        System.out.println("Result = " + (num1 / num2));
                    }

                    break;

                case 5:
                    System.out.println("Result = " + (num1 % num2));
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }

        sc.close();
    }
}