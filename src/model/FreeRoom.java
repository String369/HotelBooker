package model;

/** This FreeRoom extended from Room
 * @param  here passing price : 0.0 to define it is a free room
 * @return if price.equals(0.0) , It will be a free room
 * */
public class FreeRoom extends Room{
    FreeRoom(final String roomNumber,final RoomType enumeration) {
        super(roomNumber,0.0, enumeration);
    }

    /*For the better description*/
    @Override
    public String toString(){
        return "Free Room => "+ super.toString();
    }
}
