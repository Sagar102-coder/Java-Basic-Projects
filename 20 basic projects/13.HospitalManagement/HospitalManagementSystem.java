import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/hospital_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("    HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Update Disease");
            System.out.println("5. Delete Patient");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addPatient();
                    break;

                case 2:
                    viewPatients();
                    break;

                case 3:
                    searchPatient();
                    break;

                case 4:
                    updateDisease();
                    break;

                case 5:
                    deletePatient();
                    break;

                case 6:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addPatient() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Patient ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Patient Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Disease: ");
            String disease = sc.nextLine();

            String sql =
                    "INSERT INTO patients VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, disease);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Patient Added Successfully!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void viewPatients() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT * FROM patients";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println("----------------------");
                System.out.println("Patient ID   : " +
                        rs.getInt("patient_id"));

                System.out.println("Patient Name : " +
                        rs.getString("patient_name"));

                System.out.println("Age          : " +
                        rs.getInt("age"));

                System.out.println("Disease      : " +
                        rs.getString("disease"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void searchPatient() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Patient ID: ");
            int id = sc.nextInt();

            String sql =
                    "SELECT * FROM patients WHERE patient_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Patient ID   : " +
                        rs.getInt("patient_id"));

                System.out.println("Patient Name : " +
                        rs.getString("patient_name"));

                System.out.println("Age          : " +
                        rs.getInt("age"));

                System.out.println("Disease      : " +
                        rs.getString("disease"));

            } else {

                System.out.println("Patient Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateDisease() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Patient ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Disease: ");
            String disease = sc.nextLine();

            String sql =
                    "UPDATE patients SET disease=? WHERE patient_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, disease);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Disease Updated Successfully!");

            } else {

                System.out.println("Patient Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deletePatient() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Patient ID: ");
            int id = sc.nextInt();

            String sql =
                    "DELETE FROM patients WHERE patient_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Patient Deleted Successfully!");

            } else {

                System.out.println("Patient Not Found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
