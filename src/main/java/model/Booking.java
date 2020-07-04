package model;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Booking {
    private static final AtomicInteger count = new AtomicInteger(-1);
    private Integer id;
    private BookingStateBase state;
    private String employeeName;
    private final String createdDate;
    private String cancellationReason;
    private static final LinkedHashSet<Booking> list = new LinkedHashSet<>();

    private Booking(String employeeName, BookingStateBase state) {
        //Autoincrement id
        this.id = count.incrementAndGet();
        this.state = state;
        this.employeeName = employeeName;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("z hh:mm:ss a dd-MM-yyyy");
        this.createdDate = simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    //Booking creation method, which adds booking to database (list in my example) and assigns booking to pending state
    public static Booking CreateNew(String employeeName) {
        Booking booking = new Booking(
                employeeName,
                new BookingStateBase.PendingState());
        addToList(booking);
        booking.state.EnterState(booking);
        return booking;
    }

    private static void addToList(Booking booking) {
        list.add(booking);
    }

    void TransitionToState(BookingStateBase newState) {
        this.state = newState;
        this.state.EnterState(this);
    }

    public void Accept() {
        addToList(this);
        this.state.Accept(this);
    }

    public void Cancel(String cancellationReason) {
        this.setCancellationReason(cancellationReason);
        this.state.Cancel(this, cancellationReason);
    }

    @Override
    public String toString() {
        return "\nBooking " +
                "id=" + id +
                ", state=" + state +
                ", employeeName='" + employeeName + '\'' +
                ", createdDate='" + createdDate + '\'' +
                (cancellationReason == null ? "" : (", cancellationReason='" + cancellationReason + '\''));
    }

    //Getters and setters
    public Integer getId() {
        return id;
    }

    public BookingStateBase getState() {
        return state;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    private void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    private void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public static LinkedHashSet<Booking> getList() {
        return list;
    }
}
