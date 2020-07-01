import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    private int id;
    private BookingStateBase state;
    private String employeeName;
    private Date date;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a yyyy-MM-dd");

    private Booking(int id, String employeeName, BookingStateBase state) {
        this.id = id;
        this.state = state;
        this.employeeName = employeeName;
        this.date = new Date(System.currentTimeMillis());
    }

    public static Booking CreateNew(int id, String employeeName) {
        Booking booking = new Booking(
                id,
                employeeName,
                new BookingStateBase.PendingState()
        );
        return booking;
    }

    private void TransitionToState(BookingStateBase newState) {
        this.state = newState;
        this.state.EnterState(this);
    }

    public void Accept() {
        this.state.Accept(this);
    }

    public void Cancel(String cancellationReason) {
        this.state.Cancel(this, cancellationReason);
    }

}
