import java.util.ArrayList;
import java.util.Scanner;


class Room {
    private int roomNumber;
    private String roomType;
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - Price: Rs" + price + " - " +
                (isAvailable ? "Available" : "Booked");
    }
}


class Reservation {
    private String customerName;
    private Room room;

    public Reservation(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Customer: " + customerName + ", Room: " + room.getRoomNumber() + " (" + room.getRoomType() + ")";
    }
}


class HotelManager {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    public HotelManager() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        
        rooms.add(new Room(101, "Single", 1000.00));
        rooms.add(new Room(102, "Double", 1500.00));
        rooms.add(new Room(201, "Suite", 3000.00));
        rooms.add(new Room(202, "Deluxe", 20000.00));
    }

    public void displayAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public void makeReservation(String customerName, int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (room.isAvailable()) {
                    room.setAvailable(false);
                    Reservation reservation = new Reservation(customerName, room);
                    reservations.add(reservation);
                    System.out.println("Reservation successful for " + customerName + " in Room " + roomNumber);
                    return;
                } else {
                    System.out.println("Sorry, Room " + roomNumber + " is already booked.");
                    return;
                }
            }
        }
        System.out.println("Room " + roomNumber + " does not exist.");
    }

    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet.");
        } else {
            System.out.println("\nCurrent Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    public void processPayment(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (!room.isAvailable()) {
                    System.out.println("Processing payment for Room " + roomNumber + " (Rs" + room.getPrice() + ")");
                    System.out.println("Payment successful!");
                    return;
                } else {
                    System.out.println("Room " + roomNumber + " is not reserved.");
                    return;
                }
            }
        }
        System.out.println("Room " + roomNumber + " does not exist.");
    }
}


public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelManager manager = new HotelManager();

        while (true) {
            System.out.println("\n*** Hotel Reservation System ***");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Process Payment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    manager.displayAvailableRooms();
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    manager.makeReservation(name, roomNumber);
                    break;

                case 3:
                    manager.viewReservations();
                    break;

                case 4:
                    System.out.print("Enter room number for payment: ");
                    int paymentRoomNumber = scanner.nextInt();
                    manager.processPayment(paymentRoomNumber);
                    break;

                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
