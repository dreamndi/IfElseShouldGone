abstract class BookingStateBase {

    protected void EnterState(Booking booking) {
    }
    protected void Cancel(Booking booking, String reason) {
    }
    protected void Accept(Booking booking) {
    }


    private class PendingState extends BookingStateBase {
        @Override
        protected void EnterState(Booking booking) {

        }

        @Override
        protected void Cancel(Booking booking, String reason) {

        }

        @Override
        protected void Accept(Booking booking) {

        }
    }
    private class ProcessedState extends BookingStateBase {
        @Override
        protected void EnterState(Booking booking) {

        }

        @Override
        protected void Cancel(Booking booking, String reason) {

        }

        @Override
        protected void Accept(Booking booking) {

        }
    }
    private class CancelledState extends BookingStateBase {
        @Override
        protected void EnterState(Booking booking) {

        }

        @Override
        protected void Cancel(Booking booking, String reason) {

        }

        @Override
        protected void Accept(Booking booking) {

        }
    }
}