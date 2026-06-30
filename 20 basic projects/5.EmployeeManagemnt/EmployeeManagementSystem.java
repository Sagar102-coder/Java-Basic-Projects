import java.util.ArrayList;
import java.util.Scanner;

class Employee {

    int empId;
    String empName;
    String department;
    double salary;

    Employee(int empId, String empName, String department, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.salary = salary;
    }

    void display() {
        System.out.println("\n-----------------------------");
        System.out.println("Employee ID   : " + empId);
        System.out.println("Employee Name : " + empName);
        System.out.println("Department    : " + department);
        System.out.println("Salary        : ₹" + salary);
        System.out.println("-----------------------------");
    }
}

public class EmployeeManagementSystem {

    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("    EMPLOYEE MANAGEMENT SYSTEM");
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
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addEmployee() {

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        Employee employee = new Employee(id, name, dept, salary);

        employees.add(employee);

        System.out.println("Employee Added Successfully!");
    }

    public static void viewEmployees() {

        if (employees.isEmpty()) {
            System.out.println("No Employees Found.");
            return;
        }

        for (Employee emp : employees) {
            emp.display();
        }
    }

    public static void searchEmployee() {

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        for (Employee emp : employees) {

            if (emp.empId == id) {

                System.out.println("Employee Found!");
                emp.display();
                return;
            }
        }

        System.out.println("Employee Not Found.");
    }

    public static void updateSalary() {

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        for (Employee emp : employees) {

            if (emp.empId == id) {

                System.out.print("Enter New Salary: ");
                double newSalary = sc.nextDouble();

                emp.salary = newSalary;

                System.out.println("Salary Updated Successfully!");
                return;
            }
        }

        System.out.println("Employee Not Found.");
    }

    public static void deleteEmployee() {

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        Employee deleteEmployee = null;

        for (Employee emp : employees) {

            if (emp.empId == id) {
                deleteEmployee = emp;
                break;
            }
        }

        if (deleteEmployee != null) {

            employees.remove(deleteEmployee);

            System.out.println("Employee Deleted Successfully!");

        } else {

            System.out.println("Employee Not Found.");
        }
    }
}