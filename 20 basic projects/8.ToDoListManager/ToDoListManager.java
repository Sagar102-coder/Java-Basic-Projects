import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListManager {

    static ArrayList<String> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("        TO-DO LIST MANAGER");
            System.out.println("================================");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Search Task");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addTask();
                    break;

                case 2:
                    viewTasks();
                    break;

                case 3:
                    updateTask();
                    break;

                case 4:
                    deleteTask();
                    break;

                case 5:
                    searchTask();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addTask() {

        System.out.print("Enter Task: ");
        String task = sc.nextLine();

        tasks.add(task);

        System.out.println("Task Added Successfully!");
    }

    public static void viewTasks() {

        if (tasks.isEmpty()) {
            System.out.println("No Tasks Available.");
            return;
        }

        System.out.println("\n===== TASK LIST =====");

        for (int i = 0; i < tasks.size(); i++) {

            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void updateTask() {

        viewTasks();

        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter Task Number to Update: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 1 || index > tasks.size()) {

            System.out.println("Invalid Task Number!");
            return;
        }

        System.out.print("Enter New Task: ");
        String newTask = sc.nextLine();

        tasks.set(index - 1, newTask);

        System.out.println("Task Updated Successfully!");
    }

    public static void deleteTask() {

        viewTasks();

        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter Task Number to Delete: ");
        int index = sc.nextInt();

        if (index < 1 || index > tasks.size()) {

            System.out.println("Invalid Task Number!");
            return;
        }

        tasks.remove(index - 1);

        System.out.println("Task Deleted Successfully!");
    }

    public static void searchTask() {

        System.out.print("Enter Task Name to Search: ");
        String search = sc.nextLine();

        boolean found = false;

        for (String task : tasks) {

            if (task.equalsIgnoreCase(search)) {

                System.out.println("Task Found: " + task);
                found = true;
            }
        }

        if (!found) {

            System.out.println("Task Not Found.");
        }
    }
}