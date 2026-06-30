import java.util.ArrayList;
import java.util.Scanner;

class Book {

    int bookId;
    String bookName;
    String author;
    boolean issued;

    Book(int bookId, String bookName, String author) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.issued = false;
    }

    void display() {

        System.out.println("\n----------------------");
        System.out.println("Book ID      : " + bookId);
        System.out.println("Book Name    : " + bookName);
        System.out.println("Author       : " + author);
        System.out.println("Status       : " + (issued ? "Issued" : "Available"));
    }
}

public class LibraryManagementSystem {

    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("     LIBRARY MANAGEMENT");
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

            switch (choice) {

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
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        Book book = new Book(id, name, author);

        books.add(book);

        System.out.println("Book Added Successfully!");
    }

    public static void viewBooks() {

        if (books.isEmpty()) {
            System.out.println("No Books Available.");
            return;
        }

        for (Book b : books) {
            b.display();
        }
    }

    public static void searchBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        boolean found = false;

        for (Book b : books) {

            if (b.bookId == id) {

                System.out.println("Book Found!");
                b.display();

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book Not Found.");
        }
    }

    public static void issueBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        for (Book b : books) {

            if (b.bookId == id) {

                if (!b.issued) {

                    b.issued = true;

                    System.out.println("Book Issued Successfully!");

                } else {

                    System.out.println("Book Already Issued!");
                }

                return;
            }
        }

        System.out.println("Book Not Found.");
    }

    public static void returnBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        for (Book b : books) {

            if (b.bookId == id) {

                if (b.issued) {

                    b.issued = false;

                    System.out.println("Book Returned Successfully!");

                } else {

                    System.out.println("Book Already Available!");
                }

                return;
            }
        }

        System.out.println("Book Not Found.");
    }

    public static void deleteBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        Book deleteBook = null;

        for (Book b : books) {

            if (b.bookId == id) {

                deleteBook = b;
                break;
            }
        }

        if (deleteBook != null) {

            books.remove(deleteBook);

            System.out.println("Book Deleted Successfully!");

        } else {

            System.out.println("Book Not Found.");
        }
    }
}