import model.Booking;

public class Main {
    public static void main(String[] args) {
        //Only creating booking
        Booking booking = Booking.CreateNew("alex");

        //Accepting correct booking
        Booking booking1 = Booking.CreateNew("alex");
        booking1.Accept();

        //Accepting booking with wrong employee
        Booking booking2 = Booking.CreateNew("nick");
        booking2.Accept();

        //Creating, accepting, cancelling and then accepting booking
        Booking booking3 = Booking.CreateNew("alex");
        booking3.Accept();
        booking3.Cancel("client refused");
        booking3.Accept();

        //Creating, cancelling, then accepting booking
        Booking booking4 = Booking.CreateNew("alex");
        booking4.Cancel("mistake");
        booking4.Accept();

        //Print all bookings
        System.out.println("Bookings:" + Booking.getList()
                .toString()
                .replace("[", "")
                .replace("]", ""));
    }
}
