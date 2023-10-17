package model;

import java.util.Objects;

public class Room implements IRoom{
    private final String roomNumber;
    private final  Double price;
    private final RoomType enumeration;

    /*Constructor */
    public Room(final String roomNumber, final Double price, final RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    /*Implemented all methods from IRoom interface*/
    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }
    @Override
    public Double getRoomPrice() {
        return this.price;
    }
    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }
    @Override
    public boolean isFree() {
        return this.price != null && this.price.equals(0.0);
    }

    /* For better description */
    @Override
    public String toString(){
        return "Room Number : " +this.roomNumber + "\nPrice : "+this.price +"\nEnumeration : "+ this.enumeration;
    }

    /* Equals method : when we're comparing to strings (Reference data type) the reference objects for strings are different,
      when we use "==" it will compare the addresses of two strings instead of objects inside the addresses;
      That's  why here we use Equals and HashCode methods to compare two objects of reference data types,  it will compare each and every character */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(getRoomNumber(), room.getRoomNumber());
    }

    // hashCode method : If two objects are equal by equals() method then their hashcode return by hashCode() method must be same.
    // In output, we don't get duplicate values
    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber());
    }
}
