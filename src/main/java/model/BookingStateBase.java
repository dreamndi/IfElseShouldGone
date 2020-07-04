package model;

import java.text.SimpleDateFormat;
import java.util.Date;

abstract class BookingStateBase {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("z hh:mm:ss a dd-MM-yyyy");
    protected String currentTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));

    protected void EnterState(Booking booking) {
    }

    protected void Cancel(Booking booking, String cancellationReason) {
    }

    protected void Accept(Booking booking) {
    }


    public static class PendingState extends BookingStateBase {
        @Override
        protected void EnterState(Booking booking) {
            //Check employee and booking validity in more complex project
            System.out.printf("%s: Booking [id:%d] has created and assigned to PENDING STATE %n", currentTime, booking.getId());
        }

        @Override
        protected void Cancel(Booking booking, String cancellationReason) {
            booking.TransitionToState(new CancelledState());
        }

        @Override
        protected void Accept(Booking booking) {
            booking.TransitionToState(new ProcessedState());
        }
    }

    static class ProcessedState extends BookingStateBase {
        @Override
        protected void EnterState(Booking booking) {
            //Check employee and booking validity in more complex project
            if (booking.getEmployeeName().equals("alex"))
                booking.Accept();
            else booking.Cancel(String.format("wrong employee:%s", booking.getEmployeeName()));
        }

        @Override
        protected void Cancel(Booking booking, String cancellationReason) {
            booking.TransitionToState(new CancelledState());
        }

        @Override
        protected void Accept(Booking booking) {
            System.out.printf("%s: Booking [id:%d] has been accepted by [employee:%s]%n",
                    currentTime, booking.getId(), booking.getEmployeeName());
        }
    }

    static class CancelledState extends BookingStateBase {
        @Override
        protected void EnterState(Booking booking) {
            //Check employee and booking validity in more complex project
            booking.Cancel(booking.getCancellationReason());
        }

        @Override
        protected void Cancel(Booking booking, String cancellationReason) {
                System.out.printf("%s: Booking [id:%d] cancelled because of [%s]%n", currentTime, booking.getId(), cancellationReason);
        }

        @Override
        protected void Accept(Booking booking) {
            if (booking.getCancellationReason() != null)
                System.out.printf("%s: Booking [id:%d] cannot be accepted, cause already has been cancelled %n", currentTime, booking.getId());
            else
                System.out.printf("%s: Booking [id:%d] cannot be accepted after cancellation %n", currentTime, booking.getId());
        }
    }
}