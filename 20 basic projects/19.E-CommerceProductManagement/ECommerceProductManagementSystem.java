import java.sql.*;
import java.util.Scanner;

public class ECommerceProductManagementSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/ecommerce_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while(true) {

            System.out.println("\n=================================");
            System.out.println(" E-COMMERCE PRODUCT MANAGEMENT ");
            System.out.println("=================================");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Stock");
            System.out.println("5. Delete Product");
            System.out.println("6. Calculate Inventory Value");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    viewProducts();
                    break;

                case 3:
                    searchProduct();
                    break;

                case 4:
                    updateStock();
                    break;

                case 5:
                    deleteProduct();
                    break;

                case 6:
                    calculateInventoryValue();
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addProduct() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Product ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Product Name: ");
            String name = sc.nextLine();

            System.out.print("Price: ");
            double price = sc.nextDouble();

            System.out.print("Quantity: ");
            int quantity = sc.nextInt();

            String sql =
                "INSERT INTO products VALUES(?,?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, quantity);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Product Added Successfully!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void viewProducts() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = con.createStatement();

            ResultSet rs =
                st.executeQuery("SELECT * FROM products");

            while(rs.next()) {

                System.out.println("\n-------------------");
                System.out.println("Product ID   : " +
                        rs.getInt("product_id"));

                System.out.println("Product Name : " +
                        rs.getString("product_name"));

                System.out.println("Price        : ₹" +
                        rs.getDouble("price"));

                System.out.println("Quantity     : " +
                        rs.getInt("quantity"));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void searchProduct() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Product ID: ");

            int id = sc.nextInt();

            String sql =
                "SELECT * FROM products WHERE product_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                System.out.println("Product ID   : "
                        + rs.getInt("product_id"));

                System.out.println("Product Name : "
                        + rs.getString("product_name"));

                System.out.println("Price        : ₹"
                        + rs.getDouble("price"));

                System.out.println("Quantity     : "
                        + rs.getInt("quantity"));

            } else {

                System.out.println("Product Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void updateStock() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Product ID: ");

            int id = sc.nextInt();

            System.out.print("Enter New Quantity: ");

            int quantity = sc.nextInt();

            String sql =
                "UPDATE products SET quantity=? WHERE product_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {

                System.out.println("Stock Updated Successfully!");

            } else {

                System.out.println("Product Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteProduct() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Product ID: ");

            int id = sc.nextInt();

            String sql =
                "DELETE FROM products WHERE product_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {

                System.out.println("Product Deleted Successfully!");

            } else {

                System.out.println("Product Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void calculateInventoryValue() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = con.createStatement();

            ResultSet rs =
                st.executeQuery(
                    "SELECT price, quantity FROM products");

            double totalValue = 0;

            while(rs.next()) {

                totalValue +=
                        rs.getDouble("price") *
                        rs.getInt("quantity");
            }

            System.out.println("\nTotal Inventory Value = ₹"
                    + totalValue);

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}