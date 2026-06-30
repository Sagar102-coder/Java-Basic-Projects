import java.util.ArrayList;
import java.util.Scanner;

class Room {

    int roomNumber;
    String roomType;
    boolean booked;
    String customerName;

    Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.booked = false;
        this.customerName = "";
    }

    void display() {

        System.out.println("\n---------------------------");
        System.out.println("Room Number : " + roomNumber);
        System.out.println("Room Type   : " + roomType);
        System.out.println("Status      : " + (booked ? "Booked" : "Available"));

        if (booked) {
            System.out.println("Customer    : " + customerName);
        }

        System.out.println("---------------------------");
    }
}

public class HotelBookingSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("      HOTEL BOOKING SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Room");
            System.out.println("2. View Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Search Room");
            System.out.println("6. Delete Room");
            System.out.println("7. Exit");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addRoom();
                    break;

                case 2:
                    viewRooms();
                    break;

                case 3:
                    bookRoom();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    searchRoom();
                    break;

                case 6:
                    deleteRoom();
                    break;

                case 7:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addRoom() {

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Room Type (Single/Double): ");
        String roomType = sc.nextLine();

        Room room = new Room(roomNo, roomType);

        rooms.add(room);

        System.out.println("Room Added Successfully!");
    }

    public static void viewRooms() {

        if (rooms.isEmpty()) {

            System.out.println("No Rooms Available.");
            return;
        }

        for (Room room : rooms) {
            room.display();
        }
    }

    public static void bookRoom() {

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo) {

                if (!room.booked) {

                    System.out.print("Enter Customer Name: ");
                    String customer = sc.nextLine();

                    room.booked = true;
                    room.customerName = customer;

                    System.out.println("Room Booked Successfully!");

                } else {

                    System.out.println("Room Already Booked!");
                }

                return;
            }
        }

        System.out.println("Room Not Found.");
    }

    public static void cancelBooking() {

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo) {

                if (room.booked) {

                    room.booked = false;
                    room.customerName = "";

                    System.out.println("Booking Cancelled Successfully!");

                } else {

                    System.out.println("Room is Already Available.");
                }

                return;
            }
        }

        System.out.println("Room Not Found.");
    }

    public static void searchRoom() {

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo) {

                room.display();
                return;
            }
        }

        System.out.println("Room Not Found.");
    }

    public static void deleteRoom() {

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        Room deleteRoom = null;

        for (Room room : rooms) {

            if (room.roomNumber == roomNo) {

                deleteRoom = room;
                break;
            }
        }

        if (deleteRoom != null) {

            rooms.remove(deleteRoom);

            System.out.println("Room Deleted Successfully!");

        } else {

            System.out.println("Room Not Found.");
        }
    }
}