abstract class BookingStateBase {

    void EnterState(Booking booking) {
    }
    void Cancel(Booking booking, String reason) {
    }
    void Accept(Booking booking) {
    }


    static class PendingState extends BookingStateBase {
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

    static class ProcessedState extends BookingStateBase {
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

    static class CancelledState extends BookingStateBase {
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