import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

public class Booking {
    private Integer id;
    private BookingStateBase state;
    private String employeeName;
    private String date;
    private String cancellationReason;
    private static LinkedHashMap<Integer, Booking> list = new LinkedHashMap<>();

    private Booking(int id, String employeeName, BookingStateBase state) {
        this.id = id;
        this.state = state;
        this.employeeName = employeeName;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd z hh:mm:ss a");
        this.date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static Booking CreateNew(Integer id, String employeeName) {
        Booking booking = new Booking(
                id,
                employeeName,
                new BookingStateBase.PendingState()
        );
        return booking;
    }

    public static void addToList(Booking booking) {
        list.put(booking.getId(), booking);
    }

    public static void deleteFromList(Booking booking) {
        list.remove(booking.getId(), booking);
    }

    protected void TransitionToState(BookingStateBase newState) {
        this.state = newState;
        this.state.EnterState(this);
    }

    public void Accept() {
        if (this.cancellationReason == null) {
            addToList(this);
            this.state.Accept(this);
        }
    }

    public void Cancel(String cancellationReason) {
        deleteFromList(this);
        this.state.Cancel(this, cancellationReason);
    }


    //Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookingStateBase getState() {
        return state;
    }

    public void setState(BookingStateBase state) {
        this.state = state;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public static LinkedHashMap<Integer, Booking> getList() {
        return list;
    }

    @Override
    public String toString() {
        return " Booking {" +
                "id=" + id +
                ", state=" + state +
                ", employeeName='" + employeeName + '\'' +
                ", date='" + date + '\'' +
                (cancellationReason == null ? "" : (", cancellationReason='" + cancellationReason+ '\''))  +
                '}' + "\n";
    }
}
