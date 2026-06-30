import java.sql.*;
import java.util.Scanner;

public class EmployeePayrollSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/payroll_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("     EMPLOYEE PAYROLL SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Salary");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addEmployee();
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    searchEmployee();
                    break;

                case 4:
                    updateSalary();
                    break;

                case 5:
                    deleteEmployee();
                    break;

                case 6:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addEmployee() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            String sql =
                    "INSERT INTO employees VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, department);
            ps.setDouble(4, salary);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee Added Successfully!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void viewEmployees() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT * FROM employees";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println("----------------------");
                System.out.println("ID         : " + rs.getInt("emp_id"));
                System.out.println("Name       : " + rs.getString("emp_name"));
                System.out.println("Department : " + rs.getString("department"));
                System.out.println("Salary     : ₹" + rs.getDouble("salary"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void searchEmployee() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            String sql =
                    "SELECT * FROM employees WHERE emp_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("ID         : " + rs.getInt("emp_id"));
                System.out.println("Name       : " + rs.getString("emp_name"));
                System.out.println("Department : " + rs.getString("department"));
                System.out.println("Salary     : ₹" + rs.getDouble("salary"));

            } else {

                System.out.println("Employee Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateSalary() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();

            String sql =
                    "UPDATE employees SET salary=? WHERE emp_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setDouble(1, salary);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Salary Updated Successfully!");

            } else {

                System.out.println("Employee Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteEmployee() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            String sql =
                    "DELETE FROM employees WHERE emp_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Employee Deleted Successfully!");

            } else {

                System.out.println("Employee Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
