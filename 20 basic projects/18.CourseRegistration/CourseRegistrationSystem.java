import java.sql.*;
import java.util.Scanner;

public class CourseRegistrationSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/course_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while(true) {

            System.out.println("\n===============================");
            System.out.println(" COURSE REGISTRATION SYSTEM");
            System.out.println("===============================");
            System.out.println("1. Add Course");
            System.out.println("2. Register Student");
            System.out.println("3. View Registrations");
            System.out.println("4. Search Course");
            System.out.println("5. Drop Course");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    addCourse();
                    break;

                case 2:
                    registerStudent();
                    break;

                case 3:
                    viewRegistrations();
                    break;

                case 4:
                    searchCourse();
                    break;

                case 5:
                    dropCourse();
                    break;

                case 6:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addCourse() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Course ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Course Name: ");
            String courseName = sc.nextLine();

            System.out.print("Instructor Name: ");
            String instructor = sc.nextLine();

            String sql =
                "INSERT INTO courses VALUES(?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, courseName);
            ps.setString(3, instructor);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Course Added Successfully!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void registerStudent() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Registration ID: ");
            int regId = sc.nextInt();
            sc.nextLine();

            System.out.print("Student Name: ");
            String studentName = sc.nextLine();

            System.out.print("Course ID: ");
            int courseId = sc.nextInt();

            String sql =
                "INSERT INTO registrations VALUES(?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, regId);
            ps.setString(2, studentName);
            ps.setInt(3, courseId);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Student Registered Successfully!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void viewRegistrations() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            String sql =
                "SELECT r.reg_id, r.student_name, " +
                "c.course_name, c.instructor " +
                "FROM registrations r " +
                "JOIN courses c " +
                "ON r.course_id = c.course_id";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {

                System.out.println("\n-------------------");
                System.out.println("Registration ID : "
                        + rs.getInt("reg_id"));

                System.out.println("Student Name    : "
                        + rs.getString("student_name"));

                System.out.println("Course Name     : "
                        + rs.getString("course_name"));

                System.out.println("Instructor      : "
                        + rs.getString("instructor"));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void searchCourse() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Course ID: ");

            int id = sc.nextInt();

            String sql =
                "SELECT * FROM courses WHERE course_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                System.out.println("Course ID   : "
                        + rs.getInt("course_id"));

                System.out.println("Course Name : "
                        + rs.getString("course_name"));

                System.out.println("Instructor  : "
                        + rs.getString("instructor"));

            } else {

                System.out.println("Course Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void dropCourse() {

        try {

            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Registration ID: ");

            int regId = sc.nextInt();

            String sql =
                "DELETE FROM registrations WHERE reg_id=?";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, regId);

            int rows = ps.executeUpdate();

            if(rows > 0) {

                System.out.println("Course Dropped Successfully!");

            } else {

                System.out.println("Registration Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}