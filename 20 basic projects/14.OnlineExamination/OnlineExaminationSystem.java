import java.sql.*;
import java.util.Scanner;

public class OnlineExaminationSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/exam_db";

    static final String USER = "root";
    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while(true) {

            System.out.println("\n================================");
            System.out.println("     ONLINE EXAMINATION SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Question");
            System.out.println("2. View Questions");
            System.out.println("3. Take Exam");
            System.out.println("4. Delete Question");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    addQuestion();
                    break;

                case 2:
                    viewQuestions();
                    break;

                case 3:
                    takeExam();
                    break;

                case 4:
                    deleteQuestion();
                    break;

                case 5:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addQuestion() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Question ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Question: ");
            String question = sc.nextLine();

            System.out.print("Option 1: ");
            String op1 = sc.nextLine();

            System.out.print("Option 2: ");
            String op2 = sc.nextLine();

            System.out.print("Option 3: ");
            String op3 = sc.nextLine();

            System.out.print("Option 4: ");
            String op4 = sc.nextLine();

            System.out.print("Correct Answer (1-4): ");
            int answer = sc.nextInt();

            String sql =
                "INSERT INTO questions VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, question);
            ps.setString(3, op1);
            ps.setString(4, op2);
            ps.setString(5, op3);
            ps.setString(6, op4);
            ps.setInt(7, answer);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Question Added Successfully!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void viewQuestions() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM questions");

            while(rs.next()) {

                System.out.println("\n--------------------");
                System.out.println(rs.getInt("question_id"));
                System.out.println(rs.getString("question"));
                System.out.println("1. " + rs.getString("option1"));
                System.out.println("2. " + rs.getString("option2"));
                System.out.println("3. " + rs.getString("option3"));
                System.out.println("4. " + rs.getString("option4"));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void takeExam() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM questions");

            int score = 0;
            int total = 0;

            while(rs.next()) {

                total++;

                System.out.println("\n--------------------");
                System.out.println(rs.getString("question"));
                System.out.println("1. " + rs.getString("option1"));
                System.out.println("2. " + rs.getString("option2"));
                System.out.println("3. " + rs.getString("option3"));
                System.out.println("4. " + rs.getString("option4"));

                System.out.print("Your Answer: ");

                int answer = sc.nextInt();

                if(answer ==
                   rs.getInt("correct_answer")) {

                    score++;
                }
            }

            System.out.println("\n========== RESULT ==========");
            System.out.println("Total Questions : " + total);
            System.out.println("Correct Answers : " + score);
            System.out.println("Score           : "
                               + score + "/" + total);

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteQuestion() {

        try {

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Question ID: ");

            int id = sc.nextInt();

            String sql =
                    "DELETE FROM questions WHERE question_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {

                System.out.println("Question Deleted!");

            } else {

                System.out.println("Question Not Found!");
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
