import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    private int id;
    private BookingStateBase state;
    private String employeeName;
    private String date;
    private String cancellationReason;


    private Booking(int id, String employeeName, BookingStateBase state) {
        this.id = id;
        this.state = state;
        this.employeeName = employeeName;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd z hh:mm:ss a");
        this.date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static Booking CreateNew(int id, String employeeName) {
        Booking booking = new Booking(
                id,
                employeeName,
                new BookingStateBase.PendingState()
        );
        return booking;
    }

    protected void TransitionToState(BookingStateBase newState) {
        this.state = newState;
        this.state.EnterState(this);
    }

    public void Accept() {
        this.state.Accept(this);
    }

    public void Cancel(String cancellationReason) {
        this.state.Cancel(this, cancellationReason);
    }


    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
