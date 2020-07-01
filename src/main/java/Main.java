public class Main {
    public static void main(String[] args) {
        Booking booking = Booking.CreateNew(0, "alex");
        booking.Accept();
        Booking booking1 = Booking.CreateNew(1, "aalex");
        booking1.Accept();
    }
}
