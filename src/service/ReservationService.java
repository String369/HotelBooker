package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    //Map<K,V> --> Key =String(type) :"room number" , Value = Customer(type) : Firstname, Lastname, email together
    Map<String, IRoom> roomsRes = new HashMap<>();
    private final List<Reservation> reservationsRes = new ArrayList<>();
    private static final int RECOMMENDED_ROOMS_AUTO_DEFAULT_PLUS_DAYS = 7;


    /*Static Reference : This is used to refer this class in some other class*/
    private ReservationService() {
        // Private constructor prevents direct instantiation from outside the class
    }

    private static class SingletonHolder {
        private static final ReservationService SINGLETON = new ReservationService();
    }

    public static ReservationService getSingleton() {
        return ReservationService.SingletonHolder.SINGLETON;
    }


    public void addRoom(final IRoom room) {
        roomsRes.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(final String roomNumber) {
        return roomsRes.get(roomNumber);
    }

    public Collection<IRoom> getAllRooms() {
        return new ArrayList<>(roomsRes.values());
    }

    // Method to reserve a room for a customer
    public Reservation reserveARoom(final Customer customer, final IRoom room, final Date checkInDate, final Date checkOutDate) {
        if (isRoomAvailable(room, checkInDate, checkOutDate)) {
            Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
            reservationsRes.add(reservation);
            return reservation;
        }
        return null;
    }

    private boolean isRoomAvailable(IRoom room, Date checkInDate, Date checkOutDate) {
        for (Reservation reservation : reservationsRes) {
            if (reservation.getRoom().equals(room)) {
                if (reservationOverlaps(reservation, checkInDate, checkOutDate)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean reservationOverlaps(final Reservation reservation, final Date checkInDate,
                                        final Date checkOutDate) {
        return checkInDate.before(reservation.getCheckOutDate())
                && checkOutDate.after(reservation.getCheckInDate());
    }

    // Method to get a customer's reservations
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservationsRes) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public Collection<IRoom> findRooms(final Date checkInDate, final Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : roomsRes.values()) {
            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Collection<IRoom> findRecommendedRooms(Date checkInDate, Date checkOutDate) {
        Date recommendedCheckInDate = addDefaultPlusDays(checkInDate);
        Date recommendedCheckOutDate = addDefaultPlusDays(checkOutDate);
        return findRooms(recommendedCheckInDate, recommendedCheckOutDate);
    }


    /**
     * The recommended room search will add seven days to the original check-in and check-out dates to see
     * if the hotel has any availabilities and then display the recommended rooms/dates to the customer.
     * if the customers date range search is 1/1/2020 – 1/5/2020 and all rooms are booked, the system will
     * search again for recommended rooms using the date range 1/8/2020 - 1/12/2020. If there are no recommended rooms,
     * the system will not return any rooms.
     */

    public Date addDefaultPlusDays(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, RECOMMENDED_ROOMS_AUTO_DEFAULT_PLUS_DAYS);
        return calendar.getTime();
    }


    public void printAllReservation() {
        final Collection<Reservation> reservations = getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation + "\n");
            }
        }
    }

    /* Create Collections to store and retrieve a reservations*/
    private Collection<Reservation> getAllReservations() {
        Collection<Reservation> allReservations = new LinkedList<>();
        for (Reservation reservations : reservationsRes) {
            allReservations.addAll(Collections.singleton(reservations));
        }
        return allReservations;
    }
}
