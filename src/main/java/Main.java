public class Main {
    public static void main(String[] args) {

        Booking booking = Booking.CreateNew(0, "alex");
        booking.Accept();
        booking.Cancel("client refused");
        booking.Accept();

        Booking booking1 = Booking.CreateNew(1, "aalex");
        booking1.Accept();

        Booking booking2 = Booking.CreateNew(2, "alex");
        booking2.Accept();

        System.out.println(Booking.getList());
    }
}
