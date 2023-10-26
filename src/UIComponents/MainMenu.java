package UIComponents;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    private static final HotelResource hotelResource;
    static {
        hotelResource = HotelResource.getSingleton();
    }
    private static final String DATE_FORMAT = "mm/dd/yyyy";
    public static void mainMenu() {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                printMainMenu();
                String line = scanner.nextLine();

                if (line.length() != 1) {
                    System.out.println("Error: Invalid action\n");
                    continue;
                }

                char choice = line.charAt(0);
                if (line.length() == 1) {
                    switch (choice) {
                        case '1':
                            findAndReserveRoom();
                            break;
                        case '2':
                            seeMyReservation();
                            break;
                        case '3':
                            createAccount();
                            break;
                        case '4':
                            AdminMenu.adminMenu();
                            break;
                        case '5':
                            System.out.println("Exiting...");
                            return; // Exit the method and stop the loop
                        default:
                            System.out.println("Unknown action\n");
                            break;
                    }
                } else {
                    System.out.println("Error: Invalid action\n");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }

    private static void findAndReserveRoom() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");
        Date checkIn = getIPDate(scanner);

        System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
        Date checkOut = getIPDate(scanner);

        if (checkIn == null || checkOut == null) {
            System.out.println("Invalid date input. Exiting reservation process...");
            return;
        }

        boolean roomsFound = false;
        Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);
        Collection<IRoom> recommendedRooms = hotelResource.findRecommendedRooms(checkIn, checkOut);

        if (!availableRooms.isEmpty()) {
            System.out.println("Available rooms for the selected dates:");
            printRooms(availableRooms);
            reserveRoom(scanner, checkIn, checkOut, availableRooms);
            roomsFound = true;
        }

        if (!roomsFound && !recommendedRooms.isEmpty()) {
            System.out.println("Recommended rooms in the dates:" +
                    "\n Check-In Date: " + hotelResource.addDefaultPlusDays(checkIn) +
                    "\n Check-Out Date: " + hotelResource.addDefaultPlusDays(checkOut));
            printRooms(recommendedRooms);
            reserveRoom(scanner, checkIn, checkOut, recommendedRooms);
        }

        if (!roomsFound && recommendedRooms.isEmpty()) {
            System.out.println("No rooms found.");
        }
    }


    private static Date getIPDate(final Scanner scanner) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            return dateFormatter.parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date.Please try again.");
            findAndReserveRoom();
        }
        return null;
    }

    private static void reserveRoom(final Scanner scanner, final Date checkInDate,
                                    final Date checkOutDate, final Collection<IRoom> rooms) {
        System.out.println("Would you like to book? y/n");
        final String bookRoom = scanner.nextLine();

        switch (bookRoom) {
            case "y":
                System.out.println("Do you have an account with us? y/n");
                final String haveAccount = scanner.nextLine();

                if ("y".equals(haveAccount)) {
                    System.out.println("Enter Email format: name@domain.com");
                    final String customerEmail = scanner.nextLine();

                    Customer customer = hotelResource.getCustomer(customerEmail);
                    if (customer == null) {
                        System.out.println("Customer not found.\nYou may need to create a new account.");
                        printMainMenu();
                    } else {
                        System.out.println("What room number would you like to reserve?");
                        final String roomNumber = scanner.nextLine();

                        IRoom room = findRoomByNumber(rooms, roomNumber);
                        if (room != null) {
                            final Reservation reservation = hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);
                            if (reservation != null) {
                                System.out.println("Reservation created successfully!");
                                System.out.println(reservation);
                            } else {
                                System.out.println("Error: The room is not available for the specified dates.\nPlease choose another room or change the dates.");
                            }
                        } else {
                            System.out.println("Error: room number not available.\nStart reservation again.");
                        }
                    }
                } else {
                    System.out.println("Please, create an account.");
                }
                break;

            case "n":
                printMainMenu();
                break;

            default:
                reserveRoom(scanner, checkInDate, checkOutDate, rooms);
                break;
        }
    }

    private static IRoom findRoomByNumber(Collection<IRoom> rooms, String roomNumber) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }


    private static void printRooms(final Collection<IRoom> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found in the hotel.");
        } else {
            for (IRoom room : rooms) {
                System.out.println(room);
            }
        }
    }


    private static void seeMyReservation() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Email in the format ::: name@domain.com");
        final String customerEmail = scanner.nextLine();

        Collection<Reservation> reservations = hotelResource.getCustomerReservations(customerEmail);
        printReservations(reservations);
    }

    private static void printReservations(final Collection<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found in the hotel.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println("\n" + reservation);
            }
        }
    }

    private static void createAccount() {
        final Scanner scanner = new Scanner(System.in);

        String email, firstName, lastName;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("Enter Email format: name@domain.com");
            email = scanner.nextLine();

            System.out.println("First Name:");
            firstName = scanner.nextLine();

            System.out.println("Last Name:");
            lastName = scanner.nextLine();

            try {
                hotelResource.createACustomer(email, firstName, lastName);
                System.out.println("Account created successfully!");
                isValidInput = true;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }


    public static void printMainMenu() {
        StringBuilder mainMenuBuilder = new StringBuilder();
        mainMenuBuilder.append("\nWelcome to the Hotel Reservation Application\n");
        mainMenuBuilder.append("--------------------------------------------\n");
        mainMenuBuilder.append("1. Find and reserve a room\n");
        mainMenuBuilder.append("2. See my reservations\n");
        mainMenuBuilder.append("3. Create an Account\n");
        mainMenuBuilder.append("4. Admin\n");
        mainMenuBuilder.append("5. Exit\n");
        mainMenuBuilder.append("--------------------------------------------\n");
        mainMenuBuilder.append("Please select a number for the menu option:\n");

        System.out.print(mainMenuBuilder);
    }
}
