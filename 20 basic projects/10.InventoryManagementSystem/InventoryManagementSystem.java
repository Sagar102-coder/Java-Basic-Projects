import java.util.ArrayList;
import java.util.Scanner;

class Product {

    int productId;
    String productName;
    int quantity;
    double price;

    Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    void display() {

        System.out.println("\n-----------------------------");
        System.out.println("Product ID   : " + productId);
        System.out.println("Product Name : " + productName);
        System.out.println("Quantity     : " + quantity);
        System.out.println("Price        : ₹" + price);
        System.out.println("-----------------------------");
    }
}

public class InventoryManagementSystem {

    static ArrayList<Product> products = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("    INVENTORY MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Quantity");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

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
                    updateQuantity();
                    break;

                case 5:
                    deleteProduct();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        Product product = new Product(id, name, quantity, price);

        products.add(product);

        System.out.println("Product Added Successfully!");
    }

    public static void viewProducts() {

        if (products.isEmpty()) {

            System.out.println("No Products Available.");
            return;
        }

        for (Product product : products) {
            product.display();
        }
    }

    public static void searchProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        for (Product product : products) {

            if (product.productId == id) {

                System.out.println("Product Found!");
                product.display();
                return;
            }
        }

        System.out.println("Product Not Found.");
    }

    public static void updateQuantity() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        for (Product product : products) {

            if (product.productId == id) {

                System.out.print("Enter New Quantity: ");
                int newQuantity = sc.nextInt();

                product.quantity = newQuantity;

                System.out.println("Quantity Updated Successfully!");
                return;
            }
        }

        System.out.println("Product Not Found.");
    }

    public static void deleteProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product deleteProduct = null;

        for (Product product : products) {

            if (product.productId == id) {

                deleteProduct = product;
                break;
            }
        }

        if (deleteProduct != null) {

            products.remove(deleteProduct);

            System.out.println("Product Deleted Successfully!");

        } else {

            System.out.println("Product Not Found.");
        }
    }
}