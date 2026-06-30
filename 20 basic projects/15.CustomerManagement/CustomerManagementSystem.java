import java.sql.*;
import java.util.Scanner;

public class CustomerManagementSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/customer_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("   CUSTOMER MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addCustomer();
                    break;

                case 2:
                    viewCustomers();
                    break;

                case 3:
                    searchCustomer();
                    break;

                case 4:
                    updateCustomer();
                    break;

                case 5:
                    deleteCustomer();
                    break;

                case 6:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addCustomer() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Customer ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Mobile: ");
            String mobile = sc.nextLine();

            System.out.print("City: ");
            String city = sc.nextLine();

            String sql =
                "INSERT INTO customers VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, mobile);
            ps.setString(5, city);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Customer Added Successfully!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void viewCustomers() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = con.createStatement();

            ResultSet rs =
                st.executeQuery("SELECT * FROM customers");

            while (rs.next()) {

                System.out.println("\n---------------------");
                System.out.println("ID     : " +
                    rs.getInt("customer_id"));
                System.out.println("Name   : " +
                    rs.getString("customer_name"));
                System.out.println("Email  : " +
                    rs.getString("email"));
                System.out.println("Mobile : " +
                    rs.getString("mobile"));
                System.out.println("City   : " +
                    rs.getString("city"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void searchCustomer() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();

            String sql =
                "SELECT * FROM customers WHERE customer_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("ID     : " +
                    rs.getInt("customer_id"));
                System.out.println("Name   : " +
                    rs.getString("customer_name"));
                System.out.println("Email  : " +
                    rs.getString("email"));
                System.out.println("Mobile : " +
                    rs.getString("mobile"));
                System.out.println("City   : " +
                    rs.getString("city"));

            } else {

                System.out.println("Customer Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateCustomer() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New City: ");
            String city = sc.nextLine();

            String sql =
                "UPDATE customers SET city=? WHERE customer_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setString(1, city);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Customer Updated Successfully!");

            } else {

                System.out.println("Customer Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteCustomer() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();

            String sql =
                "DELETE FROM customers WHERE customer_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Customer Deleted Successfully!");

            } else {

                System.out.println("Customer Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
