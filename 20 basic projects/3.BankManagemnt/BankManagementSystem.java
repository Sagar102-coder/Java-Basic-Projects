import java.util.ArrayList;
import java.util.Scanner;

class Account {

    int accountNumber;
    String accountHolder;
    double balance;

    Account(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    void display() {
        System.out.println("\n------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Balance        : ₹" + balance);
        System.out.println("------------------------");
    }
}

public class BankManagementSystem {

    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("      BANK MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Check Balance");
            System.out.println("6. Delete Account");
            System.out.println("7. View All Accounts");
            System.out.println("8. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    viewAccount();
                    break;

                case 3:
                    depositMoney();
                    break;

                case 4:
                    withdrawMoney();
                    break;

                case 5:
                    checkBalance();
                    break;

                case 6:
                    deleteAccount();
                    break;

                case 7:
                    viewAllAccounts();
                    break;

                case 8:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void createAccount() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double balance = sc.nextDouble();

        Account account = new Account(accNo, name, balance);

        accounts.add(account);

        System.out.println("Account Created Successfully!");
    }

    public static void viewAccount() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        for (Account acc : accounts) {

            if (acc.accountNumber == accNo) {
                acc.display();
                return;
            }
        }

        System.out.println("Account Not Found!");
    }

    public static void depositMoney() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        for (Account acc : accounts) {

            if (acc.accountNumber == accNo) {

                System.out.print("Enter Amount to Deposit: ");
                double amount = sc.nextDouble();

                acc.balance += amount;

                System.out.println("Deposit Successful!");
                System.out.println("Updated Balance: ₹" + acc.balance);

                return;
            }
        }

        System.out.println("Account Not Found!");
    }

    public static void withdrawMoney() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        for (Account acc : accounts) {

            if (acc.accountNumber == accNo) {

                System.out.print("Enter Withdrawal Amount: ");
                double amount = sc.nextDouble();

                if (amount <= acc.balance) {

                    acc.balance -= amount;

                    System.out.println("Withdrawal Successful!");
                    System.out.println("Remaining Balance: ₹" + acc.balance);

                } else {

                    System.out.println("Insufficient Balance!");
                }

                return;
            }
        }

        System.out.println("Account Not Found!");
    }

    public static void checkBalance() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        for (Account acc : accounts) {

            if (acc.accountNumber == accNo) {

                System.out.println("Current Balance: ₹" + acc.balance);
                return;
            }
        }

        System.out.println("Account Not Found!");
    }

    public static void deleteAccount() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        Account deleteAccount = null;

        for (Account acc : accounts) {

            if (acc.accountNumber == accNo) {
                deleteAccount = acc;
                break;
            }
        }

        if (deleteAccount != null) {

            accounts.remove(deleteAccount);

            System.out.println("Account Deleted Successfully!");

        } else {

            System.out.println("Account Not Found!");
        }
    }

    public static void viewAllAccounts() {

        if (accounts.isEmpty()) {

            System.out.println("No Accounts Available.");
            return;
        }

        for (Account acc : accounts) {
            acc.display();
        }
    }
}