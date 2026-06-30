import java.util.Scanner;

class ATMAccount {

    private int pin;
    private double balance;

    ATMAccount(int pin, double balance) {
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePin(int enteredPin) {
        return pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {

        if (amount <= balance) {
            balance -= amount;
            return true;
        }

        return false;
    }

    public void changePin(int newPin) {
        pin = newPin;
    }
}

    public class ATMSimulationSystem {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ATMAccount account = new ATMAccount(1234, 10000);

        System.out.println("================================");
        System.out.println("      ATM SIMULATION SYSTEM");
        System.out.println("================================");

        System.out.print("Enter ATM PIN: ");
        int enteredPin = sc.nextInt();

        if (!account.validatePin(enteredPin)) {

            System.out.println("Invalid PIN!");
            return;
        }

        System.out.println("Login Successful!");

        while (true) {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Current Balance: ₹" + account.getBalance());
                    break;

                case 2:

                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = sc.nextDouble();

                    account.deposit(depositAmount);

                    System.out.println("Deposit Successful!");
                    break;

                case 3:

                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = sc.nextDouble();

                    if (account.withdraw(withdrawAmount)) {

                        System.out.println("Withdrawal Successful!");

                    } else {

                        System.out.println("Insufficient Balance!");
                    }

                    break;

                case 4:

                    System.out.print("Enter New PIN: ");
                    int newPin = sc.nextInt();

                    account.changePin(newPin);

                    System.out.println("PIN Changed Successfully!");
                    break;

                case 5:

                    System.out.println("Thank You For Using ATM!");
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice!");
            }
        }
    }
}