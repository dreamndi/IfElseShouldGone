abstract class BookingStateBase {

    protected void EnterState(Booking booking) {
    }

    protected void Cancel(Booking booking, String reason) {
    }

    protected void Accept(Booking booking) {
    }


    public static class PendingState extends BookingStateBase {
        @Override
        protected void EnterState(Booking booking) {
            System.out.println("Booking is on PENDING STATE. Set booking state!");
        }

        @Override
        protected void Cancel(Booking booking, String reason) {
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
            System.out.printf("Booking [id:%d] is on PROCESSED STATE%n", booking.getId());
            if (booking.getEmployeeName().equalsIgnoreCase("Alex"))
                booking.Accept();
            else booking.Cancel(String.format("wrong employee [employee:%s]", booking.getEmployeeName()));
        }

        @Override
        protected void Cancel(Booking booking, String reason) {
            System.out.printf("Booking [id:%d] cannot be accepted because of %s%n", booking.getId(), reason, reason);
            booking.setCancellationReason(reason);
            booking.TransitionToState(new CancelledState());
        }

        @Override
        protected void Accept(Booking booking) {
            System.out.printf("Your booking [id:%d] has been accepted by [employee:%s] at [date:%s]. Thank You!%n",
                    booking.getId(), booking.getEmployeeName(), booking.getDate());
        }
    }

    static class CancelledState extends BookingStateBase {
        @Override
        protected void EnterState(Booking booking) {
            if (booking.getCancellationReason().equalsIgnoreCase("Wrong employee name!"))
                booking.Cancel(booking.getCancellationReason());
        }

        @Override
        protected void Cancel(Booking booking, String reason) {

        }

        @Override
        protected void Accept(Booking booking) {
            System.out.printf("Cannot accept canceled booking [id=%d]. Book again, please!%n", booking.getId());
        }
    }
}