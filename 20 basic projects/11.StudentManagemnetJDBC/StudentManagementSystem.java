import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/student_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println(" STUDENT MANAGEMENT SYSTEM JDBC ");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addStudent() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            String sql =
                    "INSERT INTO students VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, course);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Student Added Successfully!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void viewStudents() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT * FROM students";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {

                System.out.println("----------------");
                System.out.println("ID: " +
                        rs.getInt("id"));

                System.out.println(
                    
                    "Name: " +
                        rs.getString("name"));

                System.out.println("Age: " +
                        rs.getInt("age"));

                System.out.println("Course: " +
                        rs.getString("course"));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void searchStudent() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String sql =
                    "SELECT * FROM students WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                System.out.println("ID: " +
                        rs.getInt("id"));

                System.out.println("Name: " +
                        rs.getString("name"));

                System.out.println("Age: " +
                        rs.getInt("age"));

                System.out.println("Course: " +
                        rs.getString("course"));

            } else {

                System.out.println("Student Not Found");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void updateStudent() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Course: ");
            String course = sc.nextLine();

            String sql =
                    "UPDATE students SET course=? WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, course);
            ps.setInt(2, id);

            int rows =
                    ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Updated Successfully!");
            } else {
                System.out.println("Student Not Found");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteStudent() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String sql =
                    "DELETE FROM students WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows =
                    ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Deleted Successfully!");
            } else {
                System.out.println("Student Not Found");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}