import java.sql.*;
import java.util.Scanner;

public class BankManagementSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/bank_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("      BANK MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Search Account");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Delete Account");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    viewAccounts();
                    break;

                case 3:
                    searchAccount();
                    break;

                case 4:
                    depositMoney();
                    break;

                case 5:
                    withdrawMoney();
                    break;

                case 6:
                    deleteAccount();
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void createAccount() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Account Number: ");
            int accountNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Account Type (Saving/Current): ");
            String type = sc.nextLine();

            System.out.print("Initial Balance: ");
            double balance = sc.nextDouble();

            String sql =
                    "INSERT INTO accounts VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, accountNo);
            ps.setString(2, name);
            ps.setString(3, type);
            ps.setDouble(4, balance);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Account Created Successfully!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void viewAccounts() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM accounts");

            while(rs.next()) {

                System.out.println("\n----------------------");
                System.out.println("Account No : " +
                        rs.getInt("account_no"));

                System.out.println("Name       : " +
                        rs.getString("customer_name"));

                System.out.println("Type       : " +
                        rs.getString("account_type"));

                System.out.println("Balance    : ₹" +
                        rs.getDouble("balance"));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void searchAccount() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Account Number: ");
            int accountNo = sc.nextInt();

            String sql =
                    "SELECT * FROM accounts WHERE account_no=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, accountNo);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                System.out.println("Account No : " +
                        rs.getInt("account_no"));

                System.out.println("Name       : " +
                        rs.getString("customer_name"));

                System.out.println("Type       : " +
                        rs.getString("account_type"));

                System.out.println("Balance    : ₹" +
                        rs.getDouble("balance"));

            } else {

                System.out.println("Account Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void depositMoney() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Account Number: ");
            int accountNo = sc.nextInt();

            System.out.print("Deposit Amount: ");
            double amount = sc.nextDouble();

            String sql =
                    "UPDATE accounts SET balance = balance + ? WHERE account_no=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setInt(2, accountNo);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Amount Deposited Successfully!");
            } else {
                System.out.println("Account Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void withdrawMoney() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Account Number: ");
            int accountNo = sc.nextInt();

            System.out.print("Withdraw Amount: ");
            double amount = sc.nextDouble();

            String checkSql =
                    "SELECT balance FROM accounts WHERE account_no=?";

            PreparedStatement checkPs =
                    con.prepareStatement(checkSql);

            checkPs.setInt(1, accountNo);

            ResultSet rs = checkPs.executeQuery();

            if(rs.next()) {

                double currentBalance =
                        rs.getDouble("balance");

                if(currentBalance >= amount) {

                    String updateSql =
                            "UPDATE accounts SET balance = balance - ? WHERE account_no=?";

                    PreparedStatement updatePs =
                            con.prepareStatement(updateSql);

                    updatePs.setDouble(1, amount);
                    updatePs.setInt(2, accountNo);

                    updatePs.executeUpdate();

                    System.out.println("Withdrawal Successful!");

                } else {

                    System.out.println("Insufficient Balance!");
                }

            } else {

                System.out.println("Account Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteAccount() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Account Number: ");
            int accountNo = sc.nextInt();

            String sql =
                    "DELETE FROM accounts WHERE account_no=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, accountNo);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Account Deleted Successfully!");
            } else {
                System.out.println("Account Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}