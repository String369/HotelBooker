package UIComponents;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class AdminMenu {
    private static final AdminResource adminResource;
    static {
        adminResource = AdminResource.getSingleton();
    }
    public static void adminMenu() {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                printAdminMenu();
                String line = scanner.nextLine();

                if (line.length() != 1) {
                    System.out.println("Error: Invalid action\n");
                    continue;
                }

                char choice = line.charAt(0);
                switch (choice) {
                    case '1':
                        displayAllCustomers();
                        break;
                    case '2':
                        displayAllRooms();
                        break;
                    case '3':
                        displayAllReservations();
                        break;
                    case '4':
                        addRoom();
                        break;
                    case '5':
                        MainMenu.mainMenu();
                        break;
                    default:
                        System.out.println("Unknown action\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }

    private static void addRoom() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter room number:");
        final String roomNumber = scanner.nextLine();

        double roomPrice = enterRoomPrice(scanner);

        RoomType roomType = enterRoomType(scanner);

        final Room room = new Room(roomNumber, roomPrice, roomType);

        try {
            adminResource.addRoom(Collections.singletonList(room));
            System.out.println("Room added successfully!");
        } catch (Exception e) {
            System.out.println("Error: Room could not be added.");
        }

        askToAddAnotherRoom(scanner);
    }

    private static double enterRoomPrice(Scanner scanner) {
        double roomPrice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("Enter price per night:");
            try {
                roomPrice = Double.parseDouble(scanner.nextLine());
                isValidInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("Error: Invalid price format. Please enter a valid numeric value.");
            }
        }

        return roomPrice;
    }

    private static RoomType enterRoomType(Scanner scanner) {
        RoomType roomType = null;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                roomType = RoomType.SINGLE;
                isValidInput = true;
            } else if (input.equals("2")) {
                roomType = RoomType.DOUBLE;
                isValidInput = true;
            } else {
                System.out.println("Error: Invalid room type. Please enter either 1 or 2.");
            }
        }

        return roomType;
    }

    private static void askToAddAnotherRoom(Scanner scanner) {
        char choice;
        do {
            System.out.println("Would you like to add another room? Y/N");
            String anotherRoom = scanner.nextLine();

            choice = anotherRoom.length() > 0 ? anotherRoom.charAt(0) : ' ';

            switch (choice) {
                case 'Y':
                    addRoom();
                    break;
                case 'N':
                    break;
                default:
                    System.out.println("Please enter Y (Yes) or N (No)");
            }
        } while (choice != 'Y' && choice != 'N');
    }


    private static void displayAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        Iterator<IRoom> roomIterator = rooms.iterator();

        if (!roomIterator.hasNext()) {
            System.out.println("No rooms found.");
            return;
        }

        while (roomIterator.hasNext()) {
            IRoom room = roomIterator.next();
            System.out.println(room);
        }
    }

    private static void displayAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void displayAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void printAdminMenu() {
        StringBuilder adminmainMenuBuiler = new StringBuilder();
        adminmainMenuBuiler.append("\nAdmin Menu\n");
        adminmainMenuBuiler.append("--------------------------------------------\n");
        adminmainMenuBuiler.append("1. See all Customers\n");
        adminmainMenuBuiler.append("2. See all Rooms\n");
        adminmainMenuBuiler.append("3. See all Reservations\n");
        adminmainMenuBuiler.append("4. Add a Room\n");
        adminmainMenuBuiler.append("5. Back to Main Menu\n");
        adminmainMenuBuiler.append("--------------------------------------------\n");
        adminmainMenuBuiler.append("Please select a number for the menu option:\n");

        System.out.print(adminmainMenuBuiler);
    }
}
