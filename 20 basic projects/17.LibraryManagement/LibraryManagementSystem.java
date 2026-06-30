import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/library_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while(true) {

            System.out.println("\n================================");
            System.out.println("    LIBRARY MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Delete Book");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    issueBook();
                    break;

                case 5:
                    returnBook();
                    break;

                case 6:
                    deleteBook();
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addBook() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Book ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Book Name: ");
            String name = sc.nextLine();

            System.out.print("Author Name: ");
            String author = sc.nextLine();

            String sql =
                "INSERT INTO books VALUES(?,?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setBoolean(4, true);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Book Added Successfully!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void viewBooks() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = con.createStatement();

            ResultSet rs =
                st.executeQuery("SELECT * FROM books");

            while(rs.next()) {

                System.out.println("\n----------------------");
                System.out.println("Book ID   : " +
                        rs.getInt("book_id"));

                System.out.println("Book Name : " +
                        rs.getString("book_name"));

                System.out.println("Author    : " +
                        rs.getString("author"));

                System.out.println("Status    : " +
                        (rs.getBoolean("available")
                                ? "Available"
                                : "Issued"));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void searchBook() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Book ID: ");

            int id = sc.nextInt();

            String sql =
                "SELECT * FROM books WHERE book_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                System.out.println("Book ID   : " +
                        rs.getInt("book_id"));

                System.out.println("Book Name : " +
                        rs.getString("book_name"));

                System.out.println("Author    : " +
                        rs.getString("author"));

                System.out.println("Status    : " +
                        (rs.getBoolean("available")
                                ? "Available"
                                : "Issued"));

            } else {

                System.out.println("Book Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void issueBook() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Book ID: ");

            int id = sc.nextInt();

            String checkSql =
                "SELECT available FROM books WHERE book_id=?";

            PreparedStatement checkPs =
                con.prepareStatement(checkSql);

            checkPs.setInt(1, id);

            ResultSet rs = checkPs.executeQuery();

            if(rs.next()) {

                boolean available =
                        rs.getBoolean("available");

                if(available) {

                    String updateSql =
                        "UPDATE books SET available=false WHERE book_id=?";

                    PreparedStatement updatePs =
                        con.prepareStatement(updateSql);

                    updatePs.setInt(1, id);

                    updatePs.executeUpdate();

                    System.out.println("Book Issued Successfully!");

                } else {

                    System.out.println("Book Already Issued!");
                }

            } else {

                System.out.println("Book Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void returnBook() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Book ID: ");

            int id = sc.nextInt();

            String sql =
                "UPDATE books SET available=true WHERE book_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {

                System.out.println("Book Returned Successfully!");

            } else {

                System.out.println("Book Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteBook() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Book ID: ");

            int id = sc.nextInt();

            String sql =
                "DELETE FROM books WHERE book_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {

                System.out.println("Book Deleted Successfully!");

            } else {

                System.out.println("Book Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}