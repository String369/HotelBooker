package model;

/** This interface IRoom have different abstract methods which were used in Room class
 * @ author bindu thotakura
 * */
public interface IRoom {
    /* In interface by default all methods are abstract */
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();
}
